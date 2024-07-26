package com.example.SheetByChargeType.Model.ApiDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingStatement {
    private String serialNumber;
    private String chargeType;
    private double totalDebit;
    private double totalCredit;
    private double totalCharge;

    // Getters and Setters
}
