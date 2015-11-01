package com.industriousgnomes.dnd.limitation.engine;
import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.add;
import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.and;
import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.or;
import static org.junit.Assert.assertTrue;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.clazz.CharacterClass;
import com.industriousgnomes.dnd.character.race.CharacterRace;
import com.industriousgnomes.dnd.character.race.Gender;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

@RunWith(JMockit.class)
public class LimitationEngineTest {

    @Mocked PlayerCharacter mockedAttacker;
    @Mocked PlayerCharacter mockedDefender;
    @Mocked CharacterClass mockedFighter;
    @Mocked CharacterRace mockedElf;
    @Mocked CharacterRace mockedDwarf;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void attackerIsFighter() {
        new Expectations() {{
            mockedAttacker.getCharacterClass(); result = mockedFighter;
            mockedFighter.getClassName(); result = "Fighter";
        }};

        Pair<GamePiece, Object> limit = 
                add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter")));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsElf() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
        }};
        
        Pair<GamePiece, Object> limit =
                add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_RACE, add(GamePiece.RACE, "Elf")));

        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsFemaleElf() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
            mockedElf.getGender(); result = Gender.FEMALE;
        }};
        
        Pair<GamePiece, Object> limit =
                add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE))));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsFemaleElfOrFemaleHuman() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); returns("Elf", "Human");
            mockedElf.getGender(); result = Gender.FEMALE;
        }};
        
        Pair<GamePiece, Object> limit =
                add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, or("Elf", "Human")), add(GamePiece.GENDER, Gender.FEMALE))));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsFemaleElfOrAnyFighter() {
        new Expectations() {{
            mockedAttacker.getCharacterClass(); result = mockedFighter;
            mockedFighter.getClassName(); result = "Fighter";
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
            mockedElf.getGender(); result = Gender.MALE;
        }};

        Pair<GamePiece, Object> limit = 
                add(GamePiece.ATTACKER, or(add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE))),
                                           add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter"))));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsFemaleElfOrAnyFighterAndDefenderIsMaleDwarf() {
        new NonStrictExpectations() {{
            mockedAttacker.getCharacterClass(); result = mockedFighter;
            mockedFighter.getClassName(); result = "Fighter";
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
            mockedElf.getGender(); result = Gender.FEMALE;

            mockedDefender.getCharacterRace(); result = mockedDwarf;
            mockedDwarf.getRace(); result = "Dwarf";
            mockedDwarf.getGender(); result = Gender.MALE;
        }};
        
        Pair<GamePiece, Object> limit = 
                add(GamePiece.WORLD, 
                and(add(GamePiece.ATTACKER, or(add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE))),
                                               add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter")))),
                    add(GamePiece.DEFENDER, add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Dwarf"), add(GamePiece.GENDER, Gender.MALE))))
                   ));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

    @Test
    public void attackerIsFemaleElfOrAnyFighterOrDefenderIsMaleDwarf() {
        new NonStrictExpectations() {{
            mockedAttacker.getCharacterClass(); result = mockedFighter;
            mockedFighter.getClassName(); result = "Fighter";
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
            mockedElf.getGender(); result = Gender.MALE;

            mockedDefender.getCharacterRace(); result = mockedDwarf;
            mockedDwarf.getRace(); result = "Dwarf";
            mockedDwarf.getGender(); result = Gender.MALE;
        }};
        
        Pair<GamePiece, Object> limit = 
                add(GamePiece.WORLD, 
                 or(add(GamePiece.ATTACKER, or(add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE))),
                                               add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter")))),
                    add(GamePiece.DEFENDER, add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Dwarf"), add(GamePiece.GENDER, Gender.MALE))))
                   ));
        
        assertTrue(LimitationEngine.execute(null, limit));
    }

}
