package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;

public class Elf_High extends Elf {

    private static final String      SUB_RACE       = "High Elf";

    public Elf_High(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.INTELLIGENCE));

        // Racial Combat Training
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.LONGSWORD));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.SHORTSWORD));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.LONGBOW));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.SHORTBOW));

        // Racial Cantrip
    }
    
    @Override
    public String getSubRace() {
        return SUB_RACE;
    }

}
