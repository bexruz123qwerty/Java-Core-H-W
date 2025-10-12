package com.example.model;

import java.time.LocalDateTime;

public class Seat {

    public enum Status {
        FREE, BOOKED, PAID
    }

    private final String id;
    private final String seatClass;
    private Passenger passenger;
    private Status status;
    private LocalDateTime bookingTime;

    public Seat(String id, String seatClass) {
        this.id = id;
        this.seatClass = seatClass;
        this.status = Status.FREE;
    }

    public String getId() { return id; }
    public String getSeatClass() { return seatClass; }
    public Passenger getPassenger() { return passenger; }
    public Status getStatus() { return status; }
    public LocalDateTime getBookingTime() { return bookingTime; }

    public boolean isBooked() {
        return status != Status.FREE;
    }

    public void book(Passenger passenger) {
        this.passenger = passenger;
        this.status = Status.BOOKED;
        this.bookingTime = LocalDateTime.now();
    }

    public void cancel() {
        this.passenger = null;
        this.status = Status.FREE;
        this.bookingTime = null;
    }

    public void setStatus(Status status) { this.status = status; }
    public void setBookingTime(LocalDateTime bookingTime) { this.bookingTime = bookingTime; }

    @Override
    public String toString() {
        String details = switch (status) {
            case FREE -> "Available";
            case BOOKED -> "Booked by " + passenger + " at " + bookingTime;
            case PAID -> "Paid by " + passenger;
        };
        return id + " (" + seatClass + ") — " + details;
    }
}
