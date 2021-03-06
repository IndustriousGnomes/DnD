package com.industriousgnomes.dnd.character.clazz;

import com.industriousgnomes.dnd.Dice;
import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Feature;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;
import com.industriousgnomes.dnd.character.feature.clazz.InitialHitDie;
import com.industriousgnomes.dnd.character.feature.proficiency.SavingThrow;

public abstract class CharacterClass {

    protected PlayerCharacter character;
    private Dice              hitDie;
    Features                  features;

    public CharacterClass(PlayerCharacter character) {
        this.character = character;
        features = character.getFeatures();
        features.remove(Source.CHARACTER_CLASS);
    }

    public abstract String getClassName();

    public void addFeature(Feature feature) {
        features.add(feature);
    }

    public Dice getHitDie() {
        return hitDie;
    }

    protected void setHitDie(Dice hitDie) {
        this.hitDie = hitDie;
        features.remove(FeatureType.HIT_DIE);
        features.add(new InitialHitDie(character, hitDie));
    }

    public void addSkill(FeatureType skill) {
        addFeature(new SavingThrow(character, Source.CHARACTER_CLASS, skill));
    }

}
