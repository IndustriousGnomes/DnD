package com.industriousgnomes.dnd.character.feature.clazz;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class InitialHitDie extends HitDie {

    public InitialHitDie(PlayerCharacter character, Integer hitDieSides) {
        super(character, hitDieSides);
        hitPoints = hitDieSides;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.INITIAL_HIT_DIE == featureType) || super.isRelevant(featureType);
    }
}
