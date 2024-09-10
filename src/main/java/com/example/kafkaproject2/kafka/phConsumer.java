package com.example.kafkaproject2.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class phConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(phConsumer.class);

    @KafkaListener(topics = "phtopic", groupId = "myGroup")
    public void consume(String message){
        LOGGER.info(String.format("Measurement received -> %s", message));
    }
}
