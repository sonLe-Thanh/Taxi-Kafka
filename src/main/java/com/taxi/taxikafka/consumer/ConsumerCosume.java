package com.taxi.taxikafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerCosume {
    @KafkaListener(topics = "taxiTopic1")
    public void listenGroup1(String message){
        System.out.println("Received Message in group 1: " + message);
    }
}
