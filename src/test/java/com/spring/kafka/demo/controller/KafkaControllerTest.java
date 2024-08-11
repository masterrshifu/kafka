package com.spring.kafka.demo.controller;

import com.spring.kafka.demo.producer.MessageProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class KafkaControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MessageProducer messageProducer;

    @InjectMocks
    private KafkaController kafkaController;

    @Value("${kafka.topic}")
    private String topic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(kafkaController).build();
    }

    @Test
    public void testSendMessage() throws Exception {
        String message = "test-message";

        // Perform a POST request to /send with the message parameter
        mockMvc.perform(post("/send")
                        .param("message", message))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent: " + message));

        // Verify that the sendMessage method in MessageProducer was called with correct parameters
        verify(messageProducer, times(1)).sendMessage(topic, message);
    }

    @Test
    public void testSendMessage_nullMessage() throws Exception {
        // Verify the handling of null messages
        mockMvc.perform(post("/send")
                        .param("message", (String) null))
                .andExpect(status().isBadRequest()); // Adjust based on your controller's behavior
    }
}