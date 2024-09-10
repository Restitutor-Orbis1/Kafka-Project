package com.example.kafkaproject2.controller;

import com.example.kafkaproject2.kafka.lumProducer;
import com.example.kafkaproject2.services.Temp;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Controller
public class luminosityController {

    private lumProducer lumProd;

    public luminosityController(lumProducer lumProd) {
        this.lumProd = lumProd;
    }

    @PostConstruct
    public void generateData() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String fakeData = "Luminosity: " + (Math.random() * 100) + " at " + Instant.now();
            sendData(fakeData);
            Thread.sleep(1000);
        }
    }

    private void sendData(String data) {
        lumProd.sendMessage(data);
    }
}