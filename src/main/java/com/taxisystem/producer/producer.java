package com.taxisystem.Producer;

import com.taxisystem.Models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("kafka")
public class Producer {

    @Autowired
    KafkaTemplate<String, Driver> kafkaTemplate;
    private static final String TOPIC = "taxi_driver_1";

    @GetMapping("publish/{message}")
    public String post(@PathVariable("message") final String message){
        Random rand = new Random();
        int bound = 200;
        int id = rand.nextInt(bound);
        double longitude = rand.nextInt(bound) + rand.nextDouble();
        double latitude = rand.nextInt(bound) + rand.nextDouble();

        kafkaTemplate.send(TOPIC, new Driver(id, longitude, latitude));
        return "Publish successfully!";
    }
}
