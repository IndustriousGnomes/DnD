package com.industriousgnomes.dnd.character.clazz;

import static com.industriousgnomes.dnd.Dice.D6;

import com.industriousgnomes.dnd.character.PlayerCharacter;

public class Commoner extends CharacterClass {

    public Commoner(PlayerCharacter character) {
        super(character);
        setHitDie(D6);
    }

    @Override
    public String getClassName() {
        return "Commoner";
    }

}
