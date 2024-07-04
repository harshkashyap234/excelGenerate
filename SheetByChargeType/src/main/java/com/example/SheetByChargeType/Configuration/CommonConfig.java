package com.example.SheetByChargeType.Configuration;

import com.example.SheetByChargeType.Model.ChargeType;
import com.example.SheetByChargeType.Model.StaticMapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class CommonConfig {

    @Bean
    private static LinkedHashMap<String,List<ChargeType>> sheetNameByChargeType(){
        LinkedHashMap<String,List<ChargeType>> nameByChargeType = new LinkedHashMap<>();

        nameByChargeType.put("Fulfillment Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.FULFILLMENT_FEE)));
        nameByChargeType.put("COD Remittance Statement",new ArrayList<ChargeType>(List.of(ChargeType.COD_REMITTANCE)));
        nameByChargeType.put("Communication Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.COMMUNICATION_FEE)));
        nameByChargeType.put("Managed Services Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.MANAGED_SERVICES_FEE)));
        nameByChargeType.put("Registration Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.REGISTRATION_FEE)));
        nameByChargeType.put("Catalog Itemized Statement" ,new ArrayList<ChargeType>(List.of(ChargeType.CATALOG_FEE)));
        nameByChargeType.put("Pricing Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.PRICING_FEE)));
        nameByChargeType.put("Credit or Debit Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.DEBIT_ADJUSTMENT,ChargeType.CREDIT_ADJUSTMENT)));
        nameByChargeType.put("Subscription Itemized Statement" ,new ArrayList<ChargeType>(List.of(ChargeType.SUBSCRIPTION_FEE)));
        nameByChargeType.put("OMS Itemized Statement",new ArrayList<ChargeType>(List.of(ChargeType.OMS_FEE)));
        return nameByChargeType;
    }

    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> communicationFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameByProperty = new LinkedHashMap<>();

        flattenNameByProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameByProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameByProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameByProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameByProperty.put("chargeSubType.", new StaticMapProperties(true, "Charge Sub Type"));
        flattenNameByProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb No"));
        flattenNameByProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        flattenNameByProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameByProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameByProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        flattenNameByProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameByProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges With Gst - Currency Code"));
        flattenNameByProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges With Gst - Amount"));

        return flattenNameByProperty;
    }

    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> fullfillmentFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameByProperty = new LinkedHashMap<>();

        flattenNameByProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameByProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameByProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameByProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameByProperty.put("chargeSubType.", new StaticMapProperties(true, "Charge Sub Type"));
        flattenNameByProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb No"));
        flattenNameByProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        flattenNameByProperty.put("chargeDetails.shipmentValue.currencyCode.", new StaticMapProperties(true, "Charge Details - Shipment Value - Currency Code"));
        flattenNameByProperty.put("chargeDetails.shipmentValue.amount.", new StaticMapProperties(true, "Charge Details - Shipment Value - Amount"));
        flattenNameByProperty.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Charge Details - Shipment Creation Date"));
        flattenNameByProperty.put("chargeDetails.sourcePincode.", new StaticMapProperties(true, "Charge Details - Source Pincode"));
        flattenNameByProperty.put("chargeDetails.sourceCity.", new StaticMapProperties(true, "Charge Details - Source City"));
        flattenNameByProperty.put("chargeDetails.destinationPincode.", new StaticMapProperties(true, "Charge Details - Destination Pincode"));
        flattenNameByProperty.put("chargeDetails.destinationCity.", new StaticMapProperties(true, "Charge Details - Destination City"));
        flattenNameByProperty.put("chargeDetails.zone.", new StaticMapProperties(true, "Charge Details - Zone"));
        flattenNameByProperty.put("chargeDetails.courierCode.", new StaticMapProperties(true, "Charge Details - Courier Code"));
        flattenNameByProperty.put("chargeDetails.deadWeight.", new StaticMapProperties(true, "Charge Details - Dead Weight"));
        flattenNameByProperty.put("chargeDetails.volumeWeight.", new StaticMapProperties(true, "Charge Details - Volume Weight"));
        flattenNameByProperty.put("chargeDetails.chargedWeight.", new StaticMapProperties(true, "Charge Details - Charged Weight"));
        flattenNameByProperty.put("chargeDetails.weightSlab.", new StaticMapProperties(true, "Charge Details - Weight Slab"));
        flattenNameByProperty.put("chargeDetails.shipmentType.", new StaticMapProperties(true, "Charge Details - Shipment Type"));
        flattenNameByProperty.put("chargeDetails.paymentMode.", new StaticMapProperties(true, "Charge Details - Payment Mode"));
        flattenNameByProperty.put("chargeDetails.freightCharges.currencyCode.", new StaticMapProperties(true, "Charge Details - Freight Charges - Currency Code"));
        flattenNameByProperty.put("chargeDetails.freightCharges.amount.", new StaticMapProperties(true, "Charge Details - Freight Charges - Amount"));
        flattenNameByProperty.put("chargeDetails.codCharges.currencyCode.", new StaticMapProperties(true, "Charge Details - Cod Charges - Currency Code"));
        flattenNameByProperty.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Charge Details - Cod Charges - Amount"));
        flattenNameByProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameByProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameByProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        flattenNameByProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameByProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges With Gst - Currency Code"));
        flattenNameByProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges With Gst - Amount"));

        return flattenNameByProperty;
    }
    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> subscriptionFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameByProperty = new LinkedHashMap<>();
        flattenNameByProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameByProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameByProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameByProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameByProperty.put("chargeSubType.", new StaticMapProperties(true, "Charge Sub Type"));
        flattenNameByProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        flattenNameByProperty.put("chargeDetails.activationDate.", new StaticMapProperties(true, "Charge Details - Activation Date"));
        flattenNameByProperty.put("chargeDetails.endDate.", new StaticMapProperties(true, "Charge Details - End Date"));
        flattenNameByProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameByProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameByProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        flattenNameByProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges With Gst - Currency Code"));
        flattenNameByProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges With Gst - Amount"));
        return flattenNameByProperty;
    }
    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> registrationFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameByProperty = new LinkedHashMap<>();
        flattenNameByProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameByProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameByProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameByProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameByProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        flattenNameByProperty.put("chargeDetails.platformName.", new StaticMapProperties(true, "Charge Details - Platform Name"));
        flattenNameByProperty.put("chargeDetails.liveDate.", new StaticMapProperties(true, "Charge Details - Live Date"));
        flattenNameByProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameByProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameByProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        flattenNameByProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage"));
        flattenNameByProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges (With Gst) - Currency Code"));
        flattenNameByProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst) - Amount"));
        return flattenNameByProperty;
    }

    @Bean
    public  static LinkedHashMap<String, StaticMapProperties> managedServiceFee(){
        LinkedHashMap<String, StaticMapProperties> flattenNameByProperty = new LinkedHashMap<>();
        flattenNameByProperty.put("orgCode.", new StaticMapProperties(true, "Org Code"));
        flattenNameByProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time"));
        flattenNameByProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type"));
        flattenNameByProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction Reference"));
        flattenNameByProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Details - Charge Type"));
        flattenNameByProperty.put("chargeDetails.orderValue.currencyCode.", new StaticMapProperties(true, "Charge Details - Order Value - Currency Code"));
        flattenNameByProperty.put("chargeDetails.orderValue.amount.", new StaticMapProperties(true, "Charge Details - Order Value - Amount"));
        flattenNameByProperty.put("chargeDetails.percentageFee.", new StaticMapProperties(true, "Charge Details - Percentage Fee"));
        flattenNameByProperty.put("billingPeriodId.", new StaticMapProperties(true, "Billing Period Id"));
        flattenNameByProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code"));
        flattenNameByProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges - Amount"));
        flattenNameByProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Total Charges With Gst - Currency Code"));
        flattenNameByProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges With Gst - Amount"));

        return flattenNameByProperty;
    }

    @Bean
    public static LinkedHashMap<String,LinkedHashMap<String,StaticMapProperties>>  sheetNameBySheetMap(){
        LinkedHashMap<String,LinkedHashMap<String,StaticMapProperties>> flattenNameByProperty = new LinkedHashMap<>();

        flattenNameByProperty.put("Fulfillment Itemized Statement",fullfillmentFee());
        flattenNameByProperty.put("COD Remittance Statement", new LinkedHashMap<>());
        flattenNameByProperty.put("Communication Itemized Statement", communicationFee());
        flattenNameByProperty.put("Managed Services Itemized Statement", managedServiceFee());
        flattenNameByProperty.put("Registration Itemized Statement", registrationFee());
        flattenNameByProperty.put("Catalog Itemized Statement", new LinkedHashMap<>());
        flattenNameByProperty.put("Pricing Itemized Statement", new LinkedHashMap<>());
        flattenNameByProperty.put("Credit or Debit Itemized Statement", new LinkedHashMap<>());
        flattenNameByProperty.put("Subscription Itemized Statement", subscriptionFee());
        flattenNameByProperty.put("OMS Itemized Statement", new LinkedHashMap<>());

        return flattenNameByProperty;

    }







}
