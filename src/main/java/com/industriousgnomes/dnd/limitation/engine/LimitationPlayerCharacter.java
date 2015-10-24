package com.industriousgnomes.dnd.limitation.engine;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class LimitationPlayerCharacter {

    @SuppressWarnings("unchecked")
    public static Boolean execute(Pair<GamePiece, Object> limitation, PlayerCharacter character) {
        GamePiece key = limitation.getKey();
        
        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, character));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, character));
        } else {
            return process(key, limitation.getValue(), character);
        }
    }

    @SuppressWarnings("unchecked")
    private static Boolean process(GamePiece key, Object value, PlayerCharacter character) {
        
        switch (key) {
            case CHARACTER_CLASS : 
                return LimitationCharacterClass.execute((Pair<GamePiece, Object>)value, character.getCharacterClass());
            
            case CHARACTER_RACE: 
                return LimitationCharacterRace.execute((Pair<GamePiece, Object>)value, character.getCharacterRace());
        }
        
        return false;
    }

}
