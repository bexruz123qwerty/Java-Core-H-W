package com.example.model;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class AirPlane {

    private static final String BOOKINGS = "Bookings.txt";

    private final Map<String, Seat> seats = new TreeMap<>();
    private final String destination;

    public AirPlane(String destination) {
        this.destination = destination;
        loadBookings();
        initSeats();
    }

    private void initSeats() {
        char[] seatLetters = {'A', 'B', 'C', 'D', 'E', 'F'};
        for (int i = 1; i < 30; i++) {
            String seatClass = (i < 5) ? "First" : (i < 21 ? "Business" : "Economy");
            for (char c : seatLetters) {
                seats.put(i + "" + c, new Seat(i + "" + c, seatClass));
            }
        }
    }

    public void showAllSeats() {
        for (Seat s : seats.values()) {
            System.out.println(s);
        }
    }

    public void bookSeat(String id, Passenger passenger) {
        Seat seat = seats.get(id);
        if (seat == null) {
            System.out.println(" Seat " + id + " does not exist!");
            return;
        }

        if (seat.isBooked()) {
            System.out.println(" Seat " + id + " is already booked by another passenger!");
            return;
        }

        seat.book(passenger);
        saveBookings();
        System.out.println(" You booked seat: " + seat.getId());
    }


    public void cancelBook(String id) {
        Seat seat = seats.get(id);
        if (seat == null) {
            System.out.println("Seat " + id + " does not exist!");
            return;
        }
        if (!seat.isBooked()) {
            System.out.println("Seat is already free!");
            return;
        }
        seat.cancel();
        saveBookings();
        System.out.println("Booking canceled for seat " + id);
    }


    public Seat getSeat(String id) {
        return seats.get(id);
    }

    private void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS))) {
            for (Seat seat : seats.values()) {
                if (seat.isBooked()) {
                    Passenger p = seat.getPassenger();
                    writer.write(seat.getId() + ";" +
                            p.getFirstName() + ";" +
                            p.getLastName() + ";" +
                            seat.getSeatClass() + ";" +
                            destination);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving bookings: " + e.getMessage());
        }
    }

    private void loadBookings() {
        File file = new File(BOOKINGS);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 5) {
                    String seatId = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String dest = parts[4];
                    if (dest.equals(destination)) {
                        Seat seat = seats.get(seatId);
                        if (seat != null) {
                            seat.book(new Passenger(firstName, lastName));
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке брони: " + e.getMessage());
        }
    }
}
