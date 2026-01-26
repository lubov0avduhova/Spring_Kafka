package org.example.sendernotification.service;

import lombok.extern.slf4j.Slf4j;
import org.example.sendernotification.dto.EstateDto;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationListener {
    @RetryableTopic(
            attempts = "3",
            backoff = @Backoff(delay = 2000, multiplier = 2.0),
            autoCreateTopics = "true"
    )
    @KafkaListener(topics = "${app.kafka.topic.estate-processor}", groupId = "notification-group")
    public void sendNotification(EstateDto dto) {
        log.info("Пытаемся отправить уведомление для объекта: {}", dto.getCadastr());

        if (dto.getPrice().doubleValue() > 10000000) {
            log.error("Ошибка сети при отправке SMS! Уходим в Retry...");
            throw new RuntimeException("SMS Service is down");
        }

        log.info("Уведомление успешно отправлено!");
    }
}