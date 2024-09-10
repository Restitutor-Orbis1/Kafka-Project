package com.example.kafkaproject2.controller;
import com.example.kafkaproject2.kafka.KafkaProducer;
import com.example.kafkaproject2.services.Temp;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Controller
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostConstruct
    public void generateData() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String fakeData = "Temperature: " + (20 + Math.random() * 10) + "°C at " + Instant.now();
            sendData(fakeData);
            Thread.sleep(1000);
        }
    }

    private void sendData(String data) {
        kafkaProducer.sendMessage(data);
    }
}


