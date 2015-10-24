package com.industriousgnomes.dnd.combat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;

@RunWith(JMockit.class)	// see usage below
public class CombatCompTest {

    @Mocked Dice mockDice;

    private PlayerCharacter attacker = new PlayerCharacter("Attacker");
    private PlayerCharacter defender = new PlayerCharacter("Defender");

    @Test
    public void attackSequenceWithAHitThatDoesntKill() {

    	// Due to the random nature of dice rolling, the rolls are being controlled through JMockit
        new Expectations() {{
        	Dice.roll(20); result = 19;
        	Dice.roll(8); result = 4;
        }};

        Combat.attackSequence(attacker, defender);

        assertTrue(defender.isAlive());
        assertEquals(0, attacker.getExperiencePoints());
    }
    
    @Test
    public void attackSequenceWithACriticalThatKills() {

        new Expectations() {{
        	Dice.roll(20); result = 20;
        	Dice.roll(8); result = 8;
        }};
        
        Combat.attackSequence(attacker, defender);

        assertFalse(defender.isAlive());
        assertEquals(10, attacker.getExperiencePoints());
    }

}
