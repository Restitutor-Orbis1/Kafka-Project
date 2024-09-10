package com.example.kafkaproject2.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class humConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(humConsumer.class);

    @KafkaListener(topics = "humtopic", groupId = "myGroup")
    public void consume(String message){
        LOGGER.info(String.format("Measurement received -> %s", message));
    }
}

