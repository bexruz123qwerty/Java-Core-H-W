package com.example.task2.model;

import java.util.*;

public class MultipleChoiceQuestion extends Question {
    private List<String> options;
    private int correctIndex;

    public MultipleChoiceQuestion(String text, List<String> options, int correctIndex) {
        super(text);
        this.options = new ArrayList<>(options);
        this.correctIndex = correctIndex;
    }

    @Override
    public boolean checkAnswer(String answer) {
        if (answer == null) return false;
        answer = answer.trim().toUpperCase();
        if (answer.isEmpty()) return false;
        char ch = answer.charAt(0);
        int idx = ch - 'A';
        return idx == correctIndex;
    }

    @Override
    public String getCorrectAnswerDisplay() {
        return (char)('A' + correctIndex) + ". " + options.get(correctIndex);
    }

    public List<String> getOptions() { return options; }
}
