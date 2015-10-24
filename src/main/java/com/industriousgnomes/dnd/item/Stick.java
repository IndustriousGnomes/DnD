package com.industriousgnomes.dnd.item;

import com.industriousgnomes.dnd.character.feature.proficiency.Equipment;

public class Stick {

    private String type;
    private String category;
    private String item;

    public Stick() {
        // TODO Auto-generated constructor stub
    }

    public boolean proficienceyRequirement(Equipment proficiency) {
        return true; // if the proficiency covers either a type, category, or item
    }
}
