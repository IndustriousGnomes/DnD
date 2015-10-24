package com.industriousgnomes.dnd.character.feature.proficiency;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class Equipment extends Proficiency {

    /**
     * @param equipmentType
     *            Armor | Weapon
     */
    public Equipment(PlayerCharacter character, Source source, FeatureType equipmentType, FeatureType... relevancy) {
        super(character, source, equipmentType, relevancy);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return Boolean.TRUE;
    }
}
