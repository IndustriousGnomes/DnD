package com.industriousgnomes.dnd.character.clazz;

import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;
import com.industriousgnomes.dnd.character.feature.clazz.InitialHitDie;
import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;

@RunWith(JMockit.class)
public class CharacterClassTest {

	@Mocked PlayerCharacter mockCharacter;
	@Mocked Equipment mockWeaponProficiency;
	@Mocked Features features;
	
	private CharacterClass characterClass;
	
	private class MyCharacterClass extends CharacterClass {
		public MyCharacterClass(PlayerCharacter character) {
			super(character);
		}

		@Override
		public String getClassName() {
			return "MyCharacterClass";
		}
	}
	
	@Before
	public void setUp() {
		characterClass = new MyCharacterClass(mockCharacter);
		characterClass.features = features;
		
		new Verifications() {{
			features.remove(Source.CHARACTER_CLASS);
		}};
	}
	
	@Test
	public void checkFeaturesSetByHitDiceSides_NewCharacter() {
		characterClass.setHitDiceSides(10);
		
		new Verifications() {{
			features.remove(FeatureType.HIT_DIE);
			features.add((InitialHitDie)any);
		}};
	}
	
	@Test
	public void addProficiency() {
		characterClass.addFeature(mockWeaponProficiency);
		
		new Verifications() {{
			features.add(mockWeaponProficiency);
		}};
	}

}
