package com.industriousgnomes.dnd.action;

import static com.industriousgnomes.dnd.Dice.D20;
import static org.junit.Assert.assertEquals;
import mockit.Expectations;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.ability.Ability;

@RunWith(JMockit.class) // see usage below
public class AttackCompTest {

    // Due to the random nature of dice rolling, the rolls are being controlled through JMockit
    @Mocked Dice mockDice;

    Attack attack;
    PlayerCharacter attacker = new PlayerCharacter("Attacker");
    PlayerCharacter defender = new PlayerCharacter("Defender");

    @Before
    public void setUp() throws Exception {
        attack = new Attack(attacker, defender);
    }

    @Test
    public void testAttackWithHitUsingNoModifiers() {
        new Expectations() {{
            D20.roll(); result = 10;     // To Hit
            Dice.die(10).roll(); result = 8;      // Damage
        }};
        
        attack.execute();
        
        assertEquals(8, ((PlayerCharacter)attack.get(Actor.DEFENDER)).getDamageTaken());
    }

    @Test
    public void testAttackWithMissUsingNoModifiers() {
        new Expectations() {{
            D20.roll(); result = 9;     // To Hit
        }};
        
        attack.execute();
        
        assertEquals(0, ((PlayerCharacter)attack.get(Actor.DEFENDER)).getDamageTaken());
    }

    @Test
    public void testAttackWithHitUsingAttackModifiers() {
        new Expectations() {{
            D20.roll(); result = 9;      // To Hit
            Dice.die(10).roll(); result = 8;      // Damage
        }};

        attacker.getFeatures().add(new Ability(attacker, 4, Source.ABILITY_SCORE, FeatureType.STRENGTH));
        
        attack.execute();
        
        assertEquals(8, ((PlayerCharacter)attack.get(Actor.DEFENDER)).getDamageTaken());
    }

    @Test
    public void testAttackWithMissUsingDefenseModifiers() {
        new Expectations() {{
            D20.roll(); result = 10;      // To Hit
        }};

        defender.getFeatures().add(new Ability(attacker, 4, Source.ABILITY_SCORE, FeatureType.DEXTERITY));
        
        attack.execute();
        
        assertEquals(0, ((PlayerCharacter)attack.get(Actor.DEFENDER)).getDamageTaken());
    }
}
