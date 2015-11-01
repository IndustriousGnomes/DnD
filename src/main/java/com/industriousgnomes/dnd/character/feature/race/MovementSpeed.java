package com.industriousgnomes.dnd.character.feature.race;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.limitation.builder.GamePiece;

public class MovementSpeed implements Feature {

    protected PlayerCharacter character;
    protected Integer         movementSpeed;

    public MovementSpeed(PlayerCharacter character, Integer movementSpeed) {
        this.character = character;
        this.movementSpeed = movementSpeed;
    }

    @Override
    public Source getSource() {
        return Source.CHARACTER_RACE;
    }

    @Override
    public Boolean isRelevant(FeatureType featureType) {
        return (FeatureType.MOVEMENT_SPEED == featureType);
    }

    @Override
    public Pair<GamePiece, Object> getLimitations() {
        return null;
    }

    @Override
    public Object execute(FeatureType featureType) {	// INV Do we need to add movement type?
        return movementSpeed;
    }
}
