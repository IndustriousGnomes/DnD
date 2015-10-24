package com.industriousgnomes.dnd.combat;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

@RunWith(JMockit.class)
public class DamageTest {

	@Mocked PlayerCharacter mockAttacker;
	@Mocked PlayerCharacter mockDefender;
	@Mocked HitResult mockHitResult;

    @Test
    public void damageACharacter() {
    	new Expectations() {{
    		mockHitResult.damageMultiplier(); result = 1;
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = 0;
    	}};
    	
        Damage.applyDamage(mockAttacker, mockDefender, 8, HitResult.HIT);

        new Verifications() {{
        	int damage;
        	mockDefender.addDamageTaken(damage = withCapture());
        	assertEquals(8, damage);
        }};
    }

    @Test
    public void damageACharacterWithAttackModifiers() {
    	new Expectations() {{
    		mockHitResult.damageMultiplier(); result = 1;
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = 4;
    	}};
    	
        Damage.applyDamage(mockAttacker, mockDefender, 8, HitResult.HIT);

        new Verifications() {{
        	int damage;
        	mockDefender.addDamageTaken(damage = withCapture());
        	assertEquals(12, damage);
        }};
    }

    @Test
    public void damageACharacterWithMinimumDamage() {        
    	new Expectations() {{
    		mockHitResult.damageMultiplier(); result = 1;
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = -4;
    	}};
    	
        Damage.applyDamage(mockAttacker, mockDefender, 2, HitResult.HIT);

        new Verifications() {{
        	int damage;
        	mockDefender.addDamageTaken(damage = withCapture());
        	assertEquals(1, damage);
        }};
    }

    @Test
    public void criticalDamageACharacterDoesDoubleDamageAndAttackModifier() {        
    	new Expectations() {{
    		mockHitResult.damageMultiplier(); result = 2;
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = 2;
    	}};
    	
        Damage.applyDamage(mockAttacker, mockDefender, 8, HitResult.HIT);

        new Verifications() {{
        	int damage;
        	mockDefender.addDamageTaken(damage = withCapture());
        	assertEquals(18, damage);
        }};
    }
}
