package com.example.task2.model;

import java.util.*;

public class Quiz {
    private String name;
    private List<Question> questions = new ArrayList<>();
    private List<Team> teams = new ArrayList<>();

    public Quiz(String name) { this.name = name; }
    public String getName() { return name; }

    public void addQuestion(Question q) { questions.add(q); }
    public void addTeam(Team t) { teams.add(t); }

    public List<Question> getQuestions() { return questions; }
    public List<Team> getTeams() { return teams; }

    public void printScores() {
        for (Team t : teams) {
            System.out.println(t.getName() + ": " + t.getScore() + " pts");
        }
    }

    public void printFinalResults() {
        System.out.println("\n--- Final Results for quiz: " + name + " ---");
        teams.sort(Comparator.comparingInt(Team::getScore).reversed());
        int place = 1;
        for (Team t : teams) {
            System.out.println(place + ") " + t.getName() + " — " + t.getScore() + " pts");
            place++;
        }
    }
}
