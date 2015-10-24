package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;

public class Dwarf_Hill extends Dwarf {

    private static final String      SUB_RACE       = "Hill Dwarf";

    public Dwarf_Hill(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.WISDOM));

        // Racial Toughness
        // TODO features.add(new HitDie...?(character, 1, Source.CHARACTER_RACE, Feature.HIT_POINTS,
        // Feature.HIT_POINTS_PER_LEVEL));
    }

    @Override
    public String getSubRace() {
        return SUB_RACE;
    }

}
