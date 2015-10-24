package com.industriousgnomes.dnd.combat;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class Damage {

    public static void applyDamage(PlayerCharacter attacker, PlayerCharacter defender, int rolledDamage, HitResult hitResult) {

        int damage = (rolledDamage * hitResult.damageMultiplier()) + attacker.getFeatures().total(FeatureType.ATTACK_MODIFIER);

        damage = Math.max(1, damage);

        defender.addDamageTaken(damage);
    }

}
