package com.industriousgnomes.dnd.effect;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;

public class DamageEffect {

    public static int determineDamage(Attack action, HitResult hitResult) {
        int rolledDamage = Dice.die(10).roll(); // FIXME - based on weapon equipped

        Features attackerFeatures = ((PlayerCharacter)action.get(Actor.ATTACKER)).getFeatures();
        int damage = (rolledDamage * hitResult.damageMultiplier()) 
                   + attackerFeatures.total(action, FeatureType.ATTACK_MODIFIER);

        damage = Math.max(1, damage);
        
        return damage;
    }

}
