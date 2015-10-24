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

public class ArmorTest {

	@Mocked PlayerCharacter mockCharacter;
	Feature armor;

	@Before
	public void setUp() {
		armor = new Equipment(mockCharacter, Source.CHARACTER_CLASS, FeatureType.ARMOR, FeatureType.LIGHT_ARMOR, FeatureType.MEDIUM_ARMOR);
	}
	
	@Test
	public void checkSource() {
		assertEquals(Source.CHARACTER_CLASS, armor.getSource());
	}

	@Test
	public void checkRelevancy_PROFICIENCY() {
		assertTrue(armor.isRelevant(FeatureType.PROFICIENCY));
	}

	@Test
	public void checkRelevancy_ARMOR() {
		assertTrue(armor.isRelevant(FeatureType.ARMOR));
	}

	@Test
	public void checkRelevancy_ARMORTypes() {
		assertTrue(armor.isRelevant(FeatureType.LIGHT_ARMOR));
		assertTrue(armor.isRelevant(FeatureType.MEDIUM_ARMOR));
	}
}
