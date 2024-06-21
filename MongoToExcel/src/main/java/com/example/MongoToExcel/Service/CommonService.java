package com.example.MongoToExcel.Service;

import com.example.MongoToExcel.Mapper.OrdersClassMapper;
import com.example.MongoToExcel.Model.Orders;
import com.example.MongoToExcel.Repositories.CommonRepo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommonService {


    public static final String YYYY_MM_DD = "yyyy-MM-dd";

   private void creatingHeader(Map<String, Object> map, XSSFSheet sheet, XSSFCellStyle style){
        int colno =0;
        XSSFRow row = sheet.createRow(0) ;



        for (Map.Entry<String, Object> instance : map.entrySet()) {

            Object value = instance.getValue();
            Object key = instance.getKey();

            if (value instanceof Map) {
                for (Map.Entry<?, ?> instance2 : ((LinkedHashMap<?, ?>) value).entrySet()) {
                    Object value2 = instance2.getValue();
                    Object key2 = instance2.getKey();

                    if (value2 instanceof Map) {
                        for (Map.Entry<?, ?> instance3 : ((LinkedHashMap<?, ?>) value2).entrySet()) {

                            Object key3 = instance3.getKey();

                            XSSFCell cell = row.createCell(colno);
                            cell.setCellStyle(style);

                            cell.setCellValue(String.valueOf(key2) + "."+String.valueOf(key3));
                            sheet.autoSizeColumn(colno);

                            colno++;


                        }
                        continue;
                    }

                    XSSFCell cell = row.createCell(colno);
                    cell.setCellStyle(style);

                    cell.setCellValue(String.valueOf(key2));
                    sheet.autoSizeColumn(colno);

                    colno++;


                }
                continue;

            }

            XSSFCell cell = row.createCell(colno);
            cell.setCellStyle(style);

            cell.setCellValue(String.valueOf(key));
            sheet.autoSizeColumn(colno);

            colno++;
        }



    }


  private  void MappingDataToEachRow(Map<String, Object> map, XSSFSheet sheet,  XSSFCellStyle style , int rowNo) {

        int colno = 0;

        XSSFRow  row = sheet.createRow(rowNo);

        for (Map.Entry<String, Object> instance : map.entrySet()) {

            Object value = instance.getValue();

            if (value instanceof Map) {
                for (Map.Entry<?, ?> instance2 : ((LinkedHashMap<?, ?>) value).entrySet()) {
                    Object value2 = instance2.getValue();

                    if (value2 instanceof Map) {
                        for (Map.Entry<?, ?> instance3 : ((LinkedHashMap<?, ?>) value2).entrySet()) {
                            Object value3 = instance3.getValue();

                            XSSFCell cell = row.createCell(colno);
                            cell.setCellStyle(style);

                            if (String.valueOf(value3).equals("null")) {
                                cell.setCellValue("");
                            } else cell.setCellValue(String.valueOf(value3));
                            sheet.autoSizeColumn(colno);

                            colno++;


                        }
                        continue;
                    }

                    XSSFCell cell = row.createCell(colno);
                    cell.setCellStyle(style);

                    if (String.valueOf(value2).equals("null")) {
                        cell.setCellValue("");
                    } else cell.setCellValue(String.valueOf(value2));
                    sheet.autoSizeColumn(colno);

                    colno++;


                }
                continue;

            }

            XSSFCell cell = row.createCell(colno);
            cell.setCellStyle(style);

            if (String.valueOf(value).equals("null")) {
                cell.setCellValue("");
            } else cell.setCellValue(String.valueOf(value));
            sheet.autoSizeColumn(colno);

            colno++;
        }

    }


    @Autowired
    private CommonRepo commonRepo;


    public List<Orders> createFile(String orgNo, String startDate, String endDate) throws Exception {

        SimpleDateFormat sdate = new SimpleDateFormat(YYYY_MM_DD);
        sdate.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date startDateDate = sdate.parse(startDate);

        Date endDateDate = sdate.parse(endDate);


        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        outputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTime(endDateDate);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        endDateDate = calendar.getTime();
        startDate = outputFormat.format(startDateDate);
        endDate = outputFormat.format(endDateDate);
        List<Orders> ordersList = commonRepo.findByOrgCodeAndTransectionTime(orgNo, startDate, endDate);

        HashMap<String, List<Orders>> map = new HashMap<>();

        for (Orders orders : ordersList) {
            if (!map.containsKey(orders.getChargeDetails().getChargeType())) {
                List<Orders> list = new ArrayList<>();
                list.add(orders);
                map.put(orders.getChargeDetails().getChargeType(), list);

            } else {
                List<Orders> list = map.get(orders.getChargeDetails().getChargeType());
                list.add(orders);
                map.put(orders.getChargeDetails().getChargeType(), list);
            }

        }

        XSSFWorkbook workbook = new XSSFWorkbook();

        for (Map.Entry<String, List<Orders>> values : map.entrySet()) {

            XSSFSheet sheet = workbook.createSheet(values.getKey());
            List<Orders> list = values.getValue();


            XSSFCellStyle style = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);




            if (list == null || list.isEmpty()) throw new Exception("No data found in mongo");

            Map<String, Object> ordersMap = OrdersClassMapper.INSTANCE.toMap(list.get(0));



            creatingHeader(ordersMap,sheet,style);
            int rowno=1;
            for (Orders data : list) {

                Map<String, Object> orderMap = OrdersClassMapper.INSTANCE.toMap(data);
                MappingDataToEachRow(orderMap,sheet,style,rowno);
                rowno++;


            }

        }


        try (FileOutputStream fo = new FileOutputStream(new File("sheet.xlsx"))) {
            workbook.write(fo);
            fo.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


        workbook.close();


        return ordersList;

    }



}

