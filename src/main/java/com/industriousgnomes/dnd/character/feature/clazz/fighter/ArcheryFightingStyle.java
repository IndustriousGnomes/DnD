package com.industriousgnomes.dnd.character.feature.clazz.fighter;

import com.industriousgnomes.dnd.character.feature.FeatureType;

public class ArcheryFightingStyle extends FightingStyle {

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.ATTACK_MODIFIER == featureType);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return 2;
    }

}
