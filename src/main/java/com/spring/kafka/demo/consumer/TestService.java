package com.spring.kafka.demo.consumer;

import org.springframework.stereotype.Component;

@Component
public class TestService {

    public int processMessage(String message) {
        return message.length();
    }
}