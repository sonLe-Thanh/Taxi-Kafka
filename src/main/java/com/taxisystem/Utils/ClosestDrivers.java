package com.taxisystem.Utils;

import com.taxisystem.Models.Driver;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import com.taxisystem.Models.ORSMTable;

public class ClosestDrivers {
    private static final String mapServerAdd = "http://127.0.0.1:5000";

    private static void executeAPICall(List<Driver> driverList, double longitude, double latitude) throws IOException, InterruptedException {
        StringBuilder prepareParams = new StringBuilder();
        prepareParams.append(longitude).append(",").append(latitude).append(";");
        long lenList = driverList.size();
        for (long i=0; i<lenList;i++){
            if (i<lenList-1){
                Driver currDriver = driverList.get((int) i);
                prepareParams.append(currDriver.getLongitude()).append(",").append(currDriver.getLatitude()).append(";");
            }
            else {
                Driver currDriver = driverList.get((int) i);
                prepareParams.append(currDriver.getLongitude()).append(",").append(currDriver.getLatitude()).append("?");
            }
        }
        prepareParams.append("sources=0");
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(
                URI.create(mapServerAdd+"/table/v1/driving/"+prepareParams)).build();
        var response = client.send(request, new JsonBodyHandler<>(ORSMTable.class));
//        URLConnection connection = new URL(mapServerAdd+"/table/v1/driving/"+prepareParams).openConnection();

    }
    public static List<Driver> getClosestDriver(List<Driver> driverList, double latitude, double longitude, int k){
        if (driverList == null){
            return null;
        }
        int n = driverList.size();
        List<Driver> listResult = new LinkedList<Driver>();
        double [] listDistance = new double[n];
        // Call API from map server


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
