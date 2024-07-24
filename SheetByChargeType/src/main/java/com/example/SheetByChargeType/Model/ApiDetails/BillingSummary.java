package com.example.SheetByChargeType.Model.ApiDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillingSummary {
    private double openingBalance;
    private double totalCredit;
    private double totalDebit;
    private double closingBalance;

    // Getters and Setters
}
