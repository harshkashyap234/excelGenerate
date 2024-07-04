package com.example.SheetByChargeType.Model;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
@Builder
public class TransactionItem {
    @Id
    private String id;
    private String orgCode;
    private String transactionTime;
    private String transactionType;
    private String transactionReference;
    private String chargeSubType;
    private String courierAwbNo;
    private ChargeDetails chargeDetails;
    private String billingPeriodId;
    private TotalCharges totalCharges;
    private Double gstPercentage;
    private TotalCharges totalChargesWithGst;
    private String createdBy;
    private String updatedBy;
    private String createdAt;
    private String modifiedAt;
    private Integer version;
    private String deleted;
    private String _class;
    @Data
    public static class ChargeDetails {
        private String chargeType;
        private String _class;
        private OrderValue orderValue;
        private Double percentageFee;
        private String activationDate;
        private String endDate;
        private String platformName;
        private String liveDate;
        private ShipmentValue shipmentValue;
        private String shipmentCreationDate;
        private String sourcePincode;
        private String sourceCity;
        private String destinationPincode;
        private String destinationCity;
        private String zone;
        private String courierCode;
        private Double deadWeight;
        private Double volumeWeight;
        private Double chargedWeight;
        private String weightSlab;
        private String shipmentType;
        private String paymentMode;
        private FreightCharges freightCharges;
        private CodCharges codCharges;
    }

    @Data
    public static class OrderValue {
        private String currencyCode;
        private Double amount;
    }

    @Data
    public static class ShipmentValue {
        private String currencyCode;
        private Double amount;
    }

    @Data
    public static class TotalCharges {
        private String currencyCode;
        private Double amount;
    }

    @Data
    public static class FreightCharges {
        private String currencyCode;
        private Double amount;
    }

    @Data
    public static class CodCharges {
        private String currencyCode;
        private Double amount;
    }
}
