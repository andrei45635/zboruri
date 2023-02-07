package com.example.zboruri.domain;

import java.time.LocalDateTime;

public class Flight extends Entity<Integer>{
    private long flightID;
    private String from;
    private String to;
    private LocalDateTime departure;
    private LocalDateTime arrival;
    private int seats;

    public Flight(int id, long flightID, String from, String to, LocalDateTime departure, LocalDateTime arrival, int seats) {
        super(id);
        this.flightID = flightID;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.arrival = arrival;
        this.seats = seats;
    }

    public long getFlightID() {
        return flightID;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public LocalDateTime getArrival() {
        return arrival;
    }

    public int getSeats() {
        return seats;
    }
}
