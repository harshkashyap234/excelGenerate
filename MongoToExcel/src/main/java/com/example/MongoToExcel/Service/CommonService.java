package com.example.MongoToExcel.Service;
import com.example.MongoToExcel.Model.Orders;
import com.example.MongoToExcel.Model.StaticMapProperties;
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
    static Map<String, Object> staticMap = new LinkedHashMap<>();
    static {
        staticMap.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        staticMap.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        staticMap.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        staticMap.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        staticMap.put("chargeSubType.", new StaticMapProperties(true, "Charge Sub Type"));
        staticMap.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb No"));
        staticMap.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        staticMap.put("chargeDetails.orderValue.currencyCode.", new StaticMapProperties(true, "Charge Details - Order Value - Currency Code"));
        staticMap.put("chargeDetails.orderValue.amount.", new StaticMapProperties(true, "Charge Details - Order Value - Amount"));
        staticMap.put("chargeDetails.percentageFee.", new StaticMapProperties(true, "Charge Details - Percentage Fee"));
        staticMap.put("chargeDetails.activationDate.", new StaticMapProperties(true, "Charge Details - Activation Date"));
        staticMap.put("chargeDetails.endDate.", new StaticMapProperties(true, "Charge Details - End Date"));
        staticMap.put("chargeDetails.platformName.", new StaticMapProperties(true, "Charge Details - Platform Name"));
        staticMap.put("chargeDetails.liveDate.", new StaticMapProperties(true, "Charge Details - Live Date"));
        staticMap.put("chargeDetails.shipmentValue.currencyCode.", new StaticMapProperties(true, "Charge Details - Shipment Value - Currency Code"));
        staticMap.put("chargeDetails.shipmentValue.amount.", new StaticMapProperties(true, "Charge Details - Shipment Value - Amount"));
        staticMap.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Charge Details - Shipment Creation Date"));
        staticMap.put("chargeDetails.sourcePincode.", new StaticMapProperties(true, "Charge Details - Source Pincode"));
        staticMap.put("chargeDetails.sourceCity.", new StaticMapProperties(true, "Charge Details - Source City"));
        staticMap.put("chargeDetails.destinationPincode.", new StaticMapProperties(true, "Charge Details - Destination Pincode"));
        staticMap.put("chargeDetails.destinationCity.", new StaticMapProperties(true, "Charge Details - Destination City"));
        staticMap.put("chargeDetails.zone.", new StaticMapProperties(true, "Charge Details - Zone"));
        staticMap.put("chargeDetails.courierCode.", new StaticMapProperties(true, "Charge Details - Courier Code"));
        staticMap.put("chargeDetails.deadWeight.", new StaticMapProperties(true, "Charge Details - Dead Weight"));
        staticMap.put("chargeDetails.volumeWeight.", new StaticMapProperties(true, "Charge Details - Volume Weight"));
        staticMap.put("chargeDetails.chargedWeight.", new StaticMapProperties(true, "Charge Details - Charged Weight"));
        staticMap.put("chargeDetails.weightSlab.", new StaticMapProperties(true, "Charge Details - Weight Slab"));
        staticMap.put("chargeDetails.shipmentType.", new StaticMapProperties(true, "Charge Details - Shipment Type"));
        staticMap.put("chargeDetails.paymentMode.", new StaticMapProperties(true, "Charge Details - Payment Mode"));
        staticMap.put("chargeDetails.freightCharges.currencyCode.", new StaticMapProperties(true, "Charge Details - Freight Charges - Currency Code"));
        staticMap.put("chargeDetails.freightCharges.amount.", new StaticMapProperties(true, "Charge Details - Freight Charges - Amount"));
        staticMap.put("chargeDetails.codCharges.currencyCode.", new StaticMapProperties(true, "Charge Details - Cod Charges - Currency Code"));
        staticMap.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Charge Details - Cod Charges - Amount"));
        staticMap.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        staticMap.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        staticMap.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        staticMap.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        staticMap.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges With Gst - Currency Code"));
        staticMap.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges With Gst - Amount"));

    }

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

    private  Map<String,String> flattenUtil(Orders order) throws JsonProcessingException {
        String JSONstring = objectMapper.writeValueAsString(order);
        JsonNode root = objectMapper.readTree(JSONstring);
        LinkedHashMap<String,String> mapOfOrderField = new LinkedHashMap<>();
        flatten(root, "" ,mapOfOrderField);
        return mapOfOrderField;
    }


    private void setHeader(Orders order, XSSFSheet sheet,  XSSFCellStyle style ) throws JsonProcessingException {
        XSSFRow  row = sheet.createRow(0);
        int colno=0;
        for(Map.Entry<String,Object>  headerMap : staticMap.entrySet()){
            String key = headerMap.getKey();
            StaticMapProperties headerProperty =(StaticMapProperties)staticMap.get(key);
            if(headerProperty.getIsVisible()) {
                XSSFCell cell = row.createCell(colno);
                cell.setCellStyle(style);
                cell.setCellValue(headerProperty.getHeaderName());
                sheet.autoSizeColumn(colno);
                colno++;
            }
        }
    }

    private void dataMapping(Orders order, XSSFSheet sheet,  XSSFCellStyle style , int rowNo) throws JsonProcessingException {
        int colno = 0;
        XSSFRow  row = sheet.createRow(rowNo);
        Map<String,String> dataMap = flattenUtil(order);
        for(Map.Entry<String,String> details : dataMap.entrySet()){
            String value = details.getValue();
            String key = details.getKey();
            StaticMapProperties headerProperty =(StaticMapProperties)staticMap.get(key);
            if(staticMap.containsKey(key) && headerProperty.getIsVisible()) {
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

