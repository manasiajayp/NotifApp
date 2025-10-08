package com.example.NotifApp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private MessageChannel mqttOutboundChannel;

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestParam String message) {
        mqttOutboundChannel.send(MessageBuilder.withPayload(message).build());
        return "✅ Notification sent to MQTT topic!";
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "MQTT Producer service is running!";
    }
}

