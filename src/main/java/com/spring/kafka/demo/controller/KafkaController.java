package com.spring.kafka.demo.controller;

import com.spring.kafka.demo.model.PREvent;
import com.spring.kafka.demo.producer.MessageProducer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Value("${kafka.topic}")
    private  String topic;

    private final MessageProducer messageProducer;

    @Autowired
    public KafkaController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    //@PostMapping("/send")
    //public String sendMessage(@RequestParam(required = true, name = "message") @NotNull String message) {
    //    messageProducer.sendMessage(topic, message);
    //    return "Message sent: " + message;
    //}

    @PostMapping("/publishPrEvent")
    public void publishPREvent(@RequestBody PREvent prEvent) {
        messageProducer.publishPREvent(prEvent);
    }


}