package com.venusdev.ecobi_c.model;

public class Station {

    int empty_slots;
    int free_bikes;
    double latitude;
    double longitude;
    String name;
    String timestamp;
    ExtraData extra;

    public int getEmpty_slots() {
        return empty_slots;
    }

    public void setEmpty_slots(int empty_slots) {
        this.empty_slots = empty_slots;
    }

    public int getFree_bikes() {
        return free_bikes;
    }

    public void setFree_bikes(int free_bikes) {
        this.free_bikes = free_bikes;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ExtraData getExtraData() {
        return extra;
    }

    public void setExtraData(ExtraData extraData) {
        this.extra = extraData;
    }
}