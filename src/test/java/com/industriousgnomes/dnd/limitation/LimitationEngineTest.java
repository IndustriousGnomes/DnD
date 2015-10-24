package com.industriousgnomes.dnd.limitation;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

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
        
        Pair<WorldPieces, Object> limitation = 
                Pair.of(WorldPieces.ATTACKER, 
                        Pair.of(PlayerCharacterPieces.CHARACTER_CLASS, 
                                Pair.of(CharacterClassPieces.NAME,
                                        "Fighter"
                                       )
                               )
                       );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, null));
    }

    @Test
    public void attackerIsElf() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
        }};
        
        Pair<WorldPieces, Object> limitation = 
                Pair.of(WorldPieces.ATTACKER, 
                        Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                Pair.of(CharacterRacePieces.RACE,
                                        "Elf"
                                       )
                               )
                       );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, null));
    }

    @Test
    public void attackerIsFemaleElf() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); result = "Elf";
            mockedElf.getGender(); result = Gender.FEMALE;
        }};
        
        Pair<WorldPieces, Object> limitation = 
                Pair.of(WorldPieces.ATTACKER, 
                        Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                asList(Pair.of(CharacterRacePieces.RACE, "Elf"),
                                       Pair.of(CharacterRacePieces.GENDER, Gender.FEMALE)
                                )
                               )
                       );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, null));
    }

    @Test
    public void attackerIsFemaleElfOrFemaleHuman() {
        new Expectations() {{
            mockedAttacker.getCharacterRace(); result = mockedElf;
            mockedElf.getRace(); returns("Elf", "Human");
            mockedElf.getGender(); result = Gender.FEMALE;
        }};
        
        Pair<WorldPieces, Object> limitation = 
                Pair.of(WorldPieces.ATTACKER, 
                        Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                asList(Pair.of(CharacterRacePieces.RACE, new String[]{"Elf", "Human"}),
                                       Pair.of(CharacterRacePieces.GENDER, Gender.FEMALE)
                                )
                               )
                       );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, null));
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
        
        Pair<WorldPieces, Object> limitation = 
                Pair.of(WorldPieces.ATTACKER, 
                        new Pair[] {Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                        asList(Pair.of(CharacterRacePieces.RACE, "Elf"),
                                               Pair.of(CharacterRacePieces.GENDER, Gender.FEMALE)
                                        )
                                       ),
                                    Pair.of(PlayerCharacterPieces.CHARACTER_CLASS, 
                                           Pair.of(CharacterClassPieces.NAME,
                                                   "Fighter"
                                                  )
                                          )
                                    }
                       );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, null));
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
        
        Collection<Pair<WorldPieces, Object>> limitation = 
            asList(Pair.of(WorldPieces.ATTACKER, 
                        new Pair[] {Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                        asList(Pair.of(CharacterRacePieces.RACE, "Elf"),
                                               Pair.of(CharacterRacePieces.GENDER, Gender.FEMALE)
                                        )
                                       ),
                                    Pair.of(PlayerCharacterPieces.CHARACTER_CLASS, 
                                           Pair.of(CharacterClassPieces.NAME,
                                                   "Fighter"
                                                  )
                                          )
                                    }
                            ),
                    Pair.of(WorldPieces.DEFENDER, 
                            Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                            asList(Pair.of(CharacterRacePieces.RACE, "Dwarf"),
                                                   Pair.of(CharacterRacePieces.GENDER, Gender.MALE)
                                            )
                                           )
                                )           
                   );
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, mockedDefender));
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
        
        @SuppressWarnings("unchecked")
        Pair<WorldPieces, Object>[] limitation = 
            new Pair[] {Pair.of(WorldPieces.ATTACKER, 
                        new Pair[] {Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                        asList(Pair.of(CharacterRacePieces.RACE, "Elf"),
                                               Pair.of(CharacterRacePieces.GENDER, Gender.FEMALE)
                                        )
                                       ),
                                    Pair.of(PlayerCharacterPieces.CHARACTER_CLASS, 
                                           Pair.of(CharacterClassPieces.NAME,
                                                   "Fighter"
                                                  )
                                          )
                                    }
                            ),
                    Pair.of(WorldPieces.DEFENDER, 
                            Pair.of(PlayerCharacterPieces.CHARACTER_RACE, 
                                            asList(Pair.of(CharacterRacePieces.RACE, "Dwarf"),
                                                   Pair.of(CharacterRacePieces.GENDER, Gender.MALE)
                                            )
                                           )
                                )           
        };
        
        assertTrue(LimitationEngine.execute(limitation, mockedAttacker, mockedDefender));
    }

/*
 * {attacker : {character_race : {race : "elf", gender : "female"}}, defender : {character_race : {race : "dwarf", gender : "male"}}}
 *
 * 
{attacker : {[race : elf, gender : female]}, defender : {[race : dwarf | gnome, gender : male]}}
 */
    
}
