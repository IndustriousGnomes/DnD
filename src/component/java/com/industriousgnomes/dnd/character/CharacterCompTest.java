package com.industriousgnomes.dnd.character;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.LinkedList;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import com.industriousgnomes.dnd.character.clazz.Fighter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class CharacterCompTest {

	private PlayerCharacter character;
	
	@Before
	public void setUp() throws Exception {
		character = new PlayerCharacter("My Character");
		character.setAlignment(Alignment.GOOD);
	}

	private Collection<Pair<FeatureType, Integer>> createAttributes(Integer strength, 
																	Integer dexterity,
																	Integer intelligence,
																	Integer wisdom,
																	Integer constitution,
																	Integer charisma) {
		Collection<Pair<FeatureType, Integer>> abilities = new LinkedList<>();
		if (strength  	 != null) { abilities.add(Pair.of(FeatureType.STRENGTH, 	strength)); }
		if (dexterity 	 != null) { abilities.add(Pair.of(FeatureType.DEXTERITY, 	dexterity)); }
		if (intelligence != null) { abilities.add(Pair.of(FeatureType.INTELLIGENCE, intelligence)); }
		if (wisdom  	 != null) { abilities.add(Pair.of(FeatureType.WISDOM, 		wisdom)); }
		if (constitution != null) { abilities.add(Pair.of(FeatureType.CONSTITUTION, constitution)); }
		if (charisma  	 != null) { abilities.add(Pair.of(FeatureType.CHARISMA, 	charisma)); }
		
		return abilities;
	}
	
	@Test
	public void testSavingThrows_Lvl1_Fighter() {
    	character.setAttributes(createAttributes(18, 14, null, null, 4, null));
    	character.setCharacterClass(new Fighter(character));
    	// Race defaults to human so +1 to all attributes
    	assertEquals( 6, character.getFeatures().total(FeatureType.STRENGTH_SAVING_THROW));
    	assertEquals( 2, character.getFeatures().total(FeatureType.DEXTERITY_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.INTELLIGENCE_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.WISDOM_SAVING_THROW));
    	assertEquals(-1, character.getFeatures().total(FeatureType.CONSTITUTION_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.CHARISMA_SAVING_THROW));

	}

	@Test
	public void testSavingThrows_Lvl5_Fighter() {
		character.setExperiencePoints(7000);
    	character.setAttributes(createAttributes(18, 14, null, null, 14, null));
    	character.setCharacterClass(new Fighter(character));
        // Race defaults to human so +1 to all attributes
    	assertEquals( 7, character.getFeatures().total(FeatureType.STRENGTH_SAVING_THROW));
    	assertEquals( 2, character.getFeatures().total(FeatureType.DEXTERITY_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.INTELLIGENCE_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.WISDOM_SAVING_THROW));
    	assertEquals( 5, character.getFeatures().total(FeatureType.CONSTITUTION_SAVING_THROW));
    	assertEquals( 0, character.getFeatures().total(FeatureType.CHARISMA_SAVING_THROW));

	}

	
}
