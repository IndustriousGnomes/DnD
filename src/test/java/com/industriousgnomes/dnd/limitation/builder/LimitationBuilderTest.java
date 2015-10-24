package com.industriousgnomes.dnd.limitation.builder;

import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.add;
import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.and;
import static com.industriousgnomes.dnd.limitation.builder.LimitationBuilder.or;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import com.industriousgnomes.dnd.character.race.Gender;

public class LimitationBuilderTest {

    @Test
    public void checkAdd() {
        Pair<GamePiece, Object> limit = add(GamePiece.NAME, "Fighter");
        
        assertEquals(GamePiece.NAME, limit.getKey());
        assertEquals("Fighter", limit.getValue());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void checkAddToAdd() {
        
        Pair<GamePiece, Object> limit = add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter"));
        
        assertEquals(GamePiece.CHARACTER_CLASS, limit.getKey());
        Pair<GamePiece, Object> limitCharacterClass = (Pair<GamePiece, Object>)limit.getValue();
        assertEquals(GamePiece.NAME, limitCharacterClass.getKey());
        assertEquals("Fighter", limitCharacterClass.getValue());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void checkAnd_Pairs() {
        
        Pair<GamePiece, Object> limit = add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE)));
        
        assertEquals(GamePiece.CHARACTER_RACE, limit.getKey());
        List<Pair<GamePiece, Object>> limitCharacterRace = (List<Pair<GamePiece, Object>>)limit.getValue();
        assertEquals(2, limitCharacterRace.size());
        
        Pair<GamePiece, Object> limitRace = limitCharacterRace.get(0);
        assertEquals(GamePiece.RACE, limitRace.getKey());
        assertEquals("Elf", limitRace.getValue());

        Pair<GamePiece, Object> limitGender = limitCharacterRace.get(1);
        assertEquals(GamePiece.GENDER, limitGender.getKey());
        assertEquals(Gender.FEMALE, limitGender.getValue());
    }
    
    // This situation is not possible
    public void checkAnd_Strings() {
//        Pair<GamePiece, Object> limit = add(GamePiece.RACE, and("Elf", "Human"));
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void checkOr_Pairs() {
        
        Pair<GamePiece, Object> limit = add(GamePiece.CHARACTER_RACE, or(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE)));
        
        assertEquals(GamePiece.CHARACTER_RACE, limit.getKey());
        Pair<GamePiece, Object>[] limitCharacterRace = (Pair<GamePiece, Object>[])limit.getValue();
        assertEquals(2, limitCharacterRace.length);
        
        Pair<GamePiece, Object> limitRace = limitCharacterRace[0];
        assertEquals(GamePiece.RACE, limitRace.getKey());
        assertEquals("Elf", limitRace.getValue());

        Pair<GamePiece, Object> limitGender = limitCharacterRace[1];
        assertEquals(GamePiece.GENDER, limitGender.getKey());
        assertEquals(Gender.FEMALE, limitGender.getValue());
    }
    
    @Test
    public void checkOr_Strings() {
        
        Pair<GamePiece, Object> limit = add(GamePiece.RACE, or("Elf", "Human"));
        
        assertEquals(GamePiece.RACE, limit.getKey());
        String[] limitRace = (String[])limit.getValue();
        assertEquals(2, limitRace.length);
        assertEquals("Elf", limitRace[0]);
        assertEquals("Human", limitRace[1]);        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void checkPath() {
        
        Pair<GamePiece, Object> limit = 
//                     add(path(GamePiece.ATTACKER, GamePiece.CHARACTER_CLASS, GamePiece.CHARACTER_CLASS_NAME), "Fighter");
                 add(GamePiece.ATTACKER, add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter")));
                
        assertEquals(GamePiece.ATTACKER, limit.getKey());
        Pair<GamePiece, Object> limitAttacker = (Pair<GamePiece, Object>)limit.getValue();
        assertEquals(GamePiece.CHARACTER_CLASS, limitAttacker.getKey());
        Pair<GamePiece, Object> limitCharacterClass = (Pair<GamePiece, Object>)limitAttacker.getValue();
        assertEquals(GamePiece.NAME, limitCharacterClass.getKey());
        assertEquals("Fighter", limitCharacterClass.getValue());
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void attackerIsFemaleElfOrAnyFighterOrDefenderIsMaleDwarf() {
        
        Pair<GamePiece, Object> limit = 
           add(GamePiece.WORLD, 
                and(
                     add(GamePiece.ATTACKER, 
                         or(add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Elf"), add(GamePiece.GENDER, Gender.FEMALE))),
                            add(GamePiece.CHARACTER_CLASS, add(GamePiece.NAME, "Fighter"))
                           )
                         ),
                     add(GamePiece.DEFENDER,
                        add(GamePiece.CHARACTER_RACE, and(add(GamePiece.RACE, "Dwarf"), add(GamePiece.GENDER, Gender.MALE)))
                        )
                   )
              ); 
                
        assertEquals(GamePiece.WORLD, limit.getKey());
        List<Pair<GamePiece, Object>> limitWorld = (List<Pair<GamePiece, Object>>)limit.getValue(); // and
        assertEquals(2, limitWorld.size());
           
        Pair<GamePiece, Object> limitAttacker = limitWorld.get(0);
        assertEquals(GamePiece.ATTACKER, limitAttacker.getKey());
        Pair<GamePiece, Object>[] limitAttackerPlayer = (Pair<GamePiece, Object>[])limitAttacker.getValue(); // or
        assertEquals(2, limitAttackerPlayer.length);
        
        Pair<GamePiece, Object> limitAttackerCharacterRace = limitAttackerPlayer[0];
        assertEquals(GamePiece.CHARACTER_RACE, limitAttackerCharacterRace.getKey());
        List<Pair<GamePiece, Object>> limitAttackerCharacterRaceRace = (List<Pair<GamePiece, Object>>)limitAttackerCharacterRace.getValue(); // and
        assertEquals(2, limitAttackerCharacterRaceRace.size());

        // TODO finish later
        
        
        
        
        Pair<GamePiece, Object> limitDefender = limitWorld.get(1);
        
    }

    @Test
    public void test() {
        /*
        Pair<WorldPieces, Object>[] limitation = 
            and {add(WorldPieces.ATTACKER, 
                        or {add(PlayerCharacterPieces.CHARACTER_RACE, 
                                        and(add(CharacterRacePieces.RACE, "Elf"),
                                               add(CharacterRacePieces.GENDER, Gender.FEMALE)
                                        )
                                       ),
                                    add(PlayerCharacterPieces.CHARACTER_CLASS, 
                                           add(CharacterClassPieces.NAME,
                                                   "Fighter"
                                                  )
                                          )
                                    }
                            ),
                    add(WorldPieces.DEFENDER, 
                            add(PlayerCharacterPieces.CHARACTER_RACE, 
                                            and(add(CharacterRacePieces.RACE, "Dwarf"),
                                                   add(CharacterRacePieces.GENDER, Gender.MALE)
                                            )
                                           )
                                )           
        };
    
 */

        
/*        
        Pair<WorldPieces, Object> limitation = 
                add(WorldPieces.ATTACKER, 
                        add(PlayerCharacterPieces.CHARACTER_CLASS, 
                                add(CharacterClassPieces.NAME,
                                        "Fighter"
                                       )
                               )
                       );

*/        

    }

 // {attacker : {character_race : {race : "elf", gender : "female"}}, defender : {character_race : {race : "dwarf", gender : "male"}}}
}
