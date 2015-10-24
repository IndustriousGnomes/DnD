package com.industriousgnomes.dnd.combat;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

@RunWith(JMockit.class)
public class AttackTest {

	@Mocked PlayerCharacter mockAttacker;
	@Mocked PlayerCharacter mockDefender;
	@Mocked Dice mockDice;
	
    @Test
    public void criticalACharacter() {
        HitResult hitResult = Attack.isHit(mockAttacker, mockDefender, 20);
        
        assertEquals(HitResult.CRITICAL, hitResult);
    }

    @Test
    public void missACharacter_natural1() {
        HitResult hitResult = Attack.isHit(mockAttacker, mockDefender, 1);
        
        assertEquals(HitResult.MISS, hitResult);
    }
    
    @Test
    public void missACharacter() {

    	new Expectations() {{
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = 2;
    		mockDefender.getFeatures().total(FeatureType.ARMOR_CLASS); result = 10;
    	}};
    	
    	HitResult hitResult = Attack.isHit(mockAttacker, mockDefender, 7);
    	
    	assertEquals(HitResult.MISS, hitResult);
    }

    @Test
    public void hitACharacter() {

    	new Expectations() {{
    		mockAttacker.getFeatures().total(FeatureType.ATTACK_MODIFIER); result = 3;
    		mockDefender.getFeatures().total(FeatureType.ARMOR_CLASS); result = 10;
    	}};
    	
    	HitResult hitResult = Attack.isHit(mockAttacker, mockDefender, 7);
    	
    	assertEquals(HitResult.HIT, hitResult);
    }
}
