package org.example.pricehistory.service;

import lombok.RequiredArgsConstructor;
import org.example.pricehistory.dto.PriceHistoryDto;
import org.example.pricehistory.mapping.PriceHistoryMapper;
import org.example.pricehistory.repository.PriceHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceHistoryService {
    private final PriceHistoryRepository priceHistoryRepository;
    private final PriceHistoryMapper mapper;

    public List<PriceHistoryDto> getAllPrices() {
        return mapper.entityToDto(priceHistoryRepository.findAll());
    }
}
