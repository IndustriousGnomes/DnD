package com.industriousgnomes.dnd.effect;

import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;

public class Hit {

    public static HitResult isHit(Attack action, int roll) {
        if (roll == 20) {
            return HitResult.CRITICAL;
        }

        if (roll == 1) {
            return HitResult.MISS;
        }

        Features attackerFeatures = ((PlayerCharacter)action.get(Actor.ATTACKER)).getFeatures();
        Features defenderFeatures = ((PlayerCharacter)action.get(Actor.DEFENDER)).getFeatures();
        // FIXME - Should be more compact
        int hitBy = roll 
                  + attackerFeatures.total(action, FeatureType.ATTACK_MODIFIER) 
                  + attackerFeatures.total(action, FeatureType.STRENGTH_ABILITY_MODIFIER) 
                  - defenderFeatures.total(action, FeatureType.ARMOR_CLASS)
                  - defenderFeatures.total(action, FeatureType.DEXTERITY_ABILITY_MODIFIER);

        if (hitBy >= 0) {
            return HitResult.HIT;
        }

        return HitResult.MISS;
    }
}
