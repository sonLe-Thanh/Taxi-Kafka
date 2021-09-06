package com.taxisystem.Models;

public class Driver {
    private int id;
    private double longitude;
    private double latitude;
    private int seat;
    private String hexAdd;

    public Driver(int id, double longitude, double latitude, int seat) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.seat = seat;
        this.hexAdd = "";
    }

    public Driver(int id, double longitude, double latitude, int seat, String hexAdd) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.seat = seat;
        this.hexAdd = hexAdd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getHexAdd(){ return this.hexAdd;}
    public void setHexAdd(String hexAdd) {
        this.hexAdd = hexAdd;
    }

    @Override
    public String toString(){
        if (hexAdd.length() > 10){
            return id + ","+longitude+","+latitude+","+seat+","+hexAdd;
        }
        else {
            return id + ","+longitude+","+latitude+","+seat;
        }
    }
}
