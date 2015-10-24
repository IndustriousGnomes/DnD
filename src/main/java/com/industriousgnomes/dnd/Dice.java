package com.industriousgnomes.dnd;

public class Dice {

    public static int roll(int dieSides) {
        // TODO use the Random class instead of Math.random()
        return (int) (Math.random() * dieSides) + 1;
    }
}

// TODO Think about making the die rolling routine an ENUM to do d20.roll(2). Brandon's idea
