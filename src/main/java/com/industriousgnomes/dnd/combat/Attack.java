package com.industriousgnomes.dnd.combat;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class Attack {

    public static HitResult isHit(PlayerCharacter attacker, PlayerCharacter defender, int roll) {
        if (roll == 20) {
            return HitResult.CRITICAL;
        }

        if (roll == 1) {
            return HitResult.MISS;
        }

        int hitBy = roll + attacker.getFeatures().total(FeatureType.ATTACK_MODIFIER) - defender.getFeatures().total(FeatureType.ARMOR_CLASS);

        if (hitBy >= 0) {
            return HitResult.HIT;
        }

        return HitResult.MISS;
    }
}
