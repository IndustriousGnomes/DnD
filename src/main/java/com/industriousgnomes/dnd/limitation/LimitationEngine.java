package com.industriousgnomes.dnd.limitation;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;

public class LimitationEngine {


    public static Boolean execute(Collection<Pair<WorldPieces, Object>> limitations, PlayerCharacter attacker, PlayerCharacter defender) {
        return limitations.stream().allMatch(l -> execute(l, attacker, defender));
    }
    
    public static boolean execute(Pair<WorldPieces, Object>[] limitations, PlayerCharacter attacker, PlayerCharacter defender) {
        return Arrays.asList(limitations).stream().anyMatch(l -> execute(l, attacker, defender));
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Boolean execute(Pair<WorldPieces, Object> limitation, PlayerCharacter attacker, PlayerCharacter defender) {
        
        WorldPieces key = limitation.getKey();        

        if (limitation.getValue() instanceof Collection) {
            return ((Collection)limitation.getValue()).stream().allMatch(value -> process(key, value, attacker, defender));
        } else if(limitation.getValue().getClass().isArray()) {
            Object[] array = (Object[])limitation.getValue();
            return (Arrays.asList(array)).stream().anyMatch(value -> process(key, value, attacker, defender));
        } else {
            return process(key, limitation.getValue(), attacker, defender);
        }
    }
    
    @SuppressWarnings("unchecked")
    private static Boolean process(WorldPieces key, Object value, PlayerCharacter attacker, PlayerCharacter defender) {
        
        switch (key) {
            case ATTACKER:
                return LimitationPlayerCharacter.execute((Pair<PlayerCharacterPieces, Object>)value, attacker);
            case DEFENDER:
                return LimitationPlayerCharacter.execute((Pair<PlayerCharacterPieces, Object>)value, defender);
        }
        
        return false;
    }
}
