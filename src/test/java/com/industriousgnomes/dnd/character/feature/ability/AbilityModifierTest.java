package com.industriousgnomes.dnd.character.feature.ability;

import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;

import org.junit.Test;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class AbilityModifierTest {

	@Mocked PlayerCharacter mockCharacter;
	
	@Test
	public void checkProficienceBonusAtAllLevels() {
		new Expectations() {{
			mockCharacter.getFeatures().total(FeatureType.STRENGTH); 
			returns( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
					10,11,12,13,14,15,16,17,18,19,
					20,21,22,23,24,25,26,27,28,29,
					30);
		}};
		
		AbilityModifier strength = new AbilityModifier(mockCharacter, FeatureType.STRENGTH_ABILITY_MODIFIER, FeatureType.STRENGTH);
	
		for (int mod = 0; mod <= 30; mod++) {
			assertEquals("Mod " + mod, (int)Math.floor((mod - 10.0) / 2.0), strength.execute(FeatureType.STRENGTH_ABILITY_MODIFIER));
		}
	}

}
