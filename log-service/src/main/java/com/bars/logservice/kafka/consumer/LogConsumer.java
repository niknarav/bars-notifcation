package com.bars.logservice.kafka.consumer;

import com.bars.logservice.model.LogMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LogConsumer {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "logs-topic", groupId = "log-group")
    public void consume(String message) {
        try {
            LogMessage logMessage = objectMapper.readValue(message, LogMessage.class);
            IndexQuery indexQuery = new IndexQueryBuilder()
                    .withObject(logMessage)
                    .build();

            elasticsearchOperations.index(indexQuery, IndexCoordinates.of("logs-topic"));
        } catch (Exception e) {
            System.err.println("Не удалось сохранить лог в Elasticsearch: " + e.getMessage());
        }
    }
}
