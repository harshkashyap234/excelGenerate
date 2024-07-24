package com.example.SheetByChargeType.Model.ApiDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;


@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {
    private int workUnitId;
    private String clientName;
    private String clientAddress;
    private String clientEmail;
    private String date;
    private String statementFrom;
    private String statementTo;
    private List<BillingStatement> billingStatement;
    private BillingSummary billingSummary;
    private String generatedOn;

    // Getters and Setters
}
