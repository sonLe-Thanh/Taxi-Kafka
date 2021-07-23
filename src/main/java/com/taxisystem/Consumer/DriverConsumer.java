package com.taxisystem.Consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.taxisystem.Models.Driver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class DriverConsumer {

    @KafkaListener(topics = "taxi_driver_1", containerFactory = "kafkaListenerContainerFactory" , groupId = "group_id")
    public void consume(Driver driver) throws JsonProcessingException {
        System.out.println("Consumed message: ");
    }
}
