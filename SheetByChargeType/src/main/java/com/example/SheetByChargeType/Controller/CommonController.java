package com.example.SheetByChargeType.Controller;

import com.example.SheetByChargeType.Repositories.TransactionRepository;
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

@RestController
@RequestMapping()
public class CommonController {
        @Autowired
        private CommonService commonService;
        @Autowired
        private TransactionsService transactionsService;
        @Autowired
        private TransactionRepository transactionRepository;
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

//        @GetMapping("/getInvoice")
//            public void  getInvoice(Model model, @RequestParam("OrgCode") String orgCode, @RequestParam("startDate") Date startDate, @RequestParam("endDate") Date endDate){
//               List<NetTotalDto>   listOfDto =transactionRepository.findAggregatedResults(orgCode,startDate,endDate);
//               Invoice invoice = new Invoice(
//                        "1069",
//                        "Client Name",
//                        "JohnDoe@gmail.com",
//                        "555-555-5555",
//                        "May 27, 2015",
//                        "June 27, 2015",
//                        "Proin cursus, dui non tincidunt elementum, tortor ex feugiat enim, at elementum enim quam vel purus. Curabitur semper malesuada urna ut suscipit.",
//                        419.25,
//                        3644.25,
//                        listOfDto
//                );
//
//            }

    }

