package com.spring.kafka.demo.consumer;

import com.spring.kafka.demo.model.PREvent;
import com.spring.kafka.demo.repository.PREventRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageConsumer {

    @Autowired
    private PREventRepository prEventRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(topics = "MSCD.decision.request", groupId = "group_id")
    public void consume(PREvent event) {
        LOGGER.info("Received PR event update: {}", event.getClientId());
        prEventRepository.save(event);

        // Handle the received message, e.g., process acknowledgment, case assignment, or decision
    }
}