package org.example.pricehistory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstateDto {
    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;
}
