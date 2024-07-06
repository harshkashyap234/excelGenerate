package com.example.SheetByChargeType.Configuration;
import com.example.SheetByChargeType.Model.ChargeType;
import com.example.SheetByChargeType.Model.StaticMapProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CommonConfig {
    @Bean
    public static HashMap<String,List<ChargeType>> sheetNameToChargeType(){
        HashMap<String,List<ChargeType>> nameToChargeType = new HashMap<>();
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
    public  static HashMap<String, StaticMapProperties> communicationItemizedStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time",1));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",0));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type",2));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",3));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",4));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb No",5));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",6));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges",7));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges",8));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage",9));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)",10));
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",11));
        return flattenNameToProperty;
    }

    @Bean
    public  static HashMap<String, StaticMapProperties> fullfillmentItemizedStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",0));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time",1));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type",2));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",3));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",4));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb",5));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",6));
        flattenNameToProperty.put("chargeDetails.shipmentValue.currencyCode.", new StaticMapProperties(true, "Currency Code",7));
        flattenNameToProperty.put("chargeDetails.shipmentValue.amount.", new StaticMapProperties(true, "Shipment Value Amount",8));
        flattenNameToProperty.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Shipment Creation Date",9));
        flattenNameToProperty.put("chargeDetails.sourcePincode.", new StaticMapProperties(true, "Source Pincode",10));
        flattenNameToProperty.put("chargeDetails.sourceCity.", new StaticMapProperties(true, "Source City",11));
        flattenNameToProperty.put("chargeDetails.destinationPincode.", new StaticMapProperties(true, "Destination Pincode",12));
        flattenNameToProperty.put("chargeDetails.destinationCity.", new StaticMapProperties(true, "Destination City",13));
        flattenNameToProperty.put("chargeDetails.zone.", new StaticMapProperties(true, "Zone",14));
        flattenNameToProperty.put("chargeDetails.courierCode.", new StaticMapProperties(true, "Courier Code",15));
        flattenNameToProperty.put("chargeDetails.deadWeight.", new StaticMapProperties(true, "Dead Weight",16));
        flattenNameToProperty.put("chargeDetails.volumeWeight.", new StaticMapProperties(true, "Volume Weight",17));
        flattenNameToProperty.put("chargeDetails.chargedWeight.", new StaticMapProperties(true, "Charged Weight",18));
        flattenNameToProperty.put("chargeDetails.weightSlab.", new StaticMapProperties(true, "Weight Slab",19));
        flattenNameToProperty.put("chargeDetails.shipmentType.", new StaticMapProperties(true, "Shipment Type",20));
        flattenNameToProperty.put("chargeDetails.paymentMode.", new StaticMapProperties(true, "Payment Mode",21));
        flattenNameToProperty.put("chargeDetails.freightCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",22));
        flattenNameToProperty.put("chargeDetails.freightCharges.amount.", new StaticMapProperties(true, "Freight Charges Amount",23));
        flattenNameToProperty.put("chargeDetails.codCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",24));
        flattenNameToProperty.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Cod Charges Amount",25));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",26));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges Amount",27));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage",28));
        flattenNameToProperty.put("totalChargesWithGst.currencyCode.", new StaticMapProperties(true, "Currency Code",29));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges ( With Gst )",30));
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",31));
        return flattenNameToProperty;
    }
    @Bean
    public  static HashMap<String, StaticMapProperties> subscriptionItemizedStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();

        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",0));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time",1));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type",2));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",3));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",4));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",5));
        flattenNameToProperty.put("chargeDetails.activationDate.", new StaticMapProperties(true, "Activation Date",6));
        flattenNameToProperty.put("chargeDetails.endDate.", new StaticMapProperties(true, "End Date",7));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",8));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges",9));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage",10));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)",11));
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",12));
        return flattenNameToProperty;
    }
    @Bean
    public  static HashMap<String, StaticMapProperties> registrationItemizedStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();

        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",1));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time",2));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type",3));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",4));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",5));
        flattenNameToProperty.put("chargeDetails.platformName.", new StaticMapProperties(true, "Platform Name",6));
        flattenNameToProperty.put("chargeDetails.liveDate.", new StaticMapProperties(true, "Live Date",7));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges - Currency Code",8));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges",9));
        flattenNameToProperty.put("gstPercentage.", new StaticMapProperties(true, "Gst Percentage",10));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)",11));
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",12));
        return flattenNameToProperty;
    }

    @Bean
    public  static HashMap<String, StaticMapProperties> managedServiceItemizedStatementMap(){

        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",0));
        flattenNameToProperty.put("transactionTime.", new StaticMapProperties(true, "Transaction Time",1));
        flattenNameToProperty.put("transactionType.", new StaticMapProperties(true, "Transaction Type",2));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",3));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",4));
        flattenNameToProperty.put("chargeDetails.orderValue.amount.", new StaticMapProperties(true, "Charge Details Amount",5));
        flattenNameToProperty.put("chargeDetails.percentageFee.", new StaticMapProperties(true, "Percentage Fee",6));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges Currency Code",7));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges Amount",8));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)",9));
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",10));
        return flattenNameToProperty;
    }

    @Bean
    public static HashMap<String, StaticMapProperties> CODRemittanceStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();

        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",0));
        flattenNameToProperty.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Shipment Creation Date",1));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Org Code",2));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",3));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier Awb",4));
        flattenNameToProperty.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Cod Charges Amount",5));
        flattenNameToProperty.put("deliveryDate.", new StaticMapProperties(true, "Delivery Date",6));
        flattenNameToProperty.put("dateOfRemittance.", new StaticMapProperties(true, "Date of Remittance",7));
        flattenNameToProperty.put("toBankName.", new StaticMapProperties(true, "to Bank Name",8));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",9));
        return flattenNameToProperty;
    }

    @Bean
    public static HashMap<String, StaticMapProperties> creditOrDebitItemizedStatementMap(){

        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",0));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Client Code",1));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",2));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",3));
        flattenNameToProperty.put("courierAwbNo.", new StaticMapProperties(true, "Courier AWB",4));
        flattenNameToProperty.put("chargeDetails.shipmentValue.amount.", new StaticMapProperties(true, "Shipment Value",5));
        flattenNameToProperty.put("chargeDetails.sourcePincode.", new StaticMapProperties(true, "Source Pincode",6));
        flattenNameToProperty.put("chargeDetails.sourceCity.", new StaticMapProperties(true, "Source City",7));
        flattenNameToProperty.put("chargeDetails.destinationPincode.", new StaticMapProperties(true, "Destination Pincode",8));
        flattenNameToProperty.put("chargeDetails.destinationCity.", new StaticMapProperties(true, "Destination City",9));
        flattenNameToProperty.put("chargeDetails.zone.", new StaticMapProperties(true, "Zone",10));
        flattenNameToProperty.put("chargeDetails.courierCode.", new StaticMapProperties(true, "Courier Code",11));
        flattenNameToProperty.put("chargeDetails.deadWeight.", new StaticMapProperties(true, "Dead Weight",12));
        flattenNameToProperty.put("chargeDetails.volumeWeight.", new StaticMapProperties(true, "Volume Weight",13));
        flattenNameToProperty.put("chargeDetails.chargedWeight.", new StaticMapProperties(true, "Charged Weight",14));
        flattenNameToProperty.put("chargeDetails.weightSlab.", new StaticMapProperties(true, "Weight Slab",15));
        flattenNameToProperty.put("chargeDetails.shipmentType.", new StaticMapProperties(true, "Shipment Type",16));
        flattenNameToProperty.put("chargeDetails.paymentMode.", new StaticMapProperties(true, "Payment Mode",17));
        flattenNameToProperty.put("chargeDetails.freightCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",18));
        flattenNameToProperty.put("chargeDetails.freightCharges.amount.", new StaticMapProperties(true, "Freight Charges Amount",19));
        flattenNameToProperty.put("chargeDetails.codCharges.currencyCode.", new StaticMapProperties(true, "Currency Code",20));
        flattenNameToProperty.put("chargeDetails.codCharges.amount.", new StaticMapProperties(true, "Cod Charges Amount",21));
        flattenNameToProperty.put("totalCharges.currencyCode.", new StaticMapProperties(true, "Total Charges Currency Code",22));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges Amount",23));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (With Gst)",24));
        flattenNameToProperty.put("rtoCharges.", new StaticMapProperties(true, "Declared RTO Charges",25));
        flattenNameToProperty.put("newZone.", new StaticMapProperties(true, "New Zone",26));
        flattenNameToProperty.put("newDeadWeight.", new StaticMapProperties(true, "New Dead Weight",27));
        flattenNameToProperty.put("newVolWeight.", new StaticMapProperties(true, "New Vol. Weight",28));
        flattenNameToProperty.put("newChargedWeight.", new StaticMapProperties(true, "New Charged Weight",29));
        flattenNameToProperty.put("newForwardFreight.", new StaticMapProperties(true, "New Forward Freight",30));
        flattenNameToProperty.put("newCodCharges.", new StaticMapProperties(true, "New COD Charges",31));
        flattenNameToProperty.put("newRtoCharges.", new StaticMapProperties(true, "New RTO Charges",32));
        flattenNameToProperty.put("newTotalCharges.", new StaticMapProperties(true, "New Total Charges",33));
        flattenNameToProperty.put("newTotalChargesWithGst.", new StaticMapProperties(true, "New Total Charges (with GST)",34));
        flattenNameToProperty.put("differenceAmount.", new StaticMapProperties(true, "Difference Amount",35));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",36));
        return flattenNameToProperty;

    }

    @Bean
    public static  HashMap<String, StaticMapProperties> catalogItemizedStatementMap(){

        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",0));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Client Code",1));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",2));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",3));
        flattenNameToProperty.put("skuId.", new StaticMapProperties(true, "SKU ID",4));
        flattenNameToProperty.put("skuDescription.", new StaticMapProperties(true, "SKU Description",5));
        flattenNameToProperty.put("chargeDetails.shipmentCreationDate.", new StaticMapProperties(true, "Shipment Creation Date",6));
        flattenNameToProperty.put("totalCharges.amount", new StaticMapProperties(true, "Total Charges",7));
        flattenNameToProperty.put("gstPercent.", new StaticMapProperties(true, "GST%",8));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (with GST)",9));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",10));
        return flattenNameToProperty;
    }

    @Bean
    public static  HashMap<String, StaticMapProperties> OMSItemizedStatementMap(){

        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",0));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Client Code",1));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",2));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",3));
        flattenNameToProperty.put("chargeDetails.platformName.", new StaticMapProperties(true, "Platform Name",4));
        flattenNameToProperty.put("orderId.", new StaticMapProperties(true, "Order ID",5));
        flattenNameToProperty.put("totalCharges.amount", new StaticMapProperties(true, "Total Charges",6));
        flattenNameToProperty.put("gstPercent.", new StaticMapProperties(true, "GST%",7));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (with GST)",8));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",9));
        return flattenNameToProperty;


    }
    @Bean
    public static  HashMap<String, StaticMapProperties> pricingItemizedStatementMap(){
        HashMap<String, StaticMapProperties> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("dateOfLedger.", new StaticMapProperties(true, "Date of Ledger",0));
        flattenNameToProperty.put("orgCode.", new StaticMapProperties(true, "Client Code",1));
        flattenNameToProperty.put("chargeDetails.chargeType.", new StaticMapProperties(true, "Charge Type",2));
        flattenNameToProperty.put("chargeSubType.", new StaticMapProperties(true, "Service Type",3));
        flattenNameToProperty.put("skuId.", new StaticMapProperties(true, "SKU / POG ID",4));
        flattenNameToProperty.put("skuDescription.", new StaticMapProperties(true, "SKU Description",5));
        flattenNameToProperty.put("runDate.", new StaticMapProperties(true, "Run Date",6));
        flattenNameToProperty.put("runTime.", new StaticMapProperties(true, "Run Time",7));
        flattenNameToProperty.put("totalCharges.amount.", new StaticMapProperties(true, "Total Charges",8));
        flattenNameToProperty.put("gstPercent.", new StaticMapProperties(true, "GST %",9));
        flattenNameToProperty.put("totalChargesWithGst.amount.", new StaticMapProperties(true, "Total Charges (with GST)",10));
        flattenNameToProperty.put("transactionReference.", new StaticMapProperties(true, "Transaction ID",11));
        return flattenNameToProperty;

    }

    @Bean
    public static HashMap<String,HashMap<String,StaticMapProperties>>  sheetNameToSheetMap(){
        HashMap<String,HashMap<String,StaticMapProperties>> flattenNameToProperty = new HashMap<>();
        flattenNameToProperty.put("Fulfillment Itemized Statement",fullfillmentItemizedStatementMap());
        flattenNameToProperty.put("COD Remittance Statement", CODRemittanceStatementMap());
        flattenNameToProperty.put("Communication Itemized Statement", communicationItemizedStatementMap());
        flattenNameToProperty.put("Managed Services Itemized Statement", managedServiceItemizedStatementMap());
        flattenNameToProperty.put("Registration Itemized Statement", registrationItemizedStatementMap());
        flattenNameToProperty.put("Catalog Itemized Statement", catalogItemizedStatementMap());
        flattenNameToProperty.put("Pricing Itemized Statement", pricingItemizedStatementMap());
        flattenNameToProperty.put("Credit or Debit Itemized Statement",creditOrDebitItemizedStatementMap());
        flattenNameToProperty.put("Subscription Itemized Statement", subscriptionItemizedStatementMap());
        flattenNameToProperty.put("OMS Itemized Statement", OMSItemizedStatementMap());

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
