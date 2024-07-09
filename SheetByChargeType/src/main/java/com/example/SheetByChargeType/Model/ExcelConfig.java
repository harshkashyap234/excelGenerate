package com.example.SheetByChargeType.Model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ExcelConfig {
        private List<HeaderMapping> headers = new ArrayList<>();
}
