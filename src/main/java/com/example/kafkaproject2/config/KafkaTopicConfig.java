package com.example.kafkaproject2.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic javaguidesTopic(){
        return TopicBuilder.name("javaguides")
                .build();
    }
    public NewTopic phTopic(){
        return TopicBuilder.name("phtopic")
                .build();
    }
    public NewTopic lumTopic(){
        return TopicBuilder.name("lumtopic")
                .build();
    }
    public NewTopic humTopic(){
        return TopicBuilder.name("humtopic")
                .build();
    }
}
