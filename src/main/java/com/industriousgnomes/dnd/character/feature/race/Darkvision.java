package com.industriousgnomes.dnd.character.feature.race;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class Darkvision implements Feature {

    public Darkvision() {
    }

    @Override
    public Source getSource() {
        return Source.CHARACTER_RACE;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.DARKVISION == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Object execute(FeatureType featureType) {
        return Boolean.TRUE;
    }
}
