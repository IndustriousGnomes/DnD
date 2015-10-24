package com.industriousgnomes.dnd.character.clazz;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;

@RunWith(JMockit.class)
public class FighterTest {

    @Mocked PlayerCharacter mockCharacter;
    CharacterClass fighter;
    Features features = new Features();

	@Before
	public void setUp() {
		new Expectations() {{
			mockCharacter.getFeatures(); result = features;
		}};
		fighter = new Fighter(mockCharacter);
	}
	
	@Test
	public void checkHitDieSides() {		
		assertEquals(10, fighter.getHitDiceSides());
	}
	
	@Test
	public void checkWeaponProficiencies() {
		assertTrue(fighter.features.contains(FeatureType.SIMPLE_WEAPONS));
		assertTrue(fighter.features.contains(FeatureType.MARTIAL_WEAPONS));
		assertFalse(fighter.features.contains(FeatureType.QUARTERSTAFF));
	}

	@Test
	public void checkArmorProficiencies() {
		assertTrue(fighter.features.contains(FeatureType.LIGHT_ARMOR));
		assertTrue(fighter.features.contains(FeatureType.MEDIUM_ARMOR));
		assertTrue(fighter.features.contains(FeatureType.HEAVY_ARMOR));
		assertTrue(fighter.features.contains(FeatureType.SHIELD));
	}
	
	@Test
	public void checkSavingThrowProficiencies() {
		new Expectations() {{
			mockCharacter.getProficiencyBonus(); result = 2;
		}};
		
		assertEquals(2, fighter.features.total(FeatureType.STRENGTH_SAVING_THROW));
		assertEquals(0, fighter.features.total(FeatureType.DEXTERITY_SAVING_THROW));
		assertEquals(0, fighter.features.total(FeatureType.INTELLIGENCE_SAVING_THROW));
		assertEquals(0, fighter.features.total(FeatureType.WISDOM_SAVING_THROW));
		assertEquals(2, fighter.features.total(FeatureType.CONSTITUTION_SAVING_THROW));
		assertEquals(0, fighter.features.total(FeatureType.CHARISMA_SAVING_THROW));
    }
	
	@Test
	public void addSkill_valid() {
		fighter.addSkill(FeatureType.ACROBATICS);
		assertTrue(fighter.features.contains(FeatureType.ACROBATICS));
	}

	@Test
	public void addSkill_invalid() {
		fighter.addSkill(FeatureType.NATURE);
		assertFalse(fighter.features.contains(FeatureType.NATURE));
	}
}
