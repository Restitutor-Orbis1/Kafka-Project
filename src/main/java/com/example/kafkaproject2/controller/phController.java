package com.example.kafkaproject2.controller;
import com.example.kafkaproject2.kafka.phProducer;
import com.example.kafkaproject2.services.Temp;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Controller
public class phController {

    private phProducer phProd;

    public phController(phProducer phProd) {
        this.phProd = phProd;
    }

    @PostConstruct
    public void generateData() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String fakeData = "pH: " + (6 + Math.random() * 4) + " at " + Instant.now();
            sendData(fakeData);
            Thread.sleep(1000);
        }
    }

    private void sendData(String data) {
        phProd.sendMessage(data);
    }
}