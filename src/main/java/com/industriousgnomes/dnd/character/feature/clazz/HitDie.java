package com.industriousgnomes.dnd.character.feature.clazz;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class HitDie implements Feature {

    protected PlayerCharacter character;
    protected Integer         hitDieSides;
    protected Integer         hitPoints;

    public HitDie(PlayerCharacter character, Integer hitDieSides) {
        this.character = character;
        this.hitDieSides = hitDieSides;
        hitPoints = Dice.roll(hitDieSides);
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
    public Integer execute(FeatureType featureType) {
        int modifiedHitPoints = hitPoints + character.getFeatures().total(FeatureType.CONSTITUTION_ABILITY_MODIFIER);
        return Math.max(1, modifiedHitPoints);
    }

}
