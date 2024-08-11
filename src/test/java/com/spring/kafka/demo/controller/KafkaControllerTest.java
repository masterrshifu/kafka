package com.spring.kafka.demo.controller;

import com.spring.kafka.demo.producer.MessageProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class KafkaControllerTest {

    @Mock
    private MessageProducer messageProducer;

    @InjectMocks
    private KafkaController kafkaController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(kafkaController).build();
        ReflectionTestUtils.setField(kafkaController, "topic", "my-topic");
    }

        @Test
        void testSendMessage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(kafkaController).build();

        String topic = "my-topic";
        String message = "Hello Kafka";


        mockMvc.perform(post("/send")
                        .param("message", message))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent: " + message));

        Mockito.verify(messageProducer, Mockito.times(1)).sendMessage(topic, message);
    }

}