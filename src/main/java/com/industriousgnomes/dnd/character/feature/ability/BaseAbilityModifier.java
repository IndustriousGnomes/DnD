package com.industriousgnomes.dnd.character.feature.ability;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class BaseAbilityModifier implements Feature {

    protected static final int MODIFIER[] = { -5, -4, -4, -3, -3, -2, -2, -1, -1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10 };
    protected PlayerCharacter  character;
    private FeatureType        abilityModifier;
    private FeatureType        ability;

    public BaseAbilityModifier(PlayerCharacter character, FeatureType abilityModifier, FeatureType ability) {

        this.character = character;
        this.abilityModifier = abilityModifier;
        this.ability = ability;
    }

    @Override
    public Source getSource() {
        return Source.ABILITY_MODIFIER;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (abilityModifier == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Object execute(FeatureType featureType) {
        int sum = Math.max(1, character.getFeatures().total(ability));
        return MODIFIER[sum - 1];

    }
}
