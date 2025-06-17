package com.bars.notificationservice.service;

import com.bars.notificationservice.event.NotificationEvent;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationService {

    private final EmailService emailService;
    private final TelegramService telegramService;

    public NotificationService(EmailService emailService, TelegramService telegramService) {
        this.emailService = emailService;
        this.telegramService = telegramService;
    }

    public void handleNotification(NotificationEvent event) throws IOException {
        for (String channel : event.getChannels()) {
            switch (channel.toLowerCase()) {
                case "email" -> {
                    try {
                        emailService.sendEmail(event.getRecipient(), "Уведомление", event.getMessage());
                    } catch (Exception e) {
                        throw new IOException("Ошибка отправки email: " + e.getMessage());
                    }
                }
                case "telegram" -> telegramService.sendMessage(event.getMessage());
                default -> System.out.println("Неизвестный канал: " + channel);
            }
        }
    }
}
