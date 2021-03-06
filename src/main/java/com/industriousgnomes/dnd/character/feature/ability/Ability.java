package com.industriousgnomes.dnd.character.feature.ability;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class Ability implements Feature {

    protected PlayerCharacter       character;
    protected Integer               score;
    private Source                  source;
    private Collection<FeatureType> relivancy;

    public Ability(PlayerCharacter character, Integer score, Source source, FeatureType... relevancy) {
        this.character = character;
        this.score = score;
        this.source = source;
        this.relivancy = Arrays.asList(relevancy);
    }

    @Override
    public Source getSource() {
        return source;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.ABILITY == featureType) || relivancy.stream().anyMatch(r -> r == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Object execute(FeatureType featureType) {
        return score;
    }

}
