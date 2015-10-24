package com.industriousgnomes.dnd.combat;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;

public class Combat {

    public static void attackSequence(PlayerCharacter attacker, PlayerCharacter defender) {
        int attackRoll = Dice.roll(20);
        HitResult hitResult = Attack.isHit(attacker, defender, attackRoll);
        if (HitResult.MISS != hitResult) {
            processSuccessfulAttack(attacker, defender, hitResult);
        }
    }

    private static void processSuccessfulAttack(PlayerCharacter attacker, PlayerCharacter defender, HitResult hitResult) {

        int damageRoll = Dice.roll(8);
        Damage.applyDamage(attacker, defender, damageRoll, hitResult);

        if (!defender.isAlive()) {
            attacker.addExperiencePoints(10);
        }
    }
}
