package com.bars.notificationservice.kafka.consumer;

import com.bars.notificationservice.kafka.event.NotificationEvent;
import com.bars.notificationservice.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final NotificationService notificationService;

    @KafkaListener(topics = "notification-topic", groupId = "notification-group")
    public void consume(String message) {
        log.info("Получено сообщение из Kafka: {}", message);
        try {
            NotificationEvent event = objectMapper.readValue(message, NotificationEvent.class);
            notificationService.handleNotification(event);
        } catch (IOException e) {
            log.error("Ошибка обработки уведомления: {}", e.getMessage());
        }
    }
}
