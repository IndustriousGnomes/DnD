package com.industriousgnomes.dnd.character.clazz;

import static com.industriousgnomes.dnd.Dice.D10;

import java.util.Arrays;
import java.util.Collection;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;
import com.industriousgnomes.dnd.character.feature.proficiency.SavingThrow;

public class Fighter extends CharacterClass {

    public static final String      CLASS_NAME       = "Fighter";

    private Collection<FeatureType> skillLimitations = Arrays.asList(FeatureType.ACROBATICS, FeatureType.ANIMAL_HANDLING, FeatureType.ATHLETICS, FeatureType.HISTORY, FeatureType.INSIGHT, FeatureType.INTIMIDATION, FeatureType.PERCEPTION,
                                                             FeatureType.SURVIVAL);

    public Fighter(PlayerCharacter character) {
        super(character);
        setHitDie(D10);

        // Armor
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.ARMOR, FeatureType.LIGHT_ARMOR));
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.ARMOR, FeatureType.MEDIUM_ARMOR));
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.ARMOR, FeatureType.HEAVY_ARMOR));
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.ARMOR, FeatureType.SHIELD));

        // Weapons
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.WEAPON, FeatureType.SIMPLE_WEAPONS));
        addFeature(new Equipment(character, Source.CHARACTER_CLASS, FeatureType.WEAPON, FeatureType.MARTIAL_WEAPONS));

        // Tools
        // None

        // Saving Throws
        addFeature(new SavingThrow(character, Source.CHARACTER_CLASS, FeatureType.STRENGTH_SAVING_THROW));
        addFeature(new SavingThrow(character, Source.CHARACTER_CLASS, FeatureType.CONSTITUTION_SAVING_THROW));

    }

    @Override
    public String getClassName() {
        return CLASS_NAME;
    }

    @Override
    public void addSkill(FeatureType skill) {
        if (skillLimitations.contains(skill)) {
            super.addSkill(skill);
        }
    }

}
