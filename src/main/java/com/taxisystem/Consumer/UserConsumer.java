package com.taxisystem.Consumer;

import com.taxisystem.Database.DatabaseConnection;
import com.taxisystem.Models.Driver;
import com.uber.h3core.H3Core;
import com.uber.h3core.util.GeoCoord;
import com.taxisystem.Utils.ClosestDrivers;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class UserConsumer {

    @KafkaListener(topics = "taxi_user_producer_1", containerFactory = "userKafkaListenerStringContainerFactory" , groupId = "group_id")
    public String consume(String driverInfo) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        System.out.println("Consumed message: " + driverInfo);
        String [] info  = driverInfo.split(",");
        int id = Integer.parseInt(info[0]);
        double receivedLong = Double.parseDouble(info[1]);
        double receivedLat = Double.parseDouble(info[2]);
        int seat = Integer.parseInt(info[3]);
        String hexAddr = info[4];

        H3Core h3 = H3Core.newInstance();
        List<String> listNeighbor = h3.kRing(hexAddr, 1);

        List<Driver> cellDrivers = DatabaseConnection.queryInDB1Cell(hexAddr,seat);
        if (cellDrivers.size() < 5){
            for (String neighbor: listNeighbor){
                List<Driver> neighborDrivers = DatabaseConnection.queryInDB1Cell(neighbor, seat);
                cellDrivers.addAll(neighborDrivers);
            }
        }
        List<Driver> closestDrivers =  ClosestDrivers.getClosestDriver(cellDrivers, receivedLat, receivedLong, 5);
        StringBuilder resultString = new StringBuilder();
        if (closestDrivers != null){
            for (Driver driver: closestDrivers){
                resultString.append(driver.toString()).append("\n");
            }
        }

        System.out.println(resultString);
        stopWatch.stop();
//        File file = new File("/Users/thanhson/Documents/Learn/Taxi\\ project/kafka/time.txt");
//        FileWriter fileWriter = new FileWriter(file,true);
//        fileWriter.write(String.valueOf(stopWatch.getTotalTimeSeconds()));
//        fileWriter.close();
        System.out.println("Elapsed Time in minutes: "+ stopWatch.getTotalTimeSeconds());
        return resultString.toString();
    }
}
