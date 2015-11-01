package com.industriousgnomes.dnd.character.feature.ability;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class BaseSkill implements Feature {

    protected PlayerCharacter character;
    private FeatureType       skill;
    private FeatureType       basedOnAbilityModifier;

    public BaseSkill(PlayerCharacter character, FeatureType skill, FeatureType basedOnAbilityModifier) {
        this.character = character;
        this.skill = skill;
        this.basedOnAbilityModifier = basedOnAbilityModifier;
    }

    @Override
    public Source getSource() {
        return Source.SKILL;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.SKILL == featureType) || (skill == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Object execute(FeatureType featureType) {
        return character.getFeatures().total(basedOnAbilityModifier);
    }

}
