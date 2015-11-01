package com.industriousgnomes.dnd.character.feature.clazz.fighter;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public abstract class FightingStyle implements Feature {

    @Override
    public Source getSource() {
        return Source.CHARACTER_CLASS;
    }

    @Override
    public abstract Boolean isRelevant(FeatureType featureType);

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public abstract Object execute(FeatureType featureType);
}
