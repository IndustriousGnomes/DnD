package com.industriousgnomes.dnd.character.feature.proficiency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import mockit.Mocked;

import org.junit.Before;
import org.junit.Test;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class WeaponTest {

	@Mocked PlayerCharacter mockCharacter;
	Feature weapon;

	@Before
	public void setUp() {
		weapon = new Equipment(mockCharacter, Source.CHARACTER_CLASS, FeatureType.WEAPON, FeatureType.SIMPLE_WEAPONS, FeatureType.MARTIAL_WEAPONS);
	}
	
	@Test
	public void checkSource() {
		assertEquals(Source.CHARACTER_CLASS, weapon.getSource());
	}

	@Test
	public void checkRelevancy_PROFICIENCY() {
		assertTrue(weapon.isRelevant(FeatureType.PROFICIENCY));
	}

	@Test
	public void checkRelevancy_WEAPON() {
		assertTrue(weapon.isRelevant(FeatureType.WEAPON));
	}

	@Test
	public void checkRelevancy_WEAPONTypes() {
		assertTrue(weapon.isRelevant(FeatureType.SIMPLE_WEAPONS));
		assertTrue(weapon.isRelevant(FeatureType.MARTIAL_WEAPONS));
	}
}
