package com.industriousgnomes.dnd.action;

import java.util.HashMap;
import java.util.Map;

public abstract class Action {

    private Map<Actor, Object> actors = new HashMap<>();

    public Object get(Actor actor) {
        return actors.get(actor);
    }
    
    public void put(Actor actor, Object object) {
        actors.put(actor, object);
    }
    
    public abstract void execute();
}
