package com.industriousgnomes.dnd.effect;

import static com.industriousgnomes.dnd.Dice.D10;
import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;

@RunWith(JMockit.class)
public class DamageEffectTest {

    @Mocked Dice mockDice;
    @Mocked Attack mockAttack;
	@Mocked PlayerCharacter mockAttacker;
	@Mocked PlayerCharacter mockDefender;
	@Mocked HitResult mockHitResult;
	@Mocked Features mockFeatures;

    @Test
    public void determineDamage() {
    	new Expectations() {{
    	    Dice.die(10); result = D10;
    	    D10.roll(); result = 8;
            mockHitResult.damageMultiplier(); result = 1;
    	    mockAttack.get(Actor.ATTACKER); result = mockAttacker;
    	    mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = 0;
    	}};
    	
        int damage = DamageEffect.determineDamage(mockAttack, HitResult.HIT);

        assertEquals(8, damage);
    }

    @Test
    public void damageACharacterWithAttackModifiers() {
    	new Expectations() {{
            Dice.die(10); result = D10;
            D10.roll(); result = 8;
            mockHitResult.damageMultiplier(); result = 1;
            mockAttack.get(Actor.ATTACKER); result = mockAttacker;
            mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = 4;
    	}};
    	
    	int damage = DamageEffect.determineDamage(mockAttack, HitResult.HIT);
    	
        assertEquals(12, damage);
    }

    @Test
    public void damageACharacterWithMinimumDamage() {        
    	new Expectations() {{
    	    Dice.die(10).roll(); result = 2;
            mockHitResult.damageMultiplier(); result = 1;
            mockAttack.get(Actor.ATTACKER); result = mockAttacker;
    		mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = -4;
    	}};
    	
        int damage = DamageEffect.determineDamage(mockAttack, HitResult.HIT);
        
        assertEquals(1, damage);
    }

    @Test
    public void criticalDamageACharacterDoesDoubleDamageAndAttackModifier() {        
        new Expectations() {{
            Dice.die(10).roll(); result = 8;
            mockHitResult.damageMultiplier(); result = 2;
            mockAttack.get(Actor.ATTACKER); result = mockAttacker;
            mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = 2;
        }};
        
        int damage = DamageEffect.determineDamage(mockAttack, HitResult.HIT);
        
        assertEquals(18, damage);
    }
}
