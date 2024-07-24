package com.example.SheetByChargeType.Model.ApiDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiRequest {
    private String templateOwner;
    private String templateGroup;
    private List<Data> data;
    private String useCase;
    private String contextIdAttribute;
    private String type;
    private String fileName;
    private boolean aggregate;

    // Getters and Setters
}