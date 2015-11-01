package com.industriousgnomes.dnd.effect;

import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;

public class Damage {

    public static void applyDamage(Attack action, int damage) {

        ((PlayerCharacter)action.get(Actor.DEFENDER)).addDamageTaken(damage);
    }

}
