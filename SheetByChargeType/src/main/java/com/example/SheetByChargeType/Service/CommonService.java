package com.example.SheetByChargeType.Service;

import com.example.SheetByChargeType.Model.ChargeDetailsType;
import com.example.SheetByChargeType.Model.ExcelConfig;
import com.example.SheetByChargeType.Model.HeaderMapping;
import com.example.SheetByChargeType.Model.TransactionItem;
import com.example.SheetByChargeType.Repositories.TransactionItemRepository;
import com.example.SheetByChargeType.Repositories.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CommonService {
    public static final String YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String UTC = "UTC";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    private ExcelConfig excelConfig;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
    private SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z);
    @Autowired
    private  HashMap<String, List<ChargeDetailsType>> sheetNameToChargeDetailsType ;
    @Autowired
    @Qualifier("percentageCheckMap")
    private Set<String> percentageCheckMap;
    @Autowired
    private LoadJsonDataService loadJsonDataService;
    @Autowired
    @Qualifier("dateCheckMap")
    private Set<String>  dateCheckMap;
    @Autowired
    private TransactionItemRepository transactionItemRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ObjectMapper objectMapper;

    private static void flatten(JsonNode node, String prefix, HashMap<String,String> mapOfOrderField) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                flatten(field.getValue(), prefix + field.getKey() + ".",mapOfOrderField);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                flatten(node.get(i), prefix + "[" + i + "].",mapOfOrderField);
            }
        } else {
            mapOfOrderField.put(prefix,node.asText());
        }
    }
    private  Map<String,String> flattenUtil(TransactionItem transactionItem) throws JsonProcessingException {
        String JSONstring = objectMapper.writeValueAsString(transactionItem);
        JsonNode root = objectMapper.readTree(JSONstring);
        HashMap<String,String> mapOfOrderField = new HashMap<>();
        flatten(root, "" ,mapOfOrderField);
        return mapOfOrderField;
    }
    private void setHeader( XSSFSheet sheet,  XSSFCellStyle style,String sheetName ) throws JsonProcessingException {
        XSSFRow row = sheet.createRow(0);
        String[] JsonFile = sheetName.split(" ");
        sheetName="";
        for(String s :JsonFile){
            sheetName += s;
        }
        Optional<ExcelConfig>  OptionalExcelConfig= loadJsonDataService.readJsonFile(sheetName);
        if(OptionalExcelConfig.isPresent()) {
            excelConfig = OptionalExcelConfig.get();
            List<HeaderMapping> listOfHeaders = excelConfig.getHeaders() ;
            int colno = 0;
            for (HeaderMapping header : listOfHeaders) {
                if (header.isRequired()) {
                    XSSFCell cell = row.createCell(colno);
                    cell.setCellStyle(style);
                    cell.setCellValue(header.getColumn_header());
                    sheet.autoSizeColumn(colno);
                }
                colno++;
            }
        }
    }

    private void dataMapping(TransactionItem transactionItem, XSSFSheet sheet,  XSSFCellStyle style , int rowNo,String sheetName) throws JsonProcessingException {
        int colno=0;
        XSSFRow  row = sheet.createRow(rowNo);
        Map<String,String> flattenFieldToValue = flattenUtil(transactionItem);
        List<HeaderMapping> listOfHeaders = excelConfig!=null?excelConfig.getHeaders(): new ArrayList<>();
        for(HeaderMapping header : listOfHeaders){
            String fieldName = header.getField_name();
            if(flattenFieldToValue.containsKey(fieldName)&& header.isRequired()) {
                String value = flattenFieldToValue.get(fieldName);
                XSSFCell cell = row.createCell(colno);
                cell.setCellStyle(style);
                if (String.valueOf(value).equals("null")) {
                    cell.setCellValue("");
                } else{
                    if(percentageCheckMap.contains(fieldName)) cell.setCellValue(String.format("%.0f%%",Double.parseDouble(value)*100));
                    else if(dateCheckMap.contains(fieldName)) cell.setCellValue(value.substring(0,10));
                    else cell.setCellValue(String.valueOf(value));
                }
                sheet.autoSizeColumn(colno);
            }
            colno++;
        }
    }
    private String changeStarDataFormat(String dateInStirng) throws ParseException {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = simpleDateFormat.parse(dateInStirng);
        outputFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        return  outputFormat.format(date);
    }
    private String changeEndDataFormat(String dateInStirng) throws ParseException {
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = simpleDateFormat.parse(dateInStirng);
        outputFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(UTC));
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 99);
        date= calendar.getTime();
        return  outputFormat.format(date);
    }
    public String createFile(String orgNo, String startDate, String endDate) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        try {
            LocalDate.parse(startDate, formatter);
        } catch (Exception e) {
            throw new Exception("Data is not in yyyy-mm-dd format");
        }
        startDate = changeStarDataFormat(startDate);
        endDate = changeEndDataFormat(endDate);

        XSSFWorkbook workbook = new XSSFWorkbook();
        for(Map.Entry<String, List<ChargeDetailsType>> entry : sheetNameToChargeDetailsType.entrySet() ){
            String sheetName = entry.getKey();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            List<ChargeDetailsType> chargeTypes = entry.getValue();
            List<TransactionItem> transactionItems = transactionItemRepository.findByOrgCodeAndTransectionTimeAndChargeType(orgNo, startDate,endDate,chargeTypes);
            setHeader(sheet,style,sheetName);
            int rowno=1;
            for(TransactionItem transactionItem : transactionItems){
                dataMapping(transactionItem,sheet,style,rowno,sheetName);
                rowno++;
            }
        }

        try (FileOutputStream fo = new FileOutputStream(new File("sheet.xlsx"))) {
            workbook.write(fo);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        workbook.close();
        return "Sheet Successfully Created";

    }

    public byte[] createSheetByTrx(String transactionReference) throws IOException {
        List<TransactionItem> transactionItemsForTrxId = transactionItemRepository.findByTransectionReferance(transactionReference);
        String chargeType = String.valueOf(transactionItemsForTrxId.get(0).getChargeDetails().getChargeType());
        List<TransactionItem> transactionItemsForChargeType = transactionItemRepository.findByChargeType(chargeType);
        for (Map.Entry<String, List<ChargeDetailsType>> entry : sheetNameToChargeDetailsType.entrySet()) {
            String sheetName = entry.getKey();
            if (entry.getValue().contains(ChargeDetailsType.valueOf(chargeType))) {
//                log.info("Creating sheet for transaction reference: {} and sheet name: {}", transactionReference, sheetName);
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet(sheetName);
                XSSFCellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                style.setFont(font);
                style.setAlignment(HorizontalAlignment.CENTER);
                if (!transactionItemsForTrxId.isEmpty()) {
//                    log.info("Setting header for sheet: {}", sheetName);
                    setHeader(sheet, style, sheetName);
                }
                int rowNo = 1;
//                log.info("Mapping data for sheet: {}", sheetName);
                for (TransactionItem transactionItem : transactionItemsForChargeType) {
                    dataMapping(transactionItem, sheet, style, rowNo, sheetName);
                    rowNo++;
                }
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                workbook.write(bos);

                try (FileOutputStream fo = new FileOutputStream(new File("sheet2.xlsx"))) {
                    workbook.write(fo);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }

                return bos.toByteArray();
            }
        }
        return null;
    }

}
