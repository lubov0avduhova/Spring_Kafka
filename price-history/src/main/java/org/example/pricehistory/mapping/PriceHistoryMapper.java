package org.example.pricehistory.mapping;

import org.example.pricehistory.dto.PriceHistoryDto;
import org.example.pricehistory.entity.PriceHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceHistoryMapper {
    public List<PriceHistoryDto> entityToDto(List<PriceHistory> priceHistories) {
        return priceHistories.stream()
                .map(entity -> PriceHistoryDto.builder()
                        .id(entity.getId())
                        .cadastr(entity.getCadastr())
                        .type(entity.getType())
                        .square(entity.getSquare())
                        .price(entity.getPrice())
                        .source(entity.getSource())
                        .createdAt(entity.getCreatedAt())
                        .build()).toList();

    }
}
