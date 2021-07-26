package com.taxisystem.Models;

public class Driver {
    private int id;
    private double longitude;
    private double latitude;

    public Driver(int id, double longitude, double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    @Override
    public String toString(){
        return "{\"id\":"+id + ",\"longitude\":"+longitude+",\"latitude\":"+latitude+"}";
    }
}
