package com.bars.notificationservice.kafka.producer;

import com.bars.notificationservice.kafka.event.LogMessageEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LogProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${log.kafka.topic}")
    private String topic;

    public void sendLog(String message, String level) {
        try {
            LogMessageEvent logMessage = new LogMessageEvent();
            logMessage.setMessage(message);
            logMessage.setType(level);
            logMessage.setTimestamp(Instant.now());

            String json = objectMapper.writeValueAsString(logMessage);
            kafkaTemplate.send(topic, json);
        } catch (Exception e) {
            System.err.println("Не удалось сериализовать лог: " + e.getMessage());
        }
    }
}
