package com.bars.notificationservice.service;

import com.bars.notificationservice.kafka.event.NotificationEvent;
import com.bars.notificationservice.kafka.producer.LogProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;
    private final TelegramService telegramService;
    private final LogProducer logProducer;

    public void handleNotification(NotificationEvent event) throws IOException {
        for (String channel : event.getChannels()) {
            switch (channel.toLowerCase()) {
                case "email" -> {
                    try {
                        emailService.sendEmail(event.getRecipient(), "Уведомление", event.getMessage());
                        logProducer.sendLog("Email-уведомления успешно отправлено на " + event.getRecipient()
                                + " с текстом:" + event.getMessage(), "INFO");
                    } catch (Exception e) {
                        String errorMsg = "Ошибка отправки email: " + e.getMessage();
                        logProducer.sendLog(errorMsg, "ERROR");
                        throw new IOException(errorMsg);
                    }
                }
                case "telegram" -> {
                    telegramService.sendMessage(event.getMessage());
                    logProducer.sendLog("Telegram-уведомления успешно отправлено: " + event.getMessage(), "INFO");
                }
                default -> {
                    String warnMsg = "Неизвестный канал: " + channel;
                    logProducer.sendLog(warnMsg, "WARN");
                }
            }
        }
    }
}
