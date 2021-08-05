package com.taxisystem.Producer;

import com.taxisystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("kafka")
public class UserProducer {

    @Autowired
    KafkaTemplate<String, String> userKafkaTemplate;
    private static final String TOPIC = "taxi_user_producer_1";

    @GetMapping("user/publish")
    public String post(){
        Random rand = new Random();
        int bound = 200;
        int id = rand.nextInt(bound);
        double longitude = rand.nextInt(bound) + rand.nextDouble();
        double latitude = rand.nextInt(bound) + rand.nextDouble();
        User newUser = new User(id, longitude, latitude);

        userKafkaTemplate.send(TOPIC, newUser.toString());
        return "Publish successfully!";
    }
}
