package com.example.NotifApp.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class NotificationController {

    private static final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private MessageChannel mqttOutboundChannel;

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam String message) {
        logger.info("Sending MQTT notification: {}", message);
        mqttOutboundChannel.send(MessageBuilder.withPayload(message).build());
        return "✅ Notification sent to MQTT topic!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "MQTT Producer service is running!";
    }
}
