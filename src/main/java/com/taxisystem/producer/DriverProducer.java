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
public class DriverProducer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "taxi_driver_producer_1";

    private static final int[] seatList = {2,4,7};
    @GetMapping("driver/publish")
    public String post(){
        Random rand = new Random();
        int bound = 200;
        int seatBound = 3;
        int id = rand.nextInt(bound);
        double longitude = rand.nextInt(bound) + rand.nextDouble();
        double latitude = rand.nextInt(bound) + rand.nextDouble();
        int idx = rand.nextInt(seatBound);
        int seat = seatList[idx];
        Driver newDriver = new Driver(id, longitude, latitude, seat);

        kafkaTemplate.send(TOPIC, newDriver.toString());
        return "Publish successfully!";
    }
}
