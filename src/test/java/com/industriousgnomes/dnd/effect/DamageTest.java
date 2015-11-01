package com.industriousgnomes.dnd.effect;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;

@RunWith(JMockit.class)
public class DamageTest {

    @Mocked Attack mockAttack;
	@Mocked PlayerCharacter mockDefender;

    @Test
    public void damageACharacter() {
    	new Expectations() {{
    	    mockAttack.get(Actor.DEFENDER); result = mockDefender;
    	}};
    	
        Damage.applyDamage(mockAttack, 8);

        new Verifications() {{
        	int damage;
        	mockDefender.addDamageTaken(damage = withCapture());
        	assertEquals(8, damage);
        }};
    }
}
