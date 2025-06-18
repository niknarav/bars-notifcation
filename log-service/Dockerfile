# Используем официальный OpenJDK 21 образ как базовый
FROM openjdk:21-jdk-slim

# Установка сетевых утилит
RUN apt-get update && \
    apt-get install -y --no-install-recommends \
        curl \
        iputils-ping \
        telnet \
        netcat-openbsd \
        procps \
        dnsutils \
        less

# Очистка кэша для меньшего размера образа
RUN apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

WORKDIR /app

# Копируем JAR-файл из target директории
COPY target/log-service-0.0.1-SNAPSHOT.jar app.jar

# Устанавливаем параметры запуска
ENTRYPOINT ["java", "-jar", "app.jar"]