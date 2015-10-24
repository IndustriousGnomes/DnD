package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.race.MovementSpeed;

public class Human extends CharacterRace {

    private static final String      RACE       = "Human";

    public Human(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.STRENGTH));
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.DEXTERITY));
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.INTELLIGENCE));
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.WISDOM));
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.CONSTITUTION));
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.CHARISMA));

        // Movement Speed
        features.add(new MovementSpeed(character, 30));
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
