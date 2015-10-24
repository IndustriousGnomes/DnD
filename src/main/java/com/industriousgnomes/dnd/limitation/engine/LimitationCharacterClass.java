package com.industriousgnomes.dnd.limitation.engine;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.clazz.CharacterClass;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class LimitationCharacterClass {

    @SuppressWarnings("unchecked")
    public static Boolean execute(Pair<GamePiece, Object> limitation, CharacterClass clazz) {
        GamePiece key = limitation.getKey();
        
        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, clazz));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, clazz));
        } else {
            return process(key, limitation.getValue(), clazz);
        }
    }

    @SuppressWarnings("incomplete-switch")
    private static Boolean process(GamePiece key, Object value, CharacterClass clazz) {
        
        switch (key) {
            case NAME : 
                return value.equals(clazz.getClassName());
            
        }
        
        return false;
    }

}
