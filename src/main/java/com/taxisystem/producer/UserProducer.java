package com.taxisystem.producer;

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
        double max_lat = 10.963984;
        double max_long = 106.477055;

        double min_lat = 10.699614;
        double min_long = 106.579939;

        double longitude = min_long + (max_long - min_long) * rand.nextDouble();
        double latitude = min_lat + (max_lat - min_lat) * rand.nextDouble();
        int[] seat_list = {2,4,7};
        int random_seat_index = rand.nextInt(seat_list.length);
        int seat = seat_list[random_seat_index];
        User newUser = new User(id, longitude, latitude, seat);

        userKafkaTemplate.send(TOPIC, newUser.toString());
        return "Publish successfully!";
    }
}
