package com.example.MongoToExcel.Controller;

import com.example.MongoToExcel.Model.Orders;
import com.example.MongoToExcel.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/get")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @RequestMapping()
    public List<Orders> createFile(@RequestParam("orgNo") String orgNo , @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws Exception {

          List<Orders> list =commonService.createFile(orgNo,startDate,endDate);

          return list;

    }

}
