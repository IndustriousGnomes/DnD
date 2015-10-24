package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.proficiency.Skill;
import com.industriousgnomes.dnd.character.feature.race.Darkvision;
import com.industriousgnomes.dnd.character.feature.race.MovementSpeed;

public class Elf extends CharacterRace {

    private static final String      RACE       = "Elf";

    public Elf(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 2, Source.CHARACTER_RACE, FeatureType.DEXTERITY));

        // Movement Speed
        features.add(new MovementSpeed(character, 30));

        // Racial Keen Senses
        features.add(new Skill(character, Source.CHARACTER_RACE, FeatureType.PERCEPTION));

        // TODO Racial advantage on save vs charmed
        // TODO Racial immune to sleep

        // Darkvision
        features.add(new Darkvision());
    }

    @Override
    public String getRace() {
        return RACE;
    }

    @Override
    public String getSubRace() {
        return RACE;
    }
}
