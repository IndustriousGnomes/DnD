package com.industriousgnomes.dnd.character.feature.ability;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class SavingThrow implements Feature {

    PlayerCharacter character;
    FeatureType     savingThrowType;
    FeatureType     basedOnAbilityModifier;

    public SavingThrow(PlayerCharacter character, FeatureType savingThrowType, FeatureType basedOnAbilityModifier) {

        this.character = character;
        this.savingThrowType = savingThrowType;
        this.basedOnAbilityModifier = basedOnAbilityModifier;
    }

    @Override
    public Source getSource() {
        return Source.SAVING_THROW;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (savingThrowType == featureType);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return character.getFeatures().total(basedOnAbilityModifier);
    }

}
