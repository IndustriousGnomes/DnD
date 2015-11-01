package com.industriousgnomes.dnd.character.feature.clazz;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class InitialHitDie extends HitDie {

    public InitialHitDie(PlayerCharacter character, Dice hitDie) {
        super(character, hitDie);
        hitPoints = hitDie.sides();
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.INITIAL_HIT_DIE == featureType) || super.isRelevant(featureType);
    }
}
