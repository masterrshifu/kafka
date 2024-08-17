package com.spring.kafka.demo.producer;

import com.spring.kafka.demo.model.PREvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaManager {

    private final MessageProducer messageProducer;

    @Autowired
    public KafkaManager(final MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    public void sendMessage(PREvent payload) {
        messageProducer.publishPREvent(payload);
    }

}