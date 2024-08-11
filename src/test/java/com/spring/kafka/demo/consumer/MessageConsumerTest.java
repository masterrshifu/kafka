package com.spring.kafka.demo.consumer;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MessageConsumerTest {

    @Autowired
    private  KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private MessageConsumer messageConsumer;

    @MockBean
    private TestService testService;

    public MessageConsumerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListen() {
        String message = "Hello Kafka";
        messageConsumer.listen(message);
        // Verify the logic inside your listener, if any
    }

    //@Test
    // void testMessageProcessing() {
    //    String testMessage = "Hello Kafka";
    //    int expectedLength = testMessage.length();
    //
    //    // Mock the TestService to return the expected length
    //    Mockito.when(testService.processMessage(testMessage)).thenReturn(expectedLength);
    //
    //    // Send message to Kafka topic
    //    kafkaTemplate.send("my-topic", testMessage);
    //
    //    // Verify that TestService was called with the correct parameters
    //    Mockito.verify(testService, Mockito.times(1)).processMessage(testMessage);
    //
    //    assertEquals(expectedLength, messageConsumer.listen(testMessage));
    //}

}