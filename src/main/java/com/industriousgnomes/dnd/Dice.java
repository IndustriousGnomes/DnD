package com.industriousgnomes.dnd;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum Dice {
    D20 (20),
    D12 (12),
    D10 (10),
    D8  (8),
    D6  (6),
    D4  (4),
    D2  (2);
    
    private static Random random = new Random();

    private static Map<Integer, Dice> lookup = new HashMap<>();
 
    private final int sides;

    static {
        for (Dice d : Dice.values()) {
            lookup.put(d.sides, d);
        }
    }
    
    Dice(int sides) {
        this.sides = sides;
    }
    
    public int roll() {
        return roll(1);
    }

    public int roll(int numberOfRolls) {
        int roll = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            int nextInt = random.nextInt(sides);
            System.err.println("random: " + random + "; sides: " + sides + "; nextInt: " + nextInt);
            roll += nextInt + 1;
        }
        return roll;
    }

    public int roll(int numberOfRolls, int reroll) {
        int rollTotal = 0;
        int roll = 0;
        int count = 0;
        do {
            roll = roll(1);
            if (roll > reroll) {
                rollTotal += roll;
                count++;
            }
        } while (count < numberOfRolls);

        return rollTotal;
    }
    
    public int sides() {
        return sides;
    }
    
    public static Dice die(int dieSize) {
        return lookup.get(dieSize);
    }
}
