package com.example.SheetByChargeType.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//iske conversion ke lie mapper ki jaroorat haigi hai


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetTotalDto {
    private String chargeType;
    private double TotalCredit;
    private double TotalDebit;
    private double NetTotal;

}
