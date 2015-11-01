package com.industriousgnomes.dnd.action;

import static com.industriousgnomes.dnd.Dice.D20;
import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.effect.Damage;
import com.industriousgnomes.dnd.effect.DamageEffect;
import com.industriousgnomes.dnd.effect.Hit;
import com.industriousgnomes.dnd.effect.HitResult;

@RunWith(JMockit.class)
public class AttackTest {

    @Mocked Dice mockDice;
    @Mocked Hit mockHit;
    @Mocked DamageEffect mockDamageEffect;
    @Mocked Damage mockDamage;
    
    @Mocked PlayerCharacter mockAttacker;
    @Mocked PlayerCharacter mockDefender;
    
    Attack attack;
    
    @Before
    public void setUp() throws Exception {
        attack = new Attack(mockAttacker, mockDefender);
    }

    @Test
    public void testAttackSetup() {
        assertEquals(mockAttacker, attack.get(Actor.ATTACKER));
        assertEquals(mockDefender, attack.get(Actor.DEFENDER));
    }

    @Test
    public void attackWithAHit() {

        new Expectations() {{
            D20.roll(); result = 19;
            Hit.isHit((Attack)any, 19); result = HitResult.HIT;
            DamageEffect.determineDamage((Attack)any, HitResult.HIT); result = 8;
            Damage.applyDamage((Attack)any, 8);
        }};
        
        attack.execute();
    }

    @Test
    public void attackSequenceWithAMiss() {

        new Expectations() {{
            D20.roll(); result = 2;
            Hit.isHit((Attack)any, 2); result = HitResult.MISS;
        }};
        
        attack.execute();

        new Verifications() {{
            DamageEffect.determineDamage((Attack)any, (HitResult)any); times = 0;
            Damage.applyDamage((Attack)any, anyInt); times = 0;
        }};
    }

}
