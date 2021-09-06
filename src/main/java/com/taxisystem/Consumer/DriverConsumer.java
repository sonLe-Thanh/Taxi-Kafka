package com.taxisystem.Consumer;

import com.taxisystem.Database.DatabaseConnection;
import com.uber.h3core.H3Core;
import com.uber.h3core.util.GeoCoord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
public class DriverConsumer {

    @KafkaListener(topics = "taxi_driver_producer_1", containerFactory = "driverKafkaListenerStringContainerFactory" , groupId = "group_id")
    public void consume(String driverInfo) throws IOException {
        System.out.println("Consumed message: " + driverInfo);
        String [] info  = driverInfo.split(",");
        int id = Integer.parseInt(info[0]);
        double receivedLong = Double.parseDouble(info[1]);
        double receivedLat = Double.parseDouble(info[2]);
        int seat = Integer.parseInt(info[3]);
        String hexAddr = info[4];


        DatabaseConnection.writeToDB(id, hexAddr, receivedLat, receivedLong, seat);
    }
}
