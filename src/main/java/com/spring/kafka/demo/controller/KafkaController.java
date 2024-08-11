package com.spring.kafka.demo.controller;

import com.spring.kafka.demo.producer.MessageProducer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    private MessageProducer messageProducer;

    @Value("${kafka.topic}")
    private String topic;

    @PostMapping("/send")
    public String sendMessage(@RequestParam(required = true, name = "message") @NotNull String message) {
        messageProducer.sendMessage(topic, message);
        return "Message sent: " + message;
    }
}