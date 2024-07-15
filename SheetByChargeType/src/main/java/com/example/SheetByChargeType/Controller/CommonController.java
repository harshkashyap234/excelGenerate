package com.example.SheetByChargeType.Controller;
import com.example.SheetByChargeType.DTO.NetTotalDto;
import com.example.SheetByChargeType.Model.Transactions;
import com.example.SheetByChargeType.Service.CommonService;
import com.example.SheetByChargeType.Service.TransactionsService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping()
public class CommonController {
        @Autowired
        private CommonService commonService;
        @Autowired
        private TransactionsService transactionsService;
        @RequestMapping("/get")
        public String createFile(@RequestParam("orgNo") String orgNo , @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) throws Exception {
            return commonService.createFile(orgNo,startDate,endDate);
        }

        @RequestMapping("/getByTrxId")
        public byte[]  createSheetByTrx(@RequestParam("TransactionId") String trx) throws IOException {
            return commonService.createSheetByTrx(trx);
        }

        @RequestMapping("/getTransactionsTotal")
    public ResponseEntity<byte[]> getTransactionsTotal(@RequestParam("OrgCode") String orgCode, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate ) throws DocumentException, FileNotFoundException {
            ByteArrayOutputStream pdfStream= transactionsService.getTransactionsTotal(orgCode,startDate,endDate);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
            headers.setContentLength(pdfStream.size());
            return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
        }
    }

