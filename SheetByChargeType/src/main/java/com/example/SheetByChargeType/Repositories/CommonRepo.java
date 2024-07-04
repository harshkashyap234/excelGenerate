package com.example.SheetByChargeType.Repositories;
import com.example.SheetByChargeType.Model.ChargeType;
import com.example.SheetByChargeType.Model.TransactionItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

public interface CommonRepo extends MongoRepository<TransactionItem,String> {
    @Query("{ 'orgCode': ?0, 'transactionTime': { $gte: ?1, $lte: ?2 } , 'chargeDetails.chargeType': { $in: ?3 } } }")
    List<TransactionItem> findByOrgCodeAndTransectionTimeAndChargeType(String orgCode, String startDate, String endDate, List<ChargeType> chargeType);
}