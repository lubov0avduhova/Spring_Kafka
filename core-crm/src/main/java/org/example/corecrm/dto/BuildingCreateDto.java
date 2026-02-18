package org.example.corecrm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class BuildingCreateDto {
    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;
    private String assignedManager;
}
