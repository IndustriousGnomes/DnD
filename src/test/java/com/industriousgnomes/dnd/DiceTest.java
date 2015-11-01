package com.industriousgnomes.dnd;

import static com.industriousgnomes.dnd.Dice.D20;
import static org.junit.Assert.assertEquals;

import java.util.Random;

import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JMockit.class)
public class DiceTest {

    @Mocked Random mockRandom;
    
    @Test
    public void d20() {
        
        new Expectations() {{
            mockRandom.nextInt(anyInt); returns(0, 1, 2);
        }};
        
        int aRolls = D20.roll(3);
        
        assertEquals(6, aRolls);
    }

    @Test
    public void d20WithMinimumValue() {
        
        new Expectations() {{
            mockRandom.nextInt(anyInt); returns(0, 1, 2, 3, 4);
        }};
        
        int aRolls = D20.roll(3, 2);
        
        assertEquals(12, aRolls);
    }
    
    @Test
    public void unknownDieSide() {
        new Expectations() {{
            mockRandom.nextInt(anyInt); result = 5;
        }};
        
        int aRoll = Dice.die(8).roll();
        
        assertEquals(6, aRoll);
    }
}
