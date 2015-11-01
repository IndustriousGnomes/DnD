package com.industriousgnomes.dnd.character.feature;

import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.add;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import com.industriousgnomes.dnd.action.Attack;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.proficiency.Proficiency;
import com.industriousgnomes.dnd.character.race.Gender;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class FeaturesCompTest {

    Attack attack;
    PlayerCharacter attacker = new PlayerCharacter("Attacker");
    PlayerCharacter defender = new PlayerCharacter("Defender");
    
    @Before
    public void setUp() throws Exception {
        attack = new Attack(attacker, defender);
    }

    @Test
    public void testGetAllWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        Collection<Feature> aFeatures = features.getAll(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(1, aFeatures.size());
        assertTrue(aFeatures.toArray()[0] instanceof ProficiencyWithLimitation);
    }

    @Test
    public void testGetAllWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        Collection<Feature> aFeatures = features.getAll(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(0, aFeatures.size());
    }

    @Test
    public void testTotalWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        int aFeatures = features.total(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(2, aFeatures);
    }

    @Test
    public void testTotalWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        int aFeatures = features.total(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(0, aFeatures);
    }

    @Test
    public void testCountWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        int aFeatures = features.count(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(1, aFeatures);
    }

    @Test
    public void testCountWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        int aFeatures = features.count(attack, FeatureType.ATTACK_MODIFIER);

        assertEquals(0, aFeatures);
    }

    @Test
    public void testContainsWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        boolean aFeatures = features.contains(attack, FeatureType.ATTACK_MODIFIER);

        assertTrue(aFeatures);
    }

    @Test
    public void testContainsWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        boolean aFeatures = features.contains(attack, FeatureType.ATTACK_MODIFIER);

        assertFalse(aFeatures);
    }
   
    @Test
    public void testRemoveWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        features.remove(attack, FeatureType.ATTACK_MODIFIER);
        boolean aFeatures = features.contains(FeatureType.ATTACK_MODIFIER);

        assertFalse(aFeatures);
    }

    @Test
    public void testRemoveWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        features.remove(attack, FeatureType.ATTACK_MODIFIER);
        boolean aFeatures = features.contains(FeatureType.ATTACK_MODIFIER);

        assertTrue(aFeatures);
    }
   
    @Test
    public void testRemoveLastWithLimitationNotExcludingProficiency() {
        attack = new Attack(attacker, defender);
        
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        features.add(new ProficiencyWithLimitation2(attacker));
        attacker.getCharacterRace().setGender(Gender.FEMALE);

        features.removeLast(attack, FeatureType.ATTACK_MODIFIER);
        int aFeatures = features.total(FeatureType.ATTACK_MODIFIER);

        assertEquals(2, aFeatures);
    }

    @Test
    public void testRemoveLastWithLimitationExcludingProficiency() {
        Features features = attacker.getFeatures();
        features.add(new ProficiencyWithLimitation(attacker));
        features.add(new ProficiencyWithLimitation2(attacker));
        attacker.getCharacterRace().setGender(Gender.MALE);

        features.removeLast(attack, FeatureType.ATTACK_MODIFIER);
        int aFeatures = features.total(FeatureType.ATTACK_MODIFIER);

        assertEquals(5, aFeatures);
    }
   
    public class ProficiencyWithLimitation extends Proficiency {

        public ProficiencyWithLimitation(PlayerCharacter character) {
            super(character, Source.ZZZ, FeatureType.ATTACK_MODIFIER);
        }

        @Override
        public Pair<GamePiece, Object> getLimitations() {
            return add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_RACE, add(GamePiece.GENDER, Gender.FEMALE)));
        }

        @Override
        public Integer execute(FeatureType featureType) {
            return 2;
        }
    }

    public class ProficiencyWithLimitation2 extends Proficiency {

        public ProficiencyWithLimitation2(PlayerCharacter character) {
            super(character, Source.ZZZ, FeatureType.ATTACK_MODIFIER);
        }

        @Override
        public Pair<GamePiece, Object> getLimitations() {
            return add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_RACE, add(GamePiece.GENDER, Gender.FEMALE)));
        }

        @Override
        public Integer execute(FeatureType featureType) {
            return 3;
        }
    }
}
