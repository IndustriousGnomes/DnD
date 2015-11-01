package com.industriousgnomes.dnd.character.feature.clazz.fighter;

import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.add;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.item.WeaponType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class ArcheryFightingStyle extends FightingStyle {

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.ATTACK_MODIFIER == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return add(GamePiece.ATTACKER, add(GamePiece.WEAPON_TYPE, WeaponType.RANGED));
    }

    @Override
    public Object execute(FeatureType featureType) {
        return 2;
    }

}
