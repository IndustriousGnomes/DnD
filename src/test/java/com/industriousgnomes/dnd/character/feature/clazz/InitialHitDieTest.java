package com.industriousgnomes.dnd.character.feature.clazz;

import static com.industriousgnomes.dnd.Dice.D10;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

@RunWith(JMockit.class)
public class InitialHitDieTest {

	@Mocked PlayerCharacter mockedCharacter;
	@Mocked Dice mockedDice;
	
	HitDie hitDie;
	
	@Before
	public void setUp() {
		new Expectations() {{
		    D10.roll(); result = 6;
		    D10.sides(); result = 10;
		}};
		
		hitDie = new InitialHitDie(mockedCharacter, D10);
	}
	
	@Test
	public void checkRelevance() {
		assertTrue(hitDie.isRelevant(FeatureType.INITIAL_HIT_DIE));
		assertTrue(hitDie.isRelevant(FeatureType.HIT_DIE));
		assertTrue(hitDie.isRelevant(FeatureType.HIT_POINTS));
	}
	
	@Test
	public void checkExecute() {
		new Expectations() {{
			mockedCharacter.getFeatures().total(FeatureType.CONSTITUTION_ABILITY_MODIFIER); 
			result = 3;
		}};
		
		int aResult = hitDie.execute(FeatureType.HIT_DIE);
		
		assertEquals(13, aResult);
	}
}
