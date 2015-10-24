package com.industriousgnomes.dnd.limitation.builder;

import java.util.Arrays;

import org.apache.commons.lang3.tuple.Pair;

public class LimitationBuilder {
    
    public static Pair<GamePiece, Object> add(GamePiece gamePiece, Object value) {
        
        return Pair.of(gamePiece, value);

    }
    
    @SafeVarargs
    public static Object and(Pair<GamePiece, Object>... items) {
        return Arrays.asList(items);
    }

    @SafeVarargs
    public static Object or(Pair<GamePiece, Object>... items) {
        return items;
    }

    @SafeVarargs
    public static Object or(String... items) {
        return items;
    }

    // TODO can we make this?    
/*    public static GamePiece path(GamePiece... gamePieces) {
        return null;
    }
*/}
