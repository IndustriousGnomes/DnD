package com.industriousgnomes.dnd.action;

import static com.industriousgnomes.dnd.Dice.D20;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.effect.Damage;
import com.industriousgnomes.dnd.effect.DamageEffect;
import com.industriousgnomes.dnd.effect.Hit;
import com.industriousgnomes.dnd.effect.HitResult;

public class Attack extends Action {

    public Attack(PlayerCharacter attacker, PlayerCharacter defender) {
        put(Actor.ATTACKER, attacker);
        put(Actor.DEFENDER, defender);
    }

    @Override
    public void execute() {
        int attackRoll = D20.roll();
        HitResult hitResult = Hit.isHit(this, attackRoll);
        if (HitResult.MISS != hitResult) {
            successfulHit(hitResult);
        }
    }

    private void successfulHit(HitResult hitResult) {
        int damage = DamageEffect.determineDamage(this, hitResult);
        Damage.applyDamage(this, damage);
    }
}
