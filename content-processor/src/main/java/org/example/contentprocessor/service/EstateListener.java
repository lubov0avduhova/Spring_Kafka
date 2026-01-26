package org.example.contentprocessor.service;

import lombok.RequiredArgsConstructor;
import org.example.contentprocessor.dto.EstateDto;
import org.example.contentprocessor.entity.Estate;
import org.example.contentprocessor.repository.EstateRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstateListener {
    private final EstateRepository estateRepository;

    @KafkaListener(
            topics = "${app.kafka.topic.estate-processor}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void handleBatch(List<EstateDto> dtos) {
        for (EstateDto dto : dtos) {
            Optional<Estate> existing = estateRepository.findByCadastr(dto.getCadastr());

            Estate entity;
            if (existing.isPresent()) {
                entity = existing.get();
                entity.setPrice(dto.getPrice());
            } else {
                entity = new Estate();
                entity.setCadastr(dto.getCadastr());
                entity.setPrice(dto.getPrice());
            }
            estateRepository.save(entity);
        }
    }
}
