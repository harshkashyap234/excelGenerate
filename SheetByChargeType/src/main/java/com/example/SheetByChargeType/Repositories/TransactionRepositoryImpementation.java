package com.example.SheetByChargeType.Repositories;

import com.example.SheetByChargeType.DTO.NetTotalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


public class TransactionRepositoryImpementation {

    @Autowired
    private MongoTemplate mongoTemplate;

    private ArithmeticOperators ap;


    public List<NetTotalDto> findAggregatedResults(String orgCode,Date startDate, Date endDate) {

        MatchOperation matchOperation= match(new Criteria("orgCode").is(orgCode).andOperator(new Criteria("transactionTime").gte(startDate),new Criteria("transactionTime").lt(endDate)));

        GroupOperation groupOperation= group("chargeType")
                                       .sum(ConditionalOperators.when(new Criteria("transactionType").is("CREDIT")).then("$transactionAmount.amount").otherwise(0)).as("totalCredit")
                                       .sum(ConditionalOperators.when(new Criteria("transactionType").is("DEBIT")).then("$transactionAmount.amount").otherwise(0)).as("totalDebit");

        AddFieldsOperation addFieldsOperation = AddFieldsOperation.addField("netTotal")
                .withValue(ArithmeticOperators.Subtract.valueOf("$totalCredit").subtract("$totalDebit")).build();

        ProjectionOperation projectionOperation= project("totalDebit","totalCredit","$_id","netTotal");

        Aggregation aggregation= newAggregation(matchOperation,groupOperation,addFieldsOperation,projectionOperation);

        AggregationResults<NetTotalDto> results =mongoTemplate.aggregate(aggregation,"transactions",NetTotalDto.class);

        return results.getMappedResults();

    }



}



