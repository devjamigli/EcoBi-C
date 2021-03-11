package com.venusdev.ecobi_c.model;

import java.util.ArrayList;

public class DataResponse {

    Location location;
    ArrayList<Station> stations;
    String name;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Station> getStations() {
        return stations;
    }

    public void setStations(ArrayList<Station> stations) {
        this.stations = stations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
