package com.example.task2.model;

public abstract class Question {
    protected String text;

    public Question(String text) {
        this.text = text;
    }

    public String getText() { return text; }

    public abstract boolean checkAnswer(String answer);
    public abstract String getCorrectAnswerDisplay();
}
