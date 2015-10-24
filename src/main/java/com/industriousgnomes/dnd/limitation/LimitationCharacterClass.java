package com.industriousgnomes.dnd.limitation;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.clazz.CharacterClass;

public class LimitationCharacterClass {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Boolean execute(Pair<CharacterClassPieces, Object> limitation, CharacterClass characterClass) {
        
        CharacterClassPieces key = limitation.getKey();        

        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, characterClass));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, characterClass));
        } else {
            return process(key, limitation.getValue(), characterClass);
        }
    }

    private static Boolean process(CharacterClassPieces key, Object value, CharacterClass characterClass) {

        switch (key) {
            case NAME:
                return (String)value == characterClass.getClassName();
        }
        
        return false;
    }

}
