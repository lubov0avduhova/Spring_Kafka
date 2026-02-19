package org.example.pricehistory.mapping;

import org.example.pricehistory.dto.PriceHistoryDto;
import org.example.pricehistory.entity.PriceHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceHistoryMapper {

    public PriceHistoryDto toDto(PriceHistory priceHistory) {
        return PriceHistoryDto.builder()
                        .id(priceHistory.getId())
                        .cadastr(priceHistory.getCadastr())
                        .type(priceHistory.getType())
                        .square(priceHistory.getSquare())
                        .price(priceHistory.getPrice())
                        .source(priceHistory.getSource())
                        .createdAt(priceHistory.getCreatedAt())
                        .build();
    }

    public List<PriceHistoryDto> entityToDto(List<PriceHistory> priceHistories) {
        return priceHistories.stream()
                .map(this::toDto)
                .toList();
    }
}
