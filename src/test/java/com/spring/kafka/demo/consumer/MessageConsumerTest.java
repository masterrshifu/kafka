package com.spring.kafka.demo.consumer;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class MessageConsumerTest {

    @InjectMocks
    private MessageConsumer messageConsumer;


    //@BeforeEach
    //public void setUp() {
    //    messageConsumer = new MessageConsumer();
    //}

    public MessageConsumerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListen() {
        String message = "Hello Kafka";
        messageConsumer.listen(message);
        // Verify the logic inside your listener, if any
    }
}