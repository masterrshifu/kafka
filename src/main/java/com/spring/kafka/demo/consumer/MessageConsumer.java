package com.spring.kafka.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    private final TestService testService;

    @Autowired
    public MessageConsumer(TestService testService) {
        this.testService = testService;
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group-id")
    public int listen(String message) {
        log.info("Received message: " + message);
        return testService.processMessage(message);
    }
}