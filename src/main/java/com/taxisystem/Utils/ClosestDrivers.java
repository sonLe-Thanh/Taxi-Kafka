package com.taxisystem.Utils;

import com.taxisystem.Models.Driver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

public class ClosestDrivers {
    public static List<Driver> getClosestDriver(List<Driver> driverList, double latitude, double longitude, int k){
        if (driverList == null){
            return null;
        }
        int n = driverList.size();
        List<Driver> listResult = new LinkedList<Driver>();
        double [] listDistance = new double[n];
        for (int i =0; i<n; i++){
            double driverLat = driverList.get(i).getLatitude();
            double driverLong = driverList.get(i).getLongitude();
            listDistance[i] = Math.sqrt(Math.pow(latitude - driverLat,2)+Math.pow(longitude-driverLong,2));
        }
        Arrays.sort(listDistance);
        double kdist = listDistance[k-1];

        for (Driver driver : driverList) {
            double driverLat = driver.getLatitude();
            double driverLong = driver.getLongitude();
            double distance = Math.sqrt(Math.pow(latitude - driverLat, 2) + Math.pow(longitude - driverLong, 2));
            if (distance <= kdist) {
                listResult.add(driver);
            }
        }
        return listResult;
    }
}
