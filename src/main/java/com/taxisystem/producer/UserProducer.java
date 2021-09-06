package com.taxisystem.Producer;

import com.taxisystem.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("kafka")
public class UserProducer {

    @Autowired
    KafkaTemplate<String, String> userKafkaTemplate;
    private static final String TOPIC = "taxi_user_producer_1";

    @GetMapping("user/publish")
    public String post(@RequestParam double longitude, @RequestParam double latitude, @RequestParam int seat, @RequestParam String hexAddr){
        User newUser = new User(longitude, latitude, seat, hexAddr);

        userKafkaTemplate.send(TOPIC, newUser.toString());

        return "Publish successfully!";
    }
}
