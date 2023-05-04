package co.develhope.customqueries1.entities;

import java.util.Random;

public enum FlightStatus {
    ON_TIME,
    DELAYED,
    CANCELLED;


    public static FlightStatus randomFlightStatus(){
        Random random = new Random();
        FlightStatus[] flightStatus = values();
        return flightStatus[random.nextInt(flightStatus.length)];
    }
}