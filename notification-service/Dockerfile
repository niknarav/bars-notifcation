# Используем официальный OpenJDK 21 образ как базовый
FROM openjdk:21-jdk-slim
WORKDIR /app

# Копируем JAR-файл из target директории
COPY target/notification-service-0.0.1-SNAPSHOT.jar app.jar

# Устанавливаем параметры запуска
ENTRYPOINT ["java", "-jar", "app.jar"]