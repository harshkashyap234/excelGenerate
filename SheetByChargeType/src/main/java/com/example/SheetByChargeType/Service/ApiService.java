package com.example.SheetByChargeType.Service;

import com.example.SheetByChargeType.DTO.NetTotalDto;
import com.example.SheetByChargeType.Model.ApiDetails.ApiRequest;
import com.example.SheetByChargeType.Model.ApiDetails.BillingStatement;
import com.example.SheetByChargeType.Model.ApiDetails.BillingSummary;
import com.example.SheetByChargeType.Model.ApiDetails.Data;
import com.example.SheetByChargeType.Model.Transactions;
import com.example.SheetByChargeType.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ApiService {

    private static final String url="";
    @Autowired
    private WebClient webClient;

    @Autowired
    private TransactionRepository transactionRepository;

    public void  hitApiForOrgCode() throws ParseException {

        SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));

        Date currDate= iso8601Format.parse("2024-07-06T06:55:47.642Z");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currDate);
        calendar.add(Calendar.HOUR_OF_DAY, -24);
        Date currDateYesterday = calendar.getTime();
        String formattedDate = iso8601Format.format(currDate);
        List<String>  ordCodes = transactionRepository.findByOrg(currDate.toString(),currDateYesterday.toString());
        for(String orgCode:ordCodes){
            sendApiRequest(orgCode,currDate,currDateYesterday);
        }
        System.out.println("Formatted date: " + formattedDate);

    }

    public void sendApiRequest(String orgCode,Date startDate, Date endDate) {

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.setTemplateOwner(orgCode);
        apiRequest.setTemplateGroup("");
        apiRequest.setUseCase("");
        apiRequest.setContextIdAttribute("");
        apiRequest.setType("application/pdf");
        apiRequest.setFileName("abc" + ".pdf");
        apiRequest.setAggregate(false);

        List<Data> dataList = new ArrayList<>();
        Data data = new Data();
        data.setWorkUnitId(1);
        data.setClientName("");
        data.setClientAddress("");
        data.setClientEmail("");
        data.setDate("");
        data.setStatementFrom("");
        data.setStatementTo("");
        List<BillingStatement> billingStatements = new ArrayList<>();
        List<NetTotalDto> netTotalDtoList = transactionRepository.findAggregatedResults(orgCode,startDate,endDate);
        int i=1;
        for (NetTotalDto netTotalDto : netTotalDtoList) {
            BillingStatement billingStatement = new BillingStatement();
            billingStatement.setSerialNumber(String.valueOf(i));
            billingStatement.setChargeType(netTotalDto.getChargeType());
            billingStatement.setTotalDebit(netTotalDto.getTotalDebit());
            billingStatement.setTotalCredit(netTotalDto.getTotalCredit());
            billingStatement.setTotalCharge(netTotalDto.getNetTotal());
            billingStatements.add(billingStatement);
            i++;
        }
        data.setBillingStatement(billingStatements);

        BillingSummary billingSummary = new BillingSummary();
        billingSummary.setOpeningBalance(0);
        billingSummary.setTotalCredit(netTotalDtoList.stream().mapToDouble(NetTotalDto::getTotalCredit).sum());
        billingSummary.setTotalDebit(netTotalDtoList.stream().mapToDouble(NetTotalDto::getTotalDebit).sum());
        billingSummary.setClosingBalance(0);
        data.setBillingSummary(billingSummary);

        data.setGeneratedOn("");

        dataList.add(data);
        apiRequest.setData(dataList);

        webClient.post()
                .uri(url)
                .header("Content-Type", "application/json")
                .body(Mono.just(apiRequest), ApiRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("Response: " + response))
                .doOnError(Throwable::printStackTrace)
                .block();

    }

}
