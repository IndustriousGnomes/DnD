package com.industriousgnomes.dnd.character.feature.clazz.fighter;

import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public abstract class FightingStyle implements Feature {

    public FightingStyle() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Source getSource() {
        return Source.CHARACTER_CLASS;
    }

    @Override
    public abstract Boolean isRelevant(FeatureType featureType);

    @Override
    public abstract Object execute(FeatureType featureType);
}
