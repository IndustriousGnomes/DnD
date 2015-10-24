package com.industriousgnomes.dnd.limitation;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.race.CharacterRace;

public class LimitationCharacterRace {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Boolean execute(Pair<CharacterRacePieces, Object> limitation, CharacterRace characterRace) {
        
        CharacterRacePieces key = limitation.getKey();        

        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, characterRace));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, characterRace));
        } else {
            return process(key, limitation.getValue(), characterRace);
        }
    }

    private static Boolean process(CharacterRacePieces key, Object value, CharacterRace characterRace) {

        switch (key) {
            case RACE:
                return (String)value == characterRace.getRace();
            case GENDER:
                return value == characterRace.getGender();
            default:
                return false;
        }        
    }

}
