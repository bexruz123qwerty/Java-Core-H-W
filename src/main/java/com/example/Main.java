package com.example;

import com.example.model.AirPlane;
import com.example.model.Passenger;
import com.example.model.Seat;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ✅ Ask for flight destination
        System.out.print("Enter flight destination: ");
        String destination = sc.nextLine();

        AirPlane plane = new AirPlane(destination);

        // ✅ Main menu
        while (true) {
            System.out.println("\n===========================");
            System.out.println("     AIR TICKET SYSTEM   ");
            System.out.println("===========================");
            System.out.println("1. Show all seats");
            System.out.println("2. Book a seat");
            System.out.println("3. Cancel a booking");
            System.out.println("4. Find seat by number");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> {
                    plane.showAllSeats();
                }

                case "2" -> {
                    System.out.print("Enter seat number (e.g. 12C): ");
                    String seatId = sc.nextLine().trim().toUpperCase();
                    System.out.print("First name: ");
                    String firstName = sc.nextLine().trim();
                    System.out.print("Last name: ");
                    String lastName = sc.nextLine().trim();

                    plane.bookSeat(seatId, new Passenger(firstName, lastName));
                }

                case "3" -> {
                    System.out.print("Enter seat number to cancel booking: ");
                    String seatId = sc.nextLine().trim().toUpperCase();
                    plane.cancelBook(seatId);
                }

                case "4" -> {
                    System.out.print("Enter seat number (e.g. 15A): ");
                    String seatId = sc.nextLine().trim().toUpperCase();
                    Seat seat = plane.getSeat(seatId);
                    if (seat != null) {
                        System.out.println(seat);
                    } else {
                        System.out.println("Seat not found!");
                    }
                }

                case "0" -> {
                    System.out.println("Exiting the program...");
                    return;
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
