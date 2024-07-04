package com.example.SheetByChargeType.Service;
import com.example.SheetByChargeType.Model.ChargeType;
import com.example.SheetByChargeType.Model.TransactionItem;
import com.example.SheetByChargeType.Model.StaticMapProperties;
import com.example.SheetByChargeType.Repositories.CommonRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    @Autowired
    private  LinkedHashMap<String, List<ChargeType>> sheetNameByChargeType ;
    @Autowired
    private  LinkedHashMap<String, StaticMapProperties> communicationFee ;
    @Autowired
    private  LinkedHashMap<String, StaticMapProperties> subscriptionFee ;
    @Autowired
    private  LinkedHashMap<String, StaticMapProperties> fullfillmentFee ;
    @Autowired
    private  LinkedHashMap<String, StaticMapProperties>  managedServiceFee ;
    @Autowired
    private  LinkedHashMap<String, StaticMapProperties>  registrationFee ;
    @Autowired
    private LinkedHashMap<String,LinkedHashMap<String,StaticMapProperties>> sheetNameBySheetMap;
    @Autowired
    private CommonRepo commonRepo;
    @Autowired
    private ObjectMapper objectMapper;

    private static void flatten(JsonNode node, String prefix, LinkedHashMap<String,String> mapOfOrderField) {
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
        LinkedHashMap<String,String> mapOfOrderField = new LinkedHashMap<>();
        flatten(root, "" ,mapOfOrderField);
        return mapOfOrderField;
    }


    private void setHeader( XSSFSheet sheet,  XSSFCellStyle style,String sheetName ) throws JsonProcessingException {
        XSSFRow row = sheet.createRow(0);
        int colno=0;
        LinkedHashMap<String,StaticMapProperties> fieldNameByHeaderName = sheetNameBySheetMap.get(sheetName);
        if(!fieldNameByHeaderName.isEmpty())
            for(Map.Entry<String,StaticMapProperties>  fieldNameByHeaderNameEntity : fieldNameByHeaderName.entrySet()){
                String key = fieldNameByHeaderNameEntity.getKey();
                StaticMapProperties headerProperty =fieldNameByHeaderName.get(key);
                if(headerProperty.getIsVisible()) {
                    XSSFCell cell = row.createCell(colno);
                    cell.setCellStyle(style);
                    cell.setCellValue(headerProperty.getHeaderName());
                    sheet.autoSizeColumn(colno);
                    colno++;
                }
            }
    }

    private void dataMapping(TransactionItem transactionItem, XSSFSheet sheet,  XSSFCellStyle style , int rowNo,String sheetName) throws JsonProcessingException {
        int colno = 0;
        XSSFRow  row = sheet.createRow(rowNo);
        Map<String,String> flattenFieldByValue = flattenUtil(transactionItem);
        LinkedHashMap<String,StaticMapProperties> fieldNameByHeaderName = sheetNameBySheetMap.get(sheetName);
        for(Map.Entry<String,String> nameByValueEntity : flattenFieldByValue.entrySet()){
            String value = nameByValueEntity.getValue();
            String key = nameByValueEntity.getKey();
            StaticMapProperties headerProperty =fieldNameByHeaderName.get(key);
            if(fieldNameByHeaderName.containsKey(key) && headerProperty.getIsVisible()) {
                XSSFCell cell = row.createCell(colno);
                cell.setCellStyle(style);
                if (String.valueOf(value).equals("null")) {
                    cell.setCellValue("");
                } else cell.setCellValue(String.valueOf(value));
                sheet.autoSizeColumn(colno);
                colno++;
            }
        }
    }

    private String changeStarDataFormat(String dateInStirng) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = simpleDateFormat.parse(dateInStirng);
        SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z);
        outputFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        return  outputFormat.format(date);
    }
    private String changeEndDataFormat(String dateInStirng) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = simpleDateFormat.parse(dateInStirng);
        SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z);
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
        for(Map.Entry<String, List<ChargeType>> entry : sheetNameByChargeType.entrySet() ){
            String sheetName = entry.getKey();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            XSSFCellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            List<ChargeType> chargeTypes = entry.getValue();
            List<TransactionItem> transactionItems = commonRepo.findByOrgCodeAndTransectionTimeAndChargeType(orgNo, startDate,endDate,chargeTypes);
            if(!transactionItems.isEmpty())
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
}
