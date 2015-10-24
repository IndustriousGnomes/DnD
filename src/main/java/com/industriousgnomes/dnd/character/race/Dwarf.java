package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;
import com.industriousgnomes.dnd.character.feature.race.Darkvision;
import com.industriousgnomes.dnd.character.feature.race.MovementSpeed;

public class Dwarf extends CharacterRace {

    private static final String      RACE       = "Dwarf";

    public Dwarf(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 2, Source.CHARACTER_RACE, FeatureType.CONSTITUTION));

        // Movement Speed
        features.add(new MovementSpeed(character, 25));
        // TODO Speed not reduced by wearing heavy armor

        // TODO advantage on save vs poison
        // TODO resistance vs poison damage

        // Racial Combat Training
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.BATTLEAXE));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.HANDAXE));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.LIGHT_HAMMER));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.WARHAMMER));

        // TODO Choice on type of tool

        // TODO Sub-type of proficiency check History on Stonework

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
