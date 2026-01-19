package com.example.lesson3.streetFighter;

import java.util.Random;

public class StreetFighter {
    private int code;
    private String name;
    private int health;
    private int attack;

    public StreetFighter(int code, String name, int health, int attack) {
        this.code = code;
        this.name = name;
        this.health = health;
        this.attack = attack;
    }

    public String getFighterInfo(){
        return "Code: " + code + ", Name: " + name + ", Health: " + health + ", Attack: " + attack;
    }

    public static void fight(StreetFighter f1, StreetFighter f2) {
        Random random = new Random();
        boolean f1Starts = random.nextBoolean();

        System.out.println("The fighters enter the arena!");
        System.out.println(f1.getFighterInfo());
        System.out.println(f2.getFighterInfo());
        System.out.println("Randomly deciding who goes first...");

        System.out.println((f1Starts ? f1.name : f2.name) + " starts the fight!");

        while (f1.health > 0 && f2.health > 0) {
            if (f1Starts) {
                f2.health -= f1.attack;
                System.out.println(f1.name + " attacks " + f2.name + " for " + f1.attack + " damage.");
                if (f2.health <= 0) break;
                f1.health -= f2.attack;
                System.out.println(f2.name + " attacks " + f1.name + " for " + f2.attack + " damage.");
            } else {
                f1.health -= f2.attack;
                System.out.println(f2.name + " attacks " + f1.name + " for " + f2.attack + " damage.");
                if (f1.health <= 0) break;
                f2.health -= f1.attack;
                System.out.println(f1.name + " attacks " + f2.name + " for " + f1.attack + " damage.");
            }
        }

        if (f1.health <= 0 && f2.health <= 0) {
            System.out.println("Both fighters are down! It's a draw!");
        } else if (f1.health <= 0) {
            System.out.println(f2.name + " WINS!");
        } else {
            System.out.println(f1.name + " WINS!");
        }
    }

    public static StreetFighter[] getFighters() {
        StreetFighter[] fighters = new StreetFighter[2];
        fighters[0] = new StreetFighter(1, "Ryu", 100, 20);
        fighters[1] = new StreetFighter(2, "Ken", 100, 20);
        return fighters;
    }

    public static void main(String[] args) {
        StreetFighter[] fighters = StreetFighter.getFighters();
        StreetFighter.fight(fighters[0], fighters[1]);
    }
}
