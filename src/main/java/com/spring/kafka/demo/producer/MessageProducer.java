package com.spring.kafka.demo.producer;

import com.spring.kafka.demo.model.PREvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.ApiException;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.concurrent.TimeoutException;


@Component
@Slf4j
public class MessageProducer {


    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);
    private static final String TOPIC = "MSCD.decision.request";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Retryable(
            value = {Exception.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000)
    )
    public void publishPREvent(PREvent event) {
        LocalDate today = LocalDate.now();
        LocalDate ninetyDaysFromNow = today.plusDays(90);

        if (event.getDueDate().isAfter(today) && event.getDueDate().isBefore(ninetyDaysFromNow)) {
            LOGGER.info("Publishing PR event for client: {}", event.getClientId());

            try {
                // Sending the message synchronously by blocking with get()
                SendResult<String, Object> result = kafkaTemplate.send(TOPIC, String.valueOf(event.getPrId()), event).get();
                handleSuccess(event, result);
            }
            catch (Exception ex) {
                handleFailure(ex);
            }
        } else {
            LOGGER.info("PR event for client: {} is not within the 90-day window. Skipping.", event.getClientId());
        }
    }

    private void handleSuccess(Object payLoad, SendResult<String, Object> result) {
        log.info("Sent message=[" + payLoad + "] with offset=[" + result.getRecordMetadata().offset() + "]");
        log.info("ACK from ProducerListener message: {} offset:  {}", result.getProducerRecord().value(), result.getRecordMetadata().offset());
    }

    private void handleFailure(final Throwable ex) {
        if (ex instanceof KafkaProducerException kpe) {
            ProducerRecord<String, String> dirtyMessage = kpe.getFailedProducerRecord();
            log.error("Unable To Deliver Message - [Topic - {} | Key - {} | Partition - {} | Time - {} | Value - {}] ", dirtyMessage.topic(), dirtyMessage.key(), dirtyMessage.partition(), dirtyMessage.timestamp(), dirtyMessage.value());
            Throwable tr = kpe.getRootCause();
            if (tr instanceof TimeoutException) {
                log.error("TimeOut Occured", tr);
            } else if (tr instanceof ApiException) {
                log.error("Api Exception", tr);
            }
        }
    }

}