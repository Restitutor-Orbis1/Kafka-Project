package com.example.kafkaproject2.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class KafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    private static final String GRAPHITE_HOST = "localhost";
    private static final int GRAPHITE_PORT = 80;
    private static final Pattern TEMPERATURE_PATTERN = Pattern.compile("Temperature: ([\\d.]+)°C at (.+)");

    @KafkaListener(topics = "javaguides", groupId = "myGroup")
    public void consume(ConsumerRecord<String, String> record) {
        String message = record.value();
        LOGGER.info(String.format("Message received -> %s", message));

        // Extract temperature value and timestamp from the message
        Matcher matcher = TEMPERATURE_PATTERN.matcher(message);
        if (matcher.matches()) {
            double temperature = Double.parseDouble(matcher.group(1));
            String timestamp = matcher.group(2);

            // Send temperature to Graphite
            sendTemperatureToGraphite(temperature, timestamp);
        }
    }

    private void sendTemperatureToGraphite(double temperature, String timestamp) {
        try (Socket socket = new Socket(GRAPHITE_HOST, GRAPHITE_PORT);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            // Format the message in Graphite plaintext format: <metric-path> <metric-value> <timestamp>\n
            String metricPath = "kafka.consumer.temperature";
            String metricValue = String.valueOf(temperature);
            String graphiteMessage = metricPath + " " + metricValue + " " + timestamp + "\n";
            out.println(graphiteMessage);
            out.flush();
        } catch (Exception e) {
            LOGGER.error("Error sending temperature to Graphite: {}", e.getMessage());
        }
    }
}