package com.spring.kafka.demo.consumer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
class MessageConsumerTest {

    @InjectMocks
    private MessageConsumer messageConsumer;

    public MessageConsumerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
     void testListen() {
        String message = "Hello Kafka";
        messageConsumer.listen(message);
        // Verify the logic inside your listener, if any
    }
}