package com.example.task2.model;

public class Team {
    private final String name;
    private int score = 0;

    public Team(String name) { this.name = name; }
    public String getName() { return name; }
    public int getScore() { return score; }
    public void addScore(int delta) { score += delta; }
}
