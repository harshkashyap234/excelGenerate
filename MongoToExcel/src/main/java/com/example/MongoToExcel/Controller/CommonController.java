package com.example.MongoToExcel.Controller;
import com.example.MongoToExcel.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/get")
public class CommonController {
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    @Autowired
    private CommonService commonService;
    @RequestMapping()
    public String createFile(@RequestParam("orgNo") String orgNo , @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws Exception {
        return commonService.createFile(orgNo,startDate,endDate);
    }

}
