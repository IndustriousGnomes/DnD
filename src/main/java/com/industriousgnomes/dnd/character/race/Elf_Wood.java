package com.industriousgnomes.dnd.character.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;
import com.industriousgnomes.dnd.character.feature.race.MovementSpeed;

public class Elf_Wood extends Elf {

    private static final String      SUB_RACE       = "Wood Elf";

    public Elf_Wood(PlayerCharacter character) {
        super(character);

        // Ability Score Modification
        features.add(new Ability(character, 1, Source.CHARACTER_RACE, FeatureType.WISDOM));

        // Racial Combat Training
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.LONGSWORD));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.SHORTSWORD));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.LONGBOW));
        features.add(new Equipment(character, Source.CHARACTER_RACE, FeatureType.WEAPON, FeatureType.SHORTBOW));

        // Racial Movement Speed
        features.remove(FeatureType.MOVEMENT_SPEED);
        features.add(new MovementSpeed(character, 35));

        // TODO Racial Mask of the wild - hide in lightly obscured foliage
    }
    
    @Override
    public String getSubRace() {
        return SUB_RACE;
    }

}
