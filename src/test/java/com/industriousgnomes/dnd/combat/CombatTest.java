package com.industriousgnomes.dnd.combat;

import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;

@RunWith(JMockit.class)
public class CombatTest {
    
    @Mocked Dice mockDice;
    @Mocked Attack mockAttack;
    @Mocked Damage mockDamage;
    
    @Mocked PlayerCharacter attacker;
    @Mocked PlayerCharacter defender;

    @Test
    public void attackSequenceWithAHitThatDoesntKill() {

        new Expectations() {{
        	Dice.roll(20); result = 19;
            Attack.isHit((PlayerCharacter)any, (PlayerCharacter)any, 19); result = HitResult.HIT;
            Dice.roll(8); result = 8;
            Damage.applyDamage((PlayerCharacter)any, (PlayerCharacter)any, 8, HitResult.HIT);
            defender.isAlive(); result = true;
        }};
        
        Combat.attackSequence(attacker, defender);
        
        new Verifications() {{
        	attacker.addExperiencePoints(anyInt); times = 0;
        }};
    }

    @Test
    public void attackSequenceWithAMiss() {

        new Expectations() {{
            Dice.roll(20); result = 2;
            Attack.isHit((PlayerCharacter)any, (PlayerCharacter)any, 2); result = HitResult.MISS;
        }};
        
        Combat.attackSequence(attacker, defender);

        new Verifications() {{
        	Dice.roll(8); times = 0;
            Damage.applyDamage((PlayerCharacter)any, (PlayerCharacter)any, anyInt, (HitResult)any); times = 0;
        }};
    }

    @Test
    public void attackSequenceWithACriticalThatKills() {

        new Expectations() {{
        	Dice.roll(20); result = 20;
            Attack.isHit((PlayerCharacter)any, (PlayerCharacter)any, 20); result = HitResult.CRITICAL;
            Dice.roll(8); result = 8;
            Damage.applyDamage((PlayerCharacter)any, (PlayerCharacter)any, 8, HitResult.CRITICAL);
            defender.isAlive(); result = false;            
        }};
        
        Combat.attackSequence(attacker, defender);

        new Verifications() {{
        	attacker.addExperiencePoints(10);
        }};
    }
}
