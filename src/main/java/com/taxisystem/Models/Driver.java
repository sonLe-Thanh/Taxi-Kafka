package com.taxisystem.Models;

public class Driver {
    private int id;
    private double longitude;
    private double latitude;
    private int seat;

    public Driver(int id, double longitude, double latitude, int seat) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.seat = seat;
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

    @Override
    public String toString(){
        return id + ","+longitude+","+latitude + ","+ seat;
    }
}
