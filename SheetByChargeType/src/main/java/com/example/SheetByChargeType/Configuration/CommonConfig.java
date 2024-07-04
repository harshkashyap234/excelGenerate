package com.example.SheetByChargeType.Configuration;
import com.example.SheetByChargeType.Model.ChargeType;
import com.example.SheetByChargeType.Model.StaticMapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CommonConfig {
    @Bean
    public static LinkedHashMap<String,List<ChargeType>> sheetNameToChargeType(){
        LinkedHashMap<String,List<ChargeType>> nameToChargeType = new LinkedHashMap<>();
        nameToChargeType.put("Fulfillment Itemized Statement",new ArrayList<>(List.of(ChargeType.FULFILLMENT_FEE)));
        nameToChargeType.put("COD Remittance Statement",new ArrayList<>(List.of(ChargeType.COD_REMITTANCE)));
        nameToChargeType.put("Communication Itemized Statement",new ArrayList<>(List.of(ChargeType.COMMUNICATION_FEE)));
        nameToChargeType.put("Managed Services Itemized Statement",new ArrayList<>(List.of(ChargeType.MANAGED_SERVICES_FEE)));
        nameToChargeType.put("Registration Itemized Statement",new ArrayList<>(List.of(ChargeType.REGISTRATION_FEE)));
        nameToChargeType.put("Catalog Itemized Statement" ,new ArrayList<>(List.of(ChargeType.CATALOG_FEE)));
        nameToChargeType.put("Pricing Itemized Statement",new ArrayList<>(List.of(ChargeType.PRICING_FEE)));
        nameToChargeType.put("Credit or Debit Itemized Statement",new ArrayList<>(List.of(ChargeType.DEBIT_ADJUSTMENT,ChargeType.CREDIT_ADJUSTMENT)));
        nameToChargeType.put("Subscription Itemized Statement" ,new ArrayList<>(List.of(ChargeType.SUBSCRIPTION_FEE)));
        nameToChargeType.put("OMS Itemized Statement",new ArrayList<>(List.of(ChargeType.OMS_FEE)));
        return nameToChargeType;
    }
    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> communicationFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type"));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb No"));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type"));
        flattenNameToProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges"));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges"));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)"));
        return flattenNameToProperty;
    }

    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> fullfillmentFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type"));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb"));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type"));
        flattenNameToProperty.put("chargeDetails.shipmentValue.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("chargeDetails.shipmentValue.amount.", new StaticMapProperties(true, "Shipment Value Amount"));
        flattenNameToProperty.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Shipment Creation Date"));
        flattenNameToProperty.put("chargeDetails.sourcePincode.", new StaticMapProperties(true, "Source Pincode"));
        flattenNameToProperty.put("chargeDetails.sourceCity.", new StaticMapProperties(true, "Source City"));
        flattenNameToProperty.put("chargeDetails.destinationPincode.", new StaticMapProperties(true, "Destination Pincode"));
        flattenNameToProperty.put("chargeDetails.destinationCity.", new StaticMapProperties(true, "Destination City"));
        flattenNameToProperty.put("chargeDetails.zone.", new StaticMapProperties(true, "Zone"));
        flattenNameToProperty.put("chargeDetails.courierCode.", new StaticMapProperties(true, "Courier Code"));
        flattenNameToProperty.put("chargeDetails.deadWeight.", new StaticMapProperties(true, "Dead Weight"));
        flattenNameToProperty.put("chargeDetails.volumeWeight.", new StaticMapProperties(true, "Volume Weight"));
        flattenNameToProperty.put("chargeDetails.chargedWeight.", new StaticMapProperties(true, "Charged Weight"));
        flattenNameToProperty.put("chargeDetails.weightSlab.", new StaticMapProperties(true, "Weight Slab"));
        flattenNameToProperty.put("chargeDetails.shipmentType.", new StaticMapProperties(true, "Shipment Type"));
        flattenNameToProperty.put("chargeDetails.paymentMode.", new StaticMapProperties(true, "Payment Mode"));
        flattenNameToProperty.put("chargeDetails.freightCharges.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("chargeDetails.freightCharges.amount.", new StaticMapProperties(true, "Freight Charges Amount"));
        flattenNameToProperty.put("chargeDetails.codCharges.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Cod Charges Amount"));
        flattenNameToProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges Amount"));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameToProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges ( With Gst )"));
        return flattenNameToProperty;
    }
    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> subscriptionFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type"));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type"));
        flattenNameToProperty.put("chargeDetails.activationDate.", new StaticMapProperties(true, "Activation Date"));
        flattenNameToProperty.put("chargeDetails.endDate.", new StaticMapProperties(true, "End Date"));
        flattenNameToProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Currency Code"));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges"));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)"));
        return flattenNameToProperty;
    }
    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> registrationFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type"));
        flattenNameToProperty.put("chargeDetails.platformName.", new StaticMapProperties(true, "Platform Name"));
        flattenNameToProperty.put("chargeDetails.liveDate.", new StaticMapProperties(true, "Live Date"));
        flattenNameToProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges"));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)"));
        return flattenNameToProperty;
    }

    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> managedServiceFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type"));
        flattenNameToProperty.put("chargeDetails.orderValue.amount.", new StaticMapProperties(true, "Charge Details Amount"));
        flattenNameToProperty.put("chargeDetails.percentageFee.", new StaticMapProperties(true, "Percentage Fee"));
        flattenNameToProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges Currency Code"));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges Amount"));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)"));
        return flattenNameToProperty;
    }

    @Bean
    public static LinkedHashMap<String,LinkedHashMap<String,StaticMapProperties>>  sheetNameToSheetMap(){
        LinkedHashMap<String,LinkedHashMap<String,StaticMapProperties>> flattenNameToProperty = new LinkedHashMap<>();
        flattenNameToProperty.put("Fulfillment Itemized Statement",fullfillmentFee());
        flattenNameToProperty.put("COD Remittance Statement", new LinkedHashMap<>());
        flattenNameToProperty.put("Communication Itemized Statement", communicationFee());
        flattenNameToProperty.put("Managed Services Itemized Statement", managedServiceFee());
        flattenNameToProperty.put("Registration Itemized Statement", registrationFee());
        flattenNameToProperty.put("Catalog Itemized Statement", new LinkedHashMap<>());
        flattenNameToProperty.put("Pricing Itemized Statement", new LinkedHashMap<>());
        flattenNameToProperty.put("Credit or Debit Itemized Statement", new LinkedHashMap<>());
        flattenNameToProperty.put("Subscription Itemized Statement", subscriptionFee());
        flattenNameToProperty.put("OMS Itemized Statement", new LinkedHashMap<>());
        return flattenNameToProperty;
    }
    @Bean
    public  static Set<String> percentageCheckMap(){
        return Set.of(
                "gstPercentage."
                ,"chargeDetails.percentageFee."
        );
    }

}
