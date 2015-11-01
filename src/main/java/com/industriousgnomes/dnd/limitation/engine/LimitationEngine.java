package com.industriousgnomes.dnd.limitation.engine;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.action.Action;
import com.industriousgnomes.dnd.action.Actor;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class LimitationEngine {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Boolean execute(Action action, Pair<GamePiece, Object> limitation) {
        
        if (limitation == null) {
            return true;    // No limitations are in effect
        }
        
        GamePiece key = limitation.getKey();
        
        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(action, key, value));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(action, key, value));
        } else {
            return process(action, key, limitation.getValue());
        }
    }
    
    @SuppressWarnings({ "unchecked", "incomplete-switch" })
    private static Boolean process(Action action, GamePiece key, Object value) {
        
        switch (key) {
            case WORLD :
                return execute(action, (Pair<GamePiece, Object>)value);
            case ATTACKER : 
                return LimitationPlayerCharacter.execute((Pair<GamePiece, Object>)value, (PlayerCharacter)action.get(Actor.ATTACKER));
            case DEFENDER : 
                return LimitationPlayerCharacter.execute((Pair<GamePiece, Object>)value, (PlayerCharacter)action.get(Actor.DEFENDER));
        }
        
        return false;
    }
}
