package com.NotificationService.Config;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

@Service
public class NotificationServiceConfig {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    @KafkaListener(topics = "inventory-notifications", groupId = "notification-group")
    public void receiveNotification(String message) {
        logger.info("Received low inventory notification: {}", message);
        // Simulate sending email or any other alert mechanism
        simulateSendingAlert(message);
    }

    private void simulateSendingAlert(String message) {
        // Simulate sending an email or other type of alert
        logger.info("Simulating sending alert for notification: {}", message);
        // Implement your alert sending logic here
    }
}