package com.example.SheetByChargeType.Configuration;
import com.example.SheetByChargeType.Model.ChargeDetailsType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
@Configuration
public class CommonConfig {
    @Bean
    public static HashMap<String,List<ChargeDetailsType>> sheetNameToChargeType(){
        HashMap<String,List<ChargeDetailsType>> nameToChargeType = new HashMap<>();
        nameToChargeType.put("Fulfillment Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.FULFILLMENT_FEE)));
        nameToChargeType.put("COD Remittance Statement",new ArrayList<>(List.of(ChargeDetailsType.COD_REMITTANCE)));
        nameToChargeType.put("Communication Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.COMMUNICATION_FEE)));
        nameToChargeType.put("Managed Services Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.MANAGED_SERVICES_FEE)));
        nameToChargeType.put("Registration Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.REGISTRATION_FEE)));
        nameToChargeType.put("Catalog Itemized Statement" ,new ArrayList<>(List.of(ChargeDetailsType.CATALOG_FEE)));
        nameToChargeType.put("Pricing Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.PRICING_FEE)));
        nameToChargeType.put("Credit or Debit Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.DEBIT_ADJUSTMENT, ChargeDetailsType.CREDIT_ADJUSTMENT)));
        nameToChargeType.put("Subscription Itemized Statement" ,new ArrayList<>(List.of(ChargeDetailsType.SUBSCRIPTION_FEE)));
        nameToChargeType.put("OMS Itemized Statement",new ArrayList<>(List.of(ChargeDetailsType.OMS_FEE)));
        return nameToChargeType;
    }
    @Bean
    public  static Set<String> percentageCheckMap(){
        return Set.of(
                "gstPercentage."
                ,"chargeDetails.percentageFee."
        );
    }
    @Bean
    public  static Set<String> dateCheckMap(){
        return Set.of(
                 "transactionTime.",
                "shipmentCreationDate.",
                "chargeDetails.shipmentCreationDate.",
                "deliveryDate.",
                "dateOfRemittance."
                ,"chargeDetails.liveDate.",
                "chargeDetails.endDate.",
                "chargeDetails.activationDate."
        );
    }

}
