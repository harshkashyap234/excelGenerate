package com.example.SheetByChargeType.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HeaderMapping implements Serializable {
    String column_header;
    String field_name;
    boolean required;
}