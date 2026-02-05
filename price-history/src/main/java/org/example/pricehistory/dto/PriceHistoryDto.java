package org.example.pricehistory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class PriceHistoryDto {
    private Long id;
    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;
    private LocalDateTime createdAt;
}
