package com.taxisystem.Utils;

import com.taxisystem.Models.Driver;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class ClosestDrivers {
    private static final String mapServerAdd = "http://127.0.0.1:5000";
    private static final String tableService = "/table/v1/driving/";

    private static String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int chr;
        while ((chr = reader.read())!=-1){
            stringBuilder.append((char) chr);
        }
        return stringBuilder.toString();
    }

    private static JSONObject readJsonFromUrl (URL url) throws IOException {
        try (InputStream inputStream = url.openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonText = readAll(reader);
            return new JSONObject(jsonText);
        }
    }
    private static JSONObject executeAPICall(List<Driver> driverList, double longitude, double latitude) throws IOException {

        //Params prepare
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


        //Do real things
        URL url = new URL(mapServerAdd+tableService+ prepareParams);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        JSONObject responseInfo;
        conn.connect();
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) throw new RuntimeException("HttpResponseCode: "+ responseCode);
        else {
            responseInfo = readJsonFromUrl(url);
        }
        return responseInfo;
    }


    public static List<Driver> getClosestDriver(List<Driver> driverList, double latitude, double longitude, int k) throws IOException {
        if (driverList == null){
            return null;
        }
        int n = driverList.size();
        List<Driver> listResult = new LinkedList<Driver>();
        // Call API from map server
        JSONObject response = executeAPICall(driverList, longitude, latitude);
//        String responseString = response.toString();
        JSONArray durations = response.getJSONArray("durations");
        JSONArray destinations = response.getJSONArray("durations");
        ArrayList<Integer> kClosest = new ArrayList<>();
        if (durations.length() <= 5){
            for (int i=0;i<durations.length();i++){
                kClosest.add(i);
            }
        }
        else {
            kClosest = KSmallest.getKSmallestIndecies(durations, k);
        }
        //Have index of these drivers
        int lenKClosest = kClosest.toArray().length;
        for (int i=0; i<lenKClosest;i++){
            listResult.add(driverList.get(kClosest.get(i)));
        }
        return listResult;
    }
}
