package org.example.corecrm.dto.building;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class BuildingDto {
    private Long id;
    private String cadastr;
    private String type;
    private Double square;
    private BigDecimal price;
    private String source;
    private String assignedManager;
}
