package com.industriousgnomes.dnd.limitation.engine;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class LimitationEngine {

    @SuppressWarnings("unchecked")
    public static Boolean execute(Pair<GamePiece, Object> limitation, PlayerCharacter attacker, PlayerCharacter defender) {
        
        GamePiece key = limitation.getKey();
        
        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, attacker, defender));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, attacker, defender));
        } else {
            return process(key, limitation.getValue(), attacker, defender);
        }
    }
    
    private static Boolean process(GamePiece key, Object value, PlayerCharacter attacker, PlayerCharacter defender) {
        
        switch (key) {
            case WORLD :
                return execute((Pair<GamePiece, Object>)value, attacker, defender);
            case ATTACKER : 
                return LimitationPlayerCharacter.execute((Pair<GamePiece, Object>)value, attacker);
            case DEFENDER : 
                return LimitationPlayerCharacter.execute((Pair<GamePiece, Object>)value, defender);
            
        }
        
        return false;
    }
}
