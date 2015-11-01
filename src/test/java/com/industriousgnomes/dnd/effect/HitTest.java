package com.industriousgnomes.dnd.effect;

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

@RunWith(JMockit.class)
public class HitTest {

    @Mocked Dice mockDice;
    @Mocked Attack mockAttack;
	@Mocked PlayerCharacter mockAttacker;
	@Mocked PlayerCharacter mockDefender;
	
    @Test
    public void criticalACharacter() {
        
        HitResult hitResult = Hit.isHit(mockAttack, 20);
        
        assertEquals(HitResult.CRITICAL, hitResult);
    }

    @Test
    public void missACharacter_natural1() {
        HitResult hitResult = Hit.isHit(mockAttack, 1);
        
        assertEquals(HitResult.MISS, hitResult);
    }
    
    @Test
    public void missACharacter() {

    	new Expectations() {{
    	    mockAttack.get(Actor.ATTACKER); result = mockAttacker;
            mockAttack.get(Actor.DEFENDER); result = mockDefender;
    	    mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = 2;
    	    mockDefender.getFeatures().total(mockAttack, FeatureType.ARMOR_CLASS); result = 10;
    	}};
    	
    	HitResult hitResult = Hit.isHit(mockAttack, 7);
    	
    	assertEquals(HitResult.MISS, hitResult);
    }

    @Test
    public void hitACharacter() {

    	new Expectations() {{
            mockAttack.get(Actor.ATTACKER); result = mockAttacker;
            mockAttack.get(Actor.DEFENDER); result = mockDefender;
            mockAttacker.getFeatures().total(mockAttack, FeatureType.ATTACK_MODIFIER); result = 3;
            mockDefender.getFeatures().total(mockAttack, FeatureType.ARMOR_CLASS); result = 10;
    	}};
    	
    	HitResult hitResult = Hit.isHit(mockAttack, 7);
    	
    	assertEquals(HitResult.HIT, hitResult);
    }
}
