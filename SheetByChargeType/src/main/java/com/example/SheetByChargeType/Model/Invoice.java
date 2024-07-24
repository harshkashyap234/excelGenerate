package com.example.SheetByChargeType.Model;

import com.example.SheetByChargeType.DTO.NetTotalDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private String id;
    private String clientName;
    private String clientEmail;
    private String clientPhone;
    private String Datefrom;
    private String Dateto;
    private String projectDescription;
    private double hst;
    private double total;
    private List<NetTotalDto> items;

}
