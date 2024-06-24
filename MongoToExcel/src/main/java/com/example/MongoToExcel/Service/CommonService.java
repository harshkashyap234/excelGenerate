package com.example.MongoToExcel.Service;
import com.example.MongoToExcel.Model.Orders;
import com.example.MongoToExcel.Repositories.CommonRepo;
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
    private ObjectMapper objectMapper;
    @Autowired
    private CommonRepo commonRepo;


    private static String convertToHumanReadable(String input) {
        String[] parts = input.split("\\.");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            int t=0;
            for(int j=0;j<parts[i].length();j++){
                if(Character.isUpperCase(parts[i].charAt(j))){
                    result.append(parts[i].substring(t, t+1).toUpperCase() + parts[i].substring(t+1,j));
                    result.append(" ");
                    t=j;
                }
            }
            result.append(parts[i].substring(t, t+1).toUpperCase() + parts[i].substring(t+1));
            if (i < parts.length - 1) {
                result.append(" - ");
            }
        }
        return result.toString();
    }

    private static void flatten(JsonNode node, String prefix, LinkedHashMap<String,String> map) {
        if (node.isObject()) {
            Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> field = fields.next();
                flatten(field.getValue(), prefix + field.getKey() + ".",map);
            }
        } else if (node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                flatten(node.get(i), prefix + "[" + i + "].",map);
            }
        } else {
            map.put(convertToHumanReadable(prefix),node.asText());
        }
    }

    private  Map<String,String> flattenUtil(Orders order) throws JsonProcessingException {
        String JSONstring = objectMapper.writeValueAsString(order);
        JsonNode root = objectMapper.readTree(JSONstring);
        LinkedHashMap<String,String> mapOfOrderField = new LinkedHashMap<>();
        flatten(root, "" ,mapOfOrderField);
        return mapOfOrderField;
    }


    private void setHeader(Orders order, XSSFSheet sheet,  XSSFCellStyle style ) throws JsonProcessingException {

        int colno = 0;
        XSSFRow  row = sheet.createRow(0);
        Map<String,String> headerMap = flattenUtil(order);
        for(Map.Entry<String,String> details : headerMap.entrySet()){
            String key = details.getKey();
            if(key.equals("Id"))continue;
            XSSFCell cell = row.createCell(colno);
            cell.setCellStyle(style);
            cell.setCellValue(key);
            sheet.autoSizeColumn(colno);
            colno++;
        }

    }
    private void dataMapping(Orders order, XSSFSheet sheet,  XSSFCellStyle style , int rowNo) throws JsonProcessingException {

        int colno = 0;
        XSSFRow  row = sheet.createRow(rowNo);
        Map<String,String> dataMap = flattenUtil(order);
        for(Map.Entry<String,String> details : dataMap.entrySet()){
            String value = details.getValue();
            if(details.getKey().equals("Id"))continue;
            XSSFCell cell = row.createCell(colno);
            cell.setCellStyle(style);
            if (String.valueOf(value).equals("null")) {
                cell.setCellValue("");
            } else cell.setCellValue(String.valueOf(value));
            sheet.autoSizeColumn(colno);
            colno++;
        }
    }

    private String changeStarDataFormat(String dateInStirng) throws ParseException {
        SimpleDateFormat sdate = new SimpleDateFormat(YYYY_MM_DD);
        sdate.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = sdate.parse(dateInStirng);
        SimpleDateFormat outputFormat = new SimpleDateFormat(YYYY_MM_DD_T_HH_MM_SS_SSS_Z);
        outputFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        return  outputFormat.format(date);
    }
    private String changeEndDataFormat(String dateInStirng) throws ParseException {
        SimpleDateFormat sdate = new SimpleDateFormat(YYYY_MM_DD);
        sdate.setTimeZone(TimeZone.getTimeZone(UTC));
        Date date = sdate.parse(dateInStirng);
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
        List<Orders> ordersList = commonRepo.findByOrgCodeAndTransectionTime(orgNo, startDate, endDate);
        if(ordersList.isEmpty())throw new Exception("No Data Found Between Range");
        HashMap<String, List<Orders>> groupByChargeType = new HashMap<>();

        for (Orders orders : ordersList) {
            if (!groupByChargeType.containsKey(orders.getChargeDetails().getChargeType())) {
                List<Orders> list = new ArrayList<>();
                list.add(orders);
                groupByChargeType.put(orders.getChargeDetails().getChargeType(), list);
            } else {
                List<Orders> list = groupByChargeType.get(orders.getChargeDetails().getChargeType());
                list.add(orders);
                groupByChargeType.put(orders.getChargeDetails().getChargeType(), list);
            }
        }

        XSSFWorkbook workbook = new XSSFWorkbook();
        for (Map.Entry<String, List<Orders>> values : groupByChargeType.entrySet()) {
            XSSFSheet sheet = workbook.createSheet(values.getKey());
            List<Orders> list = values.getValue();
            XSSFCellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            if (list == null || list.isEmpty()) throw new Exception("No data found in mongo");
            setHeader(list.get(0),sheet,style);
            int rowno=1;
            for (Orders data : list) {
                dataMapping(data,sheet,style,rowno);
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

