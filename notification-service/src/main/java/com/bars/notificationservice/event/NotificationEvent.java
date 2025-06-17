package com.bars.notificationservice.event;

import lombok.Data;

import java.util.List;

@Data
public class NotificationEvent {
    private String message;
    private List<String> channels;
    private String recipient;
}
