package org.example.contentloaderadapter.dto;

import java.math.BigDecimal;

public record EstateDto(
         String cadastr,
         String type,
         Double square,
         BigDecimal price,
         String source
) {
}
