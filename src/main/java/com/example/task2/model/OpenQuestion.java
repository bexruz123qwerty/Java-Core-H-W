package com.example.task2.model;

public class OpenQuestion extends Question {
    private String correctAnswer;

    public OpenQuestion(String text, String correctAnswer) {
        super(text);
        this.correctAnswer = correctAnswer != null ? correctAnswer.trim().toLowerCase() : "";
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (answer == null) return false;
        return answer.trim().equalsIgnoreCase(correctAnswer);
    }

    @Override
    public String getCorrectAnswerDisplay() {
        return correctAnswer;
    }
}
