package com.industriousgnomes.dnd.character.race;

import java.util.Collection;
import java.util.HashSet;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.Features;
import com.industriousgnomes.dnd.character.feature.race.NaturalArmorClass;

public abstract class CharacterRace {

    protected PlayerCharacter  character;
    Features                   features;

    private int                age;
    private int                size;
    private Gender             gender;
    private Collection<String> languages = new HashSet<>();

    public CharacterRace(PlayerCharacter character) {
        this.character = character;
        features = character.getFeatures();
        features.remove(Source.CHARACTER_RACE);

        features.add(new NaturalArmorClass(character, 10));
    }

    public abstract String getRace();

    public abstract String getSubRace();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Collection<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Collection<String> languages) {
        this.languages = languages;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
