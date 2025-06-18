package com.bars.notificationservice.kafka.event;

import lombok.Data;

import java.time.Instant;

@Data
public class LogMessageEvent {

    private String message;

    private String type;

    private Instant timestamp;

}
