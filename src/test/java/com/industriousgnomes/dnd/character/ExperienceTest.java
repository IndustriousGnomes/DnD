package com.industriousgnomes.dnd.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class ExperienceTest {

	private Experience exp = new Experience();
	
	@Test
	public void addExperiencePoints_NoLevelChange() {
		assertFalse(exp.add(100));
		assertEquals(1, exp.getExperienceLevel());
	}

	@Test
	public void addExperiencePoints_IncreaseLevelChange() {
		assertTrue(exp.add(500));
		assertEquals(2, exp.getExperienceLevel());
	}

	@Test
	public void addExperiencePoints_DecreaseLevelChange() {
		exp.add(500);
		
		assertTrue(exp.add(-300));
		assertEquals(1, exp.getExperienceLevel());
	}
	
	@Test
	public void setExperiencePoints() {
		exp.setExperiencePoints(4500);
		assertEquals(4, exp.getExperienceLevel());
	}

	@Test
	public void getProficiencyBonus() {
		exp.setExperiencePoints(30_000);
		assertEquals(3, exp.getProficiencyBonus());
	}
	
	@Test
	public void checkProficienceBonusAtAllLevels() {
		int PROFICIENCY_BONUS[] = { 2, 2, 2, 2,
									3, 3, 3, 3,
									4, 4, 4, 4,
									5, 5, 5, 5,
									6, 6, 6, 6,
					              };

		for (int lvl = 1; lvl <= 20; lvl++) {
			exp.setExperiencePoints(exp.EXERPIENCE_PER_LEVEL[lvl - 1]);
			assertEquals("Level " + exp.getExperienceLevel() + "; xp " + exp.getExperiencePoints(), PROFICIENCY_BONUS[lvl - 1], exp.getProficiencyBonus());
		}
	}
	
}
