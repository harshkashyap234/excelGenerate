package com.example.SheetByChargeType.Repositories;

import com.example.SheetByChargeType.DTO.NetTotalDto;
import com.example.SheetByChargeType.Model.TransactionItem;
import com.example.SheetByChargeType.Model.Transactions;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transactions,String> {


    @Aggregation(pipeline = {
            "{$match: {\n" +
                    "  orgCode: 'STE9QG9',\n" +
                    "  transactionTime: {\n" +
                    "    $gte: ISODate('2024-07-06T06:55:47.642Z'),\n" +
                    "    $lt: ISODate('2024-07-10T06:55:47.642Z')\n" +
                    "  }\n" +
                    "}}",
            "{$group: {\n" +
                    "  '_id': '$chargeType',\n" +
                    "  TotalCredit: {\n" +
                    "    $sum: {\n" +
                    "      $cond: [{ $eq: ['$transactionType', 'CREDIT'] }, '$transactionAmount.amount', 0]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  TotalDebit: {\n" +
                    "    $sum: {\n" +
                    "      $cond: [{ $eq: ['$transactionType', 'DEBIT'] }, '$transactionAmount.amount', 0]\n" +
                    "    }\n" +
                    "  }\n" +
                    "}}",
            "{$addFields: {\n" +
                    "  NetTotal: { $subtract: ['$TotalCredit', '$TotalDebit'] }\n" +
                    "}}",
            "{$project: {\n" +
                    "  '_id': 0,\n" +
                    "  ChargeType: '$_id',\n" +
                    "  TotalCredit: 1,\n" +
                    "  TotalDebit: 1,\n" +
                    "  NetTotal: 1\n" +
                    "}}"
    })
    List<NetTotalDto> findAggregatedResults(String orgCode, Date startDate, Date endDate);
}
