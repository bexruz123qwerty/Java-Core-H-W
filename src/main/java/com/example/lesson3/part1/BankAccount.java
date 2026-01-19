package com.example.lesson3.part1;

public class BankAccount {
    private final String fio;
    private final String accountNumber;
    private double balance;

    public BankAccount(String fio, String accountNumber, double balance) {
        this.fio = fio;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Not enough money!");
            return false;
        }
        balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}
