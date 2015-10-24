package com.industriousgnomes.dnd.character;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.apache.commons.lang3.tuple.Pair;

import com.industriousgnomes.dnd.character.clazz.CharacterClass;
import com.industriousgnomes.dnd.character.clazz.Commoner;
import com.industriousgnomes.dnd.character.feature.FeatureType;
import com.industriousgnomes.dnd.character.feature.Features;
import com.industriousgnomes.dnd.character.feature.ability.Ability;
import com.industriousgnomes.dnd.character.feature.ability.AbilityModifier;
import com.industriousgnomes.dnd.character.feature.ability.SavingThrow;
import com.industriousgnomes.dnd.character.feature.ability.Skill;
import com.industriousgnomes.dnd.character.feature.clazz.HitDie;
import com.industriousgnomes.dnd.character.race.CharacterRace;
import com.industriousgnomes.dnd.character.race.Human;

public class PlayerCharacter {
    private String         name;
    private Alignment      alignment;
    private int            damageTaken;
    private Experience     xp       = new Experience();
    private CharacterClass characterClass;
    private CharacterRace  characterRace;
    private Features       features = new Features();

    public PlayerCharacter(String name) {
        this.name = name;

        setAttributeModifierFeatures();
        setSavingThrowFeatures();
        setSkillFeatures();

        setAttributes(new HashSet<Pair<FeatureType, Integer>>());
        setCharacterClass(new Commoner(this));
        setCharacterRace(new Human(this));
    }

    public Features getFeatures() {
        return features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getHitPoints() {
        return features.total(FeatureType.HIT_POINTS);
    }

    public int getDamageTaken() {
        return damageTaken;
    }

    public void addDamageTaken(int damageTaken) {
        this.damageTaken += damageTaken;
    }

    public void setDamageTaken(int damageTaken) {
        this.damageTaken = damageTaken;
    }

    public boolean isAlive() {
        int hitPoints = getHitPoints();
        return (hitPoints > damageTaken) && (hitPoints > 0);
    }

    public int getExperiencePoints() {
        return xp.getExperiencePoints();
    }

    public void addExperiencePoints(int experiencePoints) {
        if (xp.add(experiencePoints)) {
            adjustHitDice();
        }
    }

    public void setExperiencePoints(int experiencePoints) {
        xp.setExperiencePoints(experiencePoints);
        adjustHitDice();
    }

    public int getExperienceLevel() {
        return xp.getExperienceLevel();
    }

    public int getProficiencyBonus() {
        return xp.getProficiencyBonus();
    }

    private void adjustHitDice() {
        int currentHitDice = features.count(FeatureType.HIT_DIE);

        if (getExperienceLevel() > currentHitDice) {
            adjustHitDiceUp(getExperienceLevel() - currentHitDice);
        } else if (getExperienceLevel() < currentHitDice) {
            adjustHitDiceDown(currentHitDice - getExperienceLevel());
        }
    }

    private void adjustHitDiceUp(int delta) {
        for (int i = 0; i < delta; i++) {
            features.add(new HitDie(this, characterClass.getHitDiceSides()));
        }
    }

    private void adjustHitDiceDown(int delta) {
        for (int i = 0; i < delta; i++) {
            features.removeLast(FeatureType.HIT_DIE);
        }
    }

    public void setAttributes(Collection<Pair<FeatureType, Integer>> abilityScores) {
        features.remove(Source.ABILITY_SCORE);

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.STRENGTH), Source.ABILITY_SCORE, FeatureType.STRENGTH_BASE, FeatureType.STRENGTH));

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.DEXTERITY), Source.ABILITY_SCORE, FeatureType.DEXTERITY_BASE, FeatureType.DEXTERITY));

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.INTELLIGENCE), Source.ABILITY_SCORE, FeatureType.INTELLIGENCE_BASE, FeatureType.INTELLIGENCE));

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.WISDOM), Source.ABILITY_SCORE, FeatureType.WISDOM_BASE, FeatureType.WISDOM));

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.CONSTITUTION), Source.ABILITY_SCORE, FeatureType.CONSTITUTION_BASE, FeatureType.CONSTITUTION));

        features.add(new Ability(this, findAbility(abilityScores, FeatureType.CHARISMA), Source.ABILITY_SCORE, FeatureType.CHARISMA_BASE, FeatureType.CHARISMA));
    }

    private Integer findAbility(Collection<Pair<FeatureType, Integer>> abilityScores, FeatureType featureType) {
        Optional<Pair<FeatureType, Integer>> abilityScore = abilityScores.stream().filter(a -> a.getLeft() == featureType).findFirst();
        if (abilityScore.isPresent()) {
            return abilityScore.get().getRight();
        } else {
            return 10;
        }
    }

    private void setAttributeModifierFeatures() {
        features.remove(Source.ABILITY_MODIFIER);

        features.add(new AbilityModifier(this, FeatureType.STRENGTH_ABILITY_MODIFIER, FeatureType.STRENGTH));
        features.add(new AbilityModifier(this, FeatureType.DEXTERITY_ABILITY_MODIFIER, FeatureType.DEXTERITY));
        features.add(new AbilityModifier(this, FeatureType.INTELLIGENCE_ABILITY_MODIFIER, FeatureType.INTELLIGENCE));
        features.add(new AbilityModifier(this, FeatureType.WISDOM_ABILITY_MODIFIER, FeatureType.WISDOM));
        features.add(new AbilityModifier(this, FeatureType.CONSTITUTION_ABILITY_MODIFIER, FeatureType.CONSTITUTION));
        features.add(new AbilityModifier(this, FeatureType.CHARISMA_ABILITY_MODIFIER, FeatureType.CHARISMA));
    }

    private void setSavingThrowFeatures() {
        features.remove(Source.SAVING_THROW);

        features.add(new SavingThrow(this, FeatureType.STRENGTH_SAVING_THROW, FeatureType.STRENGTH_ABILITY_MODIFIER));
        features.add(new SavingThrow(this, FeatureType.DEXTERITY_SAVING_THROW, FeatureType.DEXTERITY_ABILITY_MODIFIER));
        features.add(new SavingThrow(this, FeatureType.INTELLIGENCE_SAVING_THROW, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new SavingThrow(this, FeatureType.WISDOM_SAVING_THROW, FeatureType.WISDOM_ABILITY_MODIFIER));
        features.add(new SavingThrow(this, FeatureType.CONSTITUTION_SAVING_THROW, FeatureType.CONSTITUTION_ABILITY_MODIFIER));
        features.add(new SavingThrow(this, FeatureType.CHARISMA_SAVING_THROW, FeatureType.CHARISMA_ABILITY_MODIFIER));
    }

    private void setSkillFeatures() {
        features.remove(Source.SKILL);

        features.add(new Skill(this, FeatureType.ACROBATICS, FeatureType.DEXTERITY_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.ANIMAL_HANDLING, FeatureType.WISDOM_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.ARCANA, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.ATHLETICS, FeatureType.STRENGTH_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.DECPTION, FeatureType.CHARISMA_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.HISTORY, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.INSIGHT, FeatureType.WISDOM_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.INTIMIDATION, FeatureType.CHARISMA_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.INVESTIGATION, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.MEDICINE, FeatureType.WISDOM_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.NATURE, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.PERCEPTION, FeatureType.WISDOM_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.PERFORMANCE, FeatureType.CHARISMA_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.PERSUASION, FeatureType.CHARISMA_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.RELIGION, FeatureType.INTELLIGENCE_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.SLIGHT_OF_HAND, FeatureType.DEXTERITY_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.STEALTH, FeatureType.DEXTERITY_ABILITY_MODIFIER));
        features.add(new Skill(this, FeatureType.SURVIVAL, FeatureType.WISDOM_ABILITY_MODIFIER));
    }

    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public CharacterRace getCharacterRace() {
        return characterRace;
    }

    public void setCharacterRace(CharacterRace characterRace) {
        this.characterRace = characterRace;
    }

    // TODO attack modifier based on level
}
