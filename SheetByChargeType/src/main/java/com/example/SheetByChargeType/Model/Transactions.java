package com.example.SheetByChargeType.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions {
    private String id;
    private String orgCode;
    private LocalDateTime transactionTime;
    private String transactionType;
    private String chargeType;
    private String transactionDescription;
    private String transactionReference;
    private Amount transactionAmount;
    private Amount balance;
    private String billingPeriodId;
    private String billingStatus;
    private String batchId;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int version;
    private boolean deleted;
    private String _class;

    @Data
    public static class Amount {
        private String currencyCode;
        private double amount;
    }
}
