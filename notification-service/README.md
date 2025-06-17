# NotificationService — Микросервис отправки уведомлений

Микросервис `notification-service` предназначен для:
- Подписки на топик Apache Kafka (`notification-topic`)
- Обработки событий
- Отправки уведомлений через:
    - Email
    - Telegram

Реализован на: **Java 21**, **Spring Boot**, **Apache Kafka**, **Docker**

---

## 🧩 Архитектура


| notification-svc | -----> | Kafka (notification-topic) 


---

## 🛠 Функциональные возможности

- Подписка на Kafka топик `notification-topic`
- Парсинг JSON-сообщения с указанием:
    - Каналы уведомления (`channels`: `["email", "telegram"]`)
    - Текст сообщения (`message`)
    - Получатель (`recipient`)
- Отправка уведомлений по указанным каналам

---

## 🔧 Технологии

| Компонент       | Версия / Использование      |
|----------------|-----------------------------|
| Java           | 21                           |
| Spring Boot    | 3.x                         |
| Kafka          | Подписка и чтение сообщений |
| SMTP           | Для отправки email          |
| Telegram Bot API | Для отправки уведомлений    |
| Docker         | Контейнеризация сервиса     |

