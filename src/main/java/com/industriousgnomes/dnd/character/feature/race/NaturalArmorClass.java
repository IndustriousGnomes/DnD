package com.industriousgnomes.dnd.character.feature.race;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class NaturalArmorClass implements Feature {

    protected PlayerCharacter character;
    protected Integer         armorClass;

    public NaturalArmorClass(PlayerCharacter character, Integer armorClass) {
        this.character = character;
        this.armorClass = armorClass;
    }

    @Override
    public Source getSource() {
        return Source.CHARACTER_RACE;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.ARMOR_CLASS == featureType) || (FeatureType.NATURAL_ARMOR_CLASS == featureType);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return armorClass + character.getFeatures().total(FeatureType.DEXTERITY_ABILITY_MODIFIER);
    }
}
