package com.example.MongoToExcel.Repositories;

import com.example.MongoToExcel.Model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface CommonRepo extends MongoRepository<Orders,String> {

    @Query("{ 'orgCode': ?0, 'transactionTime': { $gte: ?1, $lte: ?2 } }")
    List<Orders> findByOrgCodeAndTransectionTime(String orgCode, String startDate, String endDate);

}
