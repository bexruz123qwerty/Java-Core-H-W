package com.example.task2.model;

import java.util.*;

public class QuizManager {
    private Scanner scanner = new Scanner(System.in);
    private Quiz currentQuiz = null;

    public void run() {
        while (true) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": createQuiz(); break;
                case "2": addQuestion(); break;
                case "3": addTeam(); break;
                case "4": startQuiz(); break;
                case "5": showResults(); break;
                case "0": System.out.println("Exit. Goodbye!"); return;
                default: System.out.println("Invalid choice, try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n=== Quiz Menu ===");
        System.out.println("1. Create new quiz");
        System.out.println("2. Add question");
        System.out.println("3. Add team");
        System.out.println("4. Start quiz");
        System.out.println("5. Show results");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private void createQuiz() {
        System.out.print("Quiz name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Untitled Quiz";
        currentQuiz = new Quiz(name);
        System.out.println("Quiz '" + name + "' created.");
    }

    private void addQuestion() {
        ensureQuizExists();
        System.out.print("Question text: ");
        String text = scanner.nextLine().trim();
        System.out.print("Question type (1 - Multiple choice, 2 - Open answer): ");
        String t = scanner.nextLine().trim();
        if (t.equals("1")) {
            List<String> options = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                System.out.print("Option " + (char)('A'+i) + ": ");
                String opt = scanner.nextLine().trim();
                options.add(opt);
            }
            System.out.print("Correct option (A-D): ");
            String correct = scanner.nextLine().trim().toUpperCase();
            if (correct.isEmpty() || "ABCD".indexOf(correct.charAt(0)) == -1) {
                System.out.println("Invalid input. Question not added.");
                return;
            }
            Question q = new MultipleChoiceQuestion(text, options, correct.charAt(0)-'A');
            currentQuiz.addQuestion(q);
            System.out.println("Question added (multiple choice).");
        } else {
            System.out.print("Correct answer (string, case-insensitive): ");
            String correct = scanner.nextLine().trim();
            Question q = new OpenQuestion(text, correct);
            currentQuiz.addQuestion(q);
            System.out.println("Question added (open answer).");
        }
    }

    private void addTeam() {
        ensureQuizExists();
        System.out.print("Team name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Team name cannot be empty.");
            return;
        }
        Team team = new Team(name);
        currentQuiz.addTeam(team);
        System.out.println("Team '" + name + "' added.");
    }

    private void startQuiz() {
        ensureQuizExists();
        if (currentQuiz.getQuestions().isEmpty()) {
            System.out.println("No questions in this quiz. Add questions first.");
            return;
        }
        if (currentQuiz.getTeams().isEmpty()) {
            System.out.println("No teams added. Add teams first.");
            return;
        }

        System.out.println("\n=== Starting quiz: " + currentQuiz.getName() + " ===");
        System.out.println("Rules: each team answers in turn. Correct answer gives +1 point.");
        System.out.println("Press Enter to begin...");
        scanner.nextLine();

        List<Question> questions = currentQuiz.getQuestions();
        List<Team> teams = currentQuiz.getTeams();

        for (int qi = 0; qi < questions.size(); qi++) {
            Question q = questions.get(qi);
            System.out.println("\nQuestion " + (qi+1) + ":");
            System.out.println(q.getText());
            if (q instanceof MultipleChoiceQuestion) {
                List<String> opts = ((MultipleChoiceQuestion) q).getOptions();
                for (int i = 0; i < opts.size(); i++) {
                    System.out.println((char)('A'+i) + ". " + opts.get(i));
                }
            }

            for (Team team : teams) {
                System.out.println("\nTeam: " + team.getName() + ". Enter your answer (or leave empty to skip): ");
                String ans = scanner.nextLine().trim();
                if (ans.isEmpty()) {
                    System.out.println("Skipped.");
                    continue;
                }
                boolean correct = q.checkAnswer(ans);
                if (correct) {
                    team.addScore(1);
                    System.out.println("Correct! +1 point.");
                } else {
                    System.out.println("Wrong. Correct answer: " + q.getCorrectAnswerDisplay());
                }
            }
            System.out.println("--- Current Scores ---");
            currentQuiz.printScores();
            System.out.println("Press Enter for next question...");
            scanner.nextLine();
        }

        System.out.println("\n=== Quiz Finished ===");
        showResults();
    }

    private void showResults() {
        if (currentQuiz == null) {
            System.out.println("No quiz created.");
            return;
        }
        currentQuiz.printFinalResults();
    }

    private void ensureQuizExists() {
        if (currentQuiz == null) {
            System.out.println("No quiz created. Please create a quiz first.");
            throw new IllegalStateException("Quiz not created");
        }
    }
}
