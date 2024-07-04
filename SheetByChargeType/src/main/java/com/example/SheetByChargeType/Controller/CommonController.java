package com.example.SheetByChargeType.Controller;
import com.example.SheetByChargeType.Service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping()
public class CommonController {
        @Autowired
        private CommonService commonService;
        @RequestMapping("/get")
        public String createFile(@RequestParam("orgNo") String orgNo , @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws Exception {
            return commonService.createFile(orgNo,startDate,endDate);
        }

        @RequestMapping("/getByTrxId")
        public byte[]  createSheetByTrx(@RequestParam("TransactionId") String trx) throws IOException {
            return commonService.createSheetByTrx(trx);
        }

    }

