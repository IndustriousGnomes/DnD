package com.industriousgnomes.dnd.character.feature.clazz;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class HitDie implements Feature {

    protected PlayerCharacter character;
    protected Dice            hitDie;
    protected Integer         hitPoints;

    public HitDie(PlayerCharacter character, Dice hitDie) {
        this.character = character;
        this.hitDie = hitDie;
        hitPoints = this.hitDie.roll();
    }

    @Override
    public Source getSource() {
        return Source.CHARACTER_CLASS;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.HIT_DIE == featureType) || (FeatureType.HIT_POINTS == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Integer execute(FeatureType featureType) {
        int modifiedHitPoints = hitPoints + character.getFeatures().total(FeatureType.CONSTITUTION_ABILITY_MODIFIER);
        return Math.max(1, modifiedHitPoints);
    }

}
