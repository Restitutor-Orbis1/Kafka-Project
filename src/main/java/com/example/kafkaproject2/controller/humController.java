package com.example.kafkaproject2.controller;

import com.example.kafkaproject2.kafka.humProducer;
import com.example.kafkaproject2.services.Temp;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Controller
public class humController {

    private humProducer humProd;

    public humController(humProducer humProd) {
        this.humProd = humProd;
    }

    @PostConstruct
    public void generateData() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String fakeData = "Humidity: " + (Math.random() * 100) + "% at " + Instant.now();
            sendData(fakeData);
            Thread.sleep(1000);
        }
    }

    private void sendData(String data) {
        humProd.sendMessage(data);
    }
}