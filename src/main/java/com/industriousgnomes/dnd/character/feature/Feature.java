package com.industriousgnomes.dnd.character.feature;

import com.industriousgnomes.dnd.character.Source;

public interface Feature {

    public Source getSource();

    public Boolean isRelevant(FeatureType featureType);

    public Object execute(FeatureType featureType);

    // TODO public Collection<Limitation> getLimitations();
}
