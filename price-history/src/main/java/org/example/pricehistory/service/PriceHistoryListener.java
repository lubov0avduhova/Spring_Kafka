package org.example.pricehistory.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.pricehistory.dto.EstateDto;
import org.example.pricehistory.entity.PriceHistory;
import org.example.pricehistory.repository.PriceHistoryRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PriceHistoryListener {
    private final PriceHistoryRepository priceHistoryRepository;


    @KafkaListener(
            topics = "${app.kafka.topic.estate-processor}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handlePriceHistory(List<EstateDto> estateDtos) {
        try {

            List<PriceHistory> entities = estateDtos.stream()
                    .map(dto -> new PriceHistory(
                            dto.getCadastr(),
                            dto.getType(),
                            dto.getSquare(),
                            dto.getPrice(),
                            dto.getSource(),
                            LocalDateTime.now()
                    ))
                    .toList();

            priceHistoryRepository.saveAll(entities);
            log.info("Батч истории успешно сохранен!");

        } catch (Exception e) {
            log.error("Ошибка при сохранении батча: {}", e.getMessage());
            throw e;
        }
    }
}
