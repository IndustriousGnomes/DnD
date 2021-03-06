package com.industriousgnomes.dnd.character.feature.proficiency;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public abstract class Proficiency implements Feature {

    protected PlayerCharacter         character;
    protected Source                  source;
    protected FeatureType             proficiencyType;
    protected Collection<FeatureType> relivancy;

    public Proficiency(PlayerCharacter character, Source source, FeatureType proficiencyType, FeatureType... relevancy) {
        this.character = character;
        this.source = source;
        this.proficiencyType = proficiencyType;
        this.relivancy = Arrays.asList(relevancy);
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.PROFICIENCY == featureType) || (proficiencyType == featureType) || relivancy.stream().anyMatch(r -> r == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public abstract Object execute(FeatureType featureType);
}
