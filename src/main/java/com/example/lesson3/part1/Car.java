package com.example.lesson3.part1;

public class Car {
    private final String country;
    private final String model;
    private final int yearOfManufacture;

    public Car(String country, String model, int yearOfManufacture) {
        this.country = country;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
    }

    public void printCarInfo() {
        System.out.println("Car: " + model + " (" + country + ", " + yearOfManufacture + ")");
    }
}

