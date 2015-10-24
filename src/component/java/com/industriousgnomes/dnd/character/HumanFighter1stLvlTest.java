package com.industriousgnomes.dnd.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import com.industriousgnomes.dnd.character.clazz.Fighter;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;
import com.industriousgnomes.dnd.character.race.Human;

public class HumanFighter1stLvlTest {

	static final int STRENGTH 		= 18;
	static final int DEXTERITY 		= 14;
	static final int INTELLIGENCE 	= 10;
	static final int WISDOM 		=  8;
	static final int CONSTITUTION 	= 15;
	static final int CHARISMA 		= 11;

	static final int DAMAGE_TAKEN	=  3;
	
	static final int XP_SET			= 10;
	static final int XP_ADD			= 30;

	
	private PlayerCharacter character;
	private Features features;
	
	@Before
	public void setUp() throws Exception {
		character = new PlayerCharacter("My Lvl 1 Human Fighter");
		character.setAlignment(Alignment.GOOD);
		character.setAttributes(Arrays.asList(
					Pair.of(FeatureType.STRENGTH, 		STRENGTH),
					Pair.of(FeatureType.DEXTERITY, 		DEXTERITY),
					Pair.of(FeatureType.INTELLIGENCE, 	INTELLIGENCE),
					Pair.of(FeatureType.WISDOM, 		WISDOM),
					Pair.of(FeatureType.CONSTITUTION, 	CONSTITUTION),
					Pair.of(FeatureType.CHARISMA, 		CHARISMA)
				));
		character.setCharacterClass(new Fighter(character));
		character.getCharacterClass().addSkill(FeatureType.NATURE);	// not valid
		character.getCharacterClass().addSkill(FeatureType.ACROBATICS);
		character.getCharacterClass().addSkill(FeatureType.INTIMIDATION);
		
		character.setCharacterRace(new Human(character));	// Default race, so not really needed
		
		character.setExperiencePoints(XP_SET);
		character.addExperiencePoints(XP_ADD);
		
		features = character.getFeatures();
		character.addDamageTaken(DAMAGE_TAKEN);
	}

	@Test
	public void checkPlayerName() {
		assertEquals("My Lvl 1 Human Fighter", character.getName());
	}

	@Test
	public void checkAlignment() {
		assertEquals(Alignment.GOOD, character.getAlignment());
	}
	
	@Test
	public void checkCharacterClass() {
		assertEquals(Fighter.CLASS_NAME, character.getCharacterClass().getClassName());
	}
	
	@Test
	public void checkHitPoints() {
		assertEquals(13, character.getHitPoints());
	}
	
	@Test
	public void checkDamageTaken() {
		assertEquals(DAMAGE_TAKEN, character.getDamageTaken());
	}
	
	@Test
	public void checkIsAlive() {
		assertEquals(12 - DAMAGE_TAKEN > 0, character.isAlive());
	}
	
	@Test
	public void checkExperiencePoints() {
		assertEquals(XP_SET + XP_ADD, character.getExperiencePoints());
	}
	
	@Test
	public void checkCharacterLevel() {
		int lvl = 0;
		while (character.getExperiencePoints() < Experience.EXERPIENCE_PER_LEVEL[lvl++]) {}
		assertEquals(lvl, character.getExperienceLevel());
	}
	
	@Test
	public void checkFeatureList() {
		assertEquals(STRENGTH + 1, 		features.total(FeatureType.STRENGTH));
		assertEquals(DEXTERITY + 1, 	features.total(FeatureType.DEXTERITY));
		assertEquals(INTELLIGENCE + 1, 	features.total(FeatureType.INTELLIGENCE));
		assertEquals(WISDOM + 1, 		features.total(FeatureType.WISDOM));
		assertEquals(CONSTITUTION + 1, 	features.total(FeatureType.CONSTITUTION));
		assertEquals(CHARISMA + 1, 		features.total(FeatureType.CHARISMA));
		
		assertEquals(30, features.total(FeatureType.MOVEMENT_SPEED));
		
		assertTrue(features.contains(FeatureType.LIGHT_ARMOR));
		assertTrue(features.contains(FeatureType.MEDIUM_ARMOR));
		assertTrue(features.contains(FeatureType.HEAVY_ARMOR));
		assertTrue(features.contains(FeatureType.SHIELD));
		
		assertTrue(features.contains(FeatureType.SIMPLE_WEAPONS));
		assertTrue(features.contains(FeatureType.MARTIAL_WEAPONS));
		
		assertEquals( 6, features.total(FeatureType.STRENGTH_SAVING_THROW));
		assertEquals( 2, features.total(FeatureType.DEXTERITY_SAVING_THROW));
		assertEquals( 0, features.total(FeatureType.INTELLIGENCE_SAVING_THROW));
		assertEquals(-1, features.total(FeatureType.WISDOM_SAVING_THROW));
		assertEquals( 5, features.total(FeatureType.CONSTITUTION_SAVING_THROW));
		assertEquals( 1, features.total(FeatureType.CHARISMA_SAVING_THROW));
		
    	assertEquals( 4, features.total(FeatureType.ACROBATICS));
    	assertEquals(-1, features.total(FeatureType.ANIMAL_HANDLING));
    	assertEquals( 0, features.total(FeatureType.ARCANA));
    	assertEquals( 4, features.total(FeatureType.ATHLETICS));
    	assertEquals( 1, features.total(FeatureType.DECPTION));
    	assertEquals( 0, features.total(FeatureType.HISTORY));
    	assertEquals(-1, features.total(FeatureType.INSIGHT));
    	assertEquals( 3, features.total(FeatureType.INTIMIDATION));
    	assertEquals( 0, features.total(FeatureType.INVESTIGATION));
    	assertEquals(-1, features.total(FeatureType.MEDICINE));
    	assertEquals( 0, features.total(FeatureType.NATURE));
    	assertEquals(-1, features.total(FeatureType.PERCEPTION));
    	assertEquals( 1, features.total(FeatureType.PERFORMANCE));
    	assertEquals( 1, features.total(FeatureType.PERSUASION));
    	assertEquals( 0, features.total(FeatureType.RELIGION));
    	assertEquals( 2, features.total(FeatureType.SLIGHT_OF_HAND));
    	assertEquals( 2, features.total(FeatureType.STEALTH));
    	assertEquals(-1, features.total(FeatureType.SURVIVAL));
	}
}
