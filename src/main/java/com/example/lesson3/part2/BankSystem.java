package com.example.lesson3.part2;

import java.util.ArrayList;
import java.util.List;

public class BankSystem {
    private final String accountNumber;
    private final String fio;
    private double balance;

    private static final List<BankSystem> accounts = new ArrayList<>();

    public BankSystem(String accountNumber, String fio, double balance) {
        this.accountNumber = accountNumber;
        this.fio = fio;
        this.balance = balance;
    }

    public String getAccountInfo() {
        return accountNumber + " - " + fio + " [" + balance + "]";
    }

    public static void addAccount(BankSystem account) {
        accounts.add(account);
    }

    public static void deleteAccount(String accountNumber) {
        accounts.removeIf(acc -> acc.accountNumber.equals(accountNumber));
    }

    public void replenishAccount(double amount) {
        balance += amount;
    }

    public static boolean transferMoneyBetweenAccounts(String fromAcc, String toAcc, double amount) {
        BankSystem from = accounts.stream()
                .filter(acc -> acc.accountNumber.equals(fromAcc))
                .findFirst().orElse(null);

        BankSystem to = accounts.stream()
                .filter(acc -> acc.accountNumber.equals(toAcc))
                .findFirst().orElse(null);

        if (from != null && to != null && from.balance >= amount) {
            from.balance -= amount;
            to.balance += amount;
            return true;
        }
        return false;
    }
}