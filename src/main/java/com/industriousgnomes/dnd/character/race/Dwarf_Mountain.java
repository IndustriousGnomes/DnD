package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;

public class Dwarf_Mountain extends Dwarf {

    private static final String      SUB_RACE       = "Mountain Dwarf";

    public Dwarf_Mountain(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 2, Source.CHARACTER_RACE, FeatureType.CONSTITUTION));

        // Racial Combat Training
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.ARMOR, FeatureType.LIGHT_ARMOR));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.ARMOR, FeatureType.MEDIUM_ARMOR));
    }

    @Override
    public String getSubRace() {
        return SUB_RACE;
    }

}
