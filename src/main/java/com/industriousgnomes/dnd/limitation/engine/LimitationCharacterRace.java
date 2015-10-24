package com.industriousgnomes.dnd.limitation.engine;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.race.CharacterRace;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class LimitationCharacterRace {

    @SuppressWarnings("unchecked")
    public static Boolean execute(Pair<GamePiece, Object> limitation, CharacterRace characterRace) {
        GamePiece key = limitation.getKey();
        
        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, characterRace));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, characterRace));
        } else {
            return process(key, limitation.getValue(), characterRace);
        }
    }

    @SuppressWarnings("incomplete-switch")
    private static Boolean process(GamePiece key, Object value, CharacterRace characterRace) {
        
        switch (key) {
            case RACE : 
                return value.equals(characterRace.getRace());

            case GENDER : 
                return value.equals(characterRace.getGender());
}
        
        return false;
    }

}
