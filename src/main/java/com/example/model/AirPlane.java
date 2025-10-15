package com.example.model;

import java.io.*;
import java.time.*;
import java.util.*;

public class AirPlane {

    private static final String BOOKINGS = "Bookings.txt";
    private static final long BOOKING_VALIDITY_MINUTES = 24; // 24 minutes

    private final Map<String, Seat> seats = new TreeMap<>();
    private final String destination;
    private final LocalDateTime departureDateTime;

    public AirPlane(String destination, LocalDateTime departureDateTime) {
        this.destination = destination;
        this.departureDateTime = departureDateTime;
        LocalDateTime createdAt = LocalDateTime.now();
        initSeats();
        loadBookings();
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
        clearExpiredBookings();
        for (Seat s : seats.values()) {
            System.out.println(s);
        }
    }

    public Seat getSeat(String id) {
        return seats.get(id);
    }

    public void bookSeat(String id, Passenger passenger) {
        Seat seat = seats.get(id);
        if (seat == null) {
            System.out.println("Seat " + id + " does not exist!");
            return;
        }

        if (seat.isBooked()) {
            System.out.println("Seat " + id + " is already booked!");
            return;
        }

        seat.book(passenger);
        saveBookings();
        System.out.println("You booked seat: " + seat.getId());
    }

    public void cancelBook(String id) {
        Seat seat = seats.get(id);
        if (seat == null) {
            System.out.println("Seat " + id + " does not exist!");
            return;
        }
        if (seat.getStatus() == Seat.Status.FREE) {
            System.out.println("Seat is already free!");
            return;
        }
        seat.cancel();
        saveBookings();
        System.out.println("Booking canceled for seat " + id);
    }

    private void clearExpiredBookings() {
        LocalDateTime now = LocalDateTime.now();
        for (Seat seat : seats.values()) {
            if (seat.getStatus() == Seat.Status.BOOKED &&
                    seat.getBookingTime() != null &&
                    Duration.between(seat.getBookingTime(), now).toMinutes() > BOOKING_VALIDITY_MINUTES) {
                seat.cancel();
                System.out.println("Booking expired for seat: " + seat.getId());
            }
        }
        saveBookings();
    }

    private void saveBookings() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKINGS))) {
            for (Seat seat : seats.values()) {
                if (seat.getStatus() != Seat.Status.FREE) {
                    Passenger p = seat.getPassenger();
                    writer.write(seat.getId() + ";" +
                            p.getFirstName() + ";" +
                            p.getLastName() + ";" +
                            seat.getSeatClass() + ";" +
                            destination + ";" +
                            departureDateTime + ";" +
                            seat.getStatus() + ";" +
                            (seat.getBookingTime() != null ? seat.getBookingTime() : ""));
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
                if (parts.length >= 8) {
                    String seatId = parts[0];
                    String firstName = parts[1];
                    String lastName = parts[2];
                    String dest = parts[4];
                    LocalDateTime flightDate = LocalDateTime.parse(parts[5]);
                    Seat.Status status = Seat.Status.valueOf(parts[6]);
                    LocalDateTime bookingTime = parts[7].isEmpty() ? null : LocalDateTime.parse(parts[7]);

                    if (dest.equals(destination) && flightDate.equals(departureDateTime)) {
                        Seat seat = seats.get(seatId);
                        if (seat != null) {
                            seat.book(new Passenger(firstName, lastName));
                            seat.setStatus(status);
                            seat.setBookingTime(bookingTime);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading bookings: " + e.getMessage());
        }
    }
}
