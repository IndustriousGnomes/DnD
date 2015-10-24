package com.industriousgnomes.dnd.character.feature.clazz;

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
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;

@RunWith(JMockit.class)
public class HitDieTest {

	@Mocked PlayerCharacter mockedCharacter;
	@Mocked Dice mockedDice;
	
	HitDie hitDie;
	
	@Before
	public void setUp() {
		new Expectations() {{
			Dice.roll(anyInt); result = 6;
		}};
		
		hitDie = new HitDie(mockedCharacter, 10);
	}
	
	@Test
	public void checkSource() {
		assertEquals(Source.CHARACTER_CLASS, hitDie.getSource());
	}

	@Test
	public void checkRelevance() {
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
		
		assertEquals(9, aResult);
	}

	@Test
	public void checkExecuteWithMinimumHitPoints() {
		new Expectations() {{
			mockedCharacter.getFeatures().total(FeatureType.CONSTITUTION_ABILITY_MODIFIER); 
			result = -4;
			Dice.roll(anyInt); result = 1;
		}};
		
		hitDie = new HitDie(mockedCharacter, 10);

		int aResult = hitDie.execute(FeatureType.HIT_DIE);
		
		assertEquals(1, aResult);
	}
}
