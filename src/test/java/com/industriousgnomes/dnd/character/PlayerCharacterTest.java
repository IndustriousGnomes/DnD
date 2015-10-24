package com.industriousgnomes.dnd.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.character.clazz.CharacterClass;
import com.industriousgnomes.dnd.character.clazz.Commoner;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;

@RunWith(JMockit.class)
public class PlayerCharacterTest {

    private PlayerCharacter character = new PlayerCharacter("Character");  
    private Features features;
    
    @Before
    public void setUp() {
    	features = character.getFeatures();
    }
    
    @Test
    public void checkDefaultAbilities() {
    	assertEquals(10, features.total(FeatureType.STRENGTH_BASE));
    	assertEquals(10, features.total(FeatureType.DEXTERITY_BASE));
    	assertEquals(10, features.total(FeatureType.INTELLIGENCE_BASE));
    	assertEquals(10, features.total(FeatureType.WISDOM_BASE));
    	assertEquals(10, features.total(FeatureType.CONSTITUTION_BASE));
    	assertEquals(10, features.total(FeatureType.CHARISMA_BASE));
    }
    
    @Test
    public void checkDefinedAbilities() {
    	Collection<Pair<FeatureType, Integer>> abilities = Arrays.asList(
    			Pair.of(FeatureType.STRENGTH, 18),
    			Pair.of(FeatureType.DEXTERITY, 15),
    			Pair.of(FeatureType.CONSTITUTION, 5)
    			);
    	
    	character.setAttributes(abilities);
    	
    	assertEquals(18, features.total(FeatureType.STRENGTH_BASE));
    	assertEquals(15, features.total(FeatureType.DEXTERITY_BASE));
    	assertEquals(10, features.total(FeatureType.INTELLIGENCE_BASE));
    	assertEquals(10, features.total(FeatureType.WISDOM_BASE));
    	assertEquals( 5, features.total(FeatureType.CONSTITUTION_BASE));
    	assertEquals(10, features.total(FeatureType.CHARISMA_BASE));
    }
    
    @Test
    public void checkSavingThrows() {
    	Collection<Pair<FeatureType, Integer>> abilities = Arrays.asList(
    			Pair.of(FeatureType.STRENGTH, 18),
    			Pair.of(FeatureType.DEXTERITY, 15),
    			Pair.of(FeatureType.CONSTITUTION, 5)
    			);
    	
    	character.setAttributes(abilities);
    	// Defaults to human, so +1 to all attributes
    	assertEquals( 4, features.total(FeatureType.STRENGTH_SAVING_THROW));
    	assertEquals( 3, features.total(FeatureType.DEXTERITY_SAVING_THROW));
    	assertEquals( 0, features.total(FeatureType.INTELLIGENCE_SAVING_THROW));
    	assertEquals( 0, features.total(FeatureType.WISDOM_SAVING_THROW));
    	assertEquals(-2, features.total(FeatureType.CONSTITUTION_SAVING_THROW));
    	assertEquals( 0, features.total(FeatureType.CHARISMA_SAVING_THROW));
    }

    @Test
    public void checkSkills() {
    	Collection<Pair<FeatureType, Integer>> abilities = Arrays.asList(
    			Pair.of(FeatureType.STRENGTH, 18),
    			Pair.of(FeatureType.DEXTERITY, 14),
    			Pair.of(FeatureType.WISDOM, 4)
    			);
    	// Defaults to human so +1 to all attributes
    	character.setAttributes(abilities);
    	assertEquals( 2, features.total(FeatureType.ACROBATICS));
    	assertEquals(-3, features.total(FeatureType.ANIMAL_HANDLING));
    	assertEquals( 0, features.total(FeatureType.ARCANA));
    	assertEquals( 4, features.total(FeatureType.ATHLETICS));
    	assertEquals( 0, features.total(FeatureType.DECPTION));
    	assertEquals( 0, features.total(FeatureType.HISTORY));
    	assertEquals(-3, features.total(FeatureType.INSIGHT));
    	assertEquals( 0, features.total(FeatureType.INTIMIDATION));
    	assertEquals( 0, features.total(FeatureType.INVESTIGATION));
    	assertEquals(-3, features.total(FeatureType.MEDICINE));
    	assertEquals( 0, features.total(FeatureType.NATURE));
    	assertEquals(-3, features.total(FeatureType.PERCEPTION));
    	assertEquals( 0, features.total(FeatureType.PERFORMANCE));
    	assertEquals( 0, features.total(FeatureType.PERSUASION));
    	assertEquals( 0, features.total(FeatureType.RELIGION));
    	assertEquals( 2, features.total(FeatureType.SLIGHT_OF_HAND));
    	assertEquals( 2, features.total(FeatureType.STEALTH));
    	assertEquals(-3, features.total(FeatureType.SURVIVAL));
    }

    @Test
    public void checkDefaultCharacterClass() {
    	CharacterClass characterClass = character.getCharacterClass();
    	assertTrue(characterClass instanceof Commoner);
    }
    
    @Test
    public void isAlive() {
        assertTrue(character.isAlive());
    }

    @Test
    public void isDead_DueToDamage() {
        character.setDamageTaken(20);
        assertFalse(character.isAlive());
    }
    
    @Test
    public void isDead_DueToHitPointsBeing0(@Mocked Experience xp) {
    	new Expectations() {{
    		xp.add(anyInt); result = true;
    		xp.getExperienceLevel(); result = 0;
    	}};
    	
    	character.addExperiencePoints(-1);

        assertFalse(character.isAlive());
    }
    
    @Test
    public void checkAddExperiencePoints_NoLevelChange(@Mocked Experience xp) {
    	new Expectations() {{
    		xp.add(anyInt); result = false;
    	}};
    	
    	assertEquals(1, features.count(FeatureType.HIT_POINTS));
    	
    	character.addExperiencePoints(1);

    	assertEquals(1, features.count(FeatureType.HIT_POINTS));
    }
    
    @Test
    public void checkAddExperiencePoints_IncreaseLevelChange(@Mocked Experience xp) {
    	new Expectations() {{
    		xp.add(anyInt); result = true;
    		xp.getExperienceLevel(); result = 3;
    	}};
    	
    	assertEquals(1, features.count(FeatureType.HIT_POINTS));

    	character.addExperiencePoints(1);

    	assertEquals(3, features.count(FeatureType.HIT_POINTS));
    }
    
    @Test
    public void checkAddExperiencePoints_DecreaseLevelChange(@Mocked Experience xp) {
    	new Expectations() {{
    		xp.add(anyInt); result = true;
    		xp.getExperienceLevel(); returns(3, 2);
    	}};
    	
    	character.addExperiencePoints(1);
    	character.addExperiencePoints(-1);

    	assertEquals(2, features.count(FeatureType.HIT_POINTS));
    }
}
