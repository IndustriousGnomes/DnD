package com.industriousgnomes.dnd.character.feature.proficiency;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class SavingThrow extends Proficiency {

    public SavingThrow(PlayerCharacter character, Source source, FeatureType savingThrowType, FeatureType... relevancy) {

        super(character, source, savingThrowType, relevancy);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return character.getProficiencyBonus();
    }

}
