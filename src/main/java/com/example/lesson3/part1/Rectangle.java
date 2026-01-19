package com.example.lesson3.part1;

public class Rectangle {
    private final double length;
    private final double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double calculateSquare() {
        return length * width;
    }

    public double calculatePerimeter() {
        return 2 * (length + width);
    }
}

