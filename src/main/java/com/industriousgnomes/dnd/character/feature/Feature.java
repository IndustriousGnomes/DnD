package com.industriousgnomes.dnd.character.feature;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public interface Feature {

    public Source getSource();

    public Boolean isRelevant(FeatureType featureType);

    public Pair<GamePiece, Object> getLimitations();

    public Object execute(FeatureType featureType);

}
