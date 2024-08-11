package com.spring.kafka.demo.producer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MessageProducerTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private MessageProducer messageProducer;

    @Value("${kafka.topic}")
    private String topic;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendMessage() {
        String message = "Hello Kafka";

        messageProducer.sendMessage(topic, message);

        verify(kafkaTemplate, times(1)).send(topic, message);
    }
}