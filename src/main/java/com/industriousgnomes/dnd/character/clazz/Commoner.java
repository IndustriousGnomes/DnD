package com.industriousgnomes.dnd.character.clazz;

import com.industriousgnomes.dnd.character.PlayerCharacter;

public class Commoner extends CharacterClass {

    public Commoner(PlayerCharacter character) {
        super(character);
        setHitDiceSides(6);
    }

    @Override
    public String getClassName() {
        return "Commoner";
    }

}
