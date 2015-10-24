package com.industriousgnomes.dnd.character.feature;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.LinkedList;

import mockit.Mocked;
import mockit.NonStrictExpectations;

import org.junit.Before;
import org.junit.Test;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;
import com.industriousgnomes.dnd.character.feature.proficiency.Skill;

public class FeaturesTest {

	@Mocked PlayerCharacter mockCharacter;
	Features features;
	
	@Before
	public void setUp() throws Exception {
		features = new Features();
		
		new NonStrictExpectations() {{
			mockCharacter.getProficiencyBonus(); result = 2;
		}};
	}

	@Test
	public void addFeature() {
		Feature feature = new Equipment(mockCharacter, Source.CHARACTER_CLASS, FeatureType.WEAPON);
		
		features.add(feature);
		
		assertEquals(1, features.getAll(FeatureType.WEAPON).size());
	}
	
	@Test
	public void addAllFeatures() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS)
				));

		features.addAll(myFeatures);
		
		assertEquals(2, features.getAll(FeatureType.ACROBATICS).size());
	}

	@Test
	public void checkTotal_FeatureType() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS)
				));

		features.addAll(myFeatures);
		
		assertEquals(4, features.total(FeatureType.ACROBATICS));
	}
		
	@Test
	public void checkCount_FeatureType() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS)
				));

		features.addAll(myFeatures);
		
		assertEquals(2, features.count(FeatureType.ACROBATICS));
	}

	@Test
	public void checkContains_FeatureType() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS)
				));

		features.addAll(myFeatures);
		
		assertTrue(features.contains(FeatureType.ACROBATICS));
	}

	@Test
	public void removeLast_FeatureType() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.CLUB),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.DAGGER)
				));

		features.addAll(myFeatures);

		features.removeLast(FeatureType.ACROBATICS);
		
		assertEquals(1, features.count(FeatureType.ACROBATICS));
		assertEquals(1, features.count(FeatureType.CLUB));
	}

	@Test
	public void remove_Source() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.CLUB),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.DAGGER)
				));

		features.addAll(myFeatures);

		features.remove(Source.SKILL);
		
		assertEquals(0, features.getAll(Source.SKILL).size());
	}

	@Test
	public void remove_FeatureType() {
		Collection<Feature> myFeatures = new LinkedList<>();
		myFeatures.addAll(asList(
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.CLUB),
					new Skill(mockCharacter, Source.SKILL, FeatureType.ACROBATICS, FeatureType.DAGGER)
				));

		features.addAll(myFeatures);

		features.remove(FeatureType.ACROBATICS);
		
		assertEquals(0, features.getAll(Source.SKILL).size());
	}
}
