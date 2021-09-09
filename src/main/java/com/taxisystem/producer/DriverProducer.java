package com.taxisystem.Producer;
import com.taxisystem.Models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("kafka")
public class DriverProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "taxi_driver_producer_1";

    @GetMapping("driver/publish")
    public String post(@RequestParam int id, @RequestParam double longitude, @RequestParam double latitude, @RequestParam int seat, @RequestParam String hexAddr){
        Driver newDriver = new Driver(id, longitude, latitude, seat, hexAddr);

        kafkaTemplate.send(TOPIC, newDriver.toString());

        return "Publish successfully!";
    }
}
