package com.example.model;

public class Seat {
    private final String id;
    private final String seatClass;
    private Passenger passenger;

    public Seat(String id, String seatClass) {
        this.id = id;
        this.seatClass = seatClass;
        this.passenger = null;
    }

    public String getId() {
        return id;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public boolean isBooked(){return passenger != null;}

    public void book(Passenger passenger){this.passenger = passenger;}

    public void cancel(){this.passenger = null;}

    @Override
    public String toString() {
        String status = isBooked()
                ? "BOOKED (" + passenger + ")"
                : "AVAILABLE";
        return id + " (" + seatClass + ") — " + status;
    }
}
