package com.example.kafkaproject2.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class humProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(humProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;

    public humProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Measurement sent %s", message));
        kafkaTemplate.send("humtopic", message);
    }

}