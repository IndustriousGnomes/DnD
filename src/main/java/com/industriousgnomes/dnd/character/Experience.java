package com.industriousgnomes.dnd.character;

public class Experience {

    static final int EXERPIENCE_PER_LEVEL[] = { 0, 300, 900, 2_700, 6_500, 14_000, 23_000, 34_000, 48_000, 64_000, 85_000, 100_000, 120_000, 140_000, 165_000, 195_000, 225_000, 265_000, 305_000, 355_000 };

    private int      experiencePoints       = 0;
    private int      currentLevel           = 1;

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public boolean add(int experiencePoints) {
        this.experiencePoints += experiencePoints;
        int calculateExperienceLevel = calculateExperienceLevel(getExperiencePoints());
        if (calculateExperienceLevel != currentLevel) {
            currentLevel = calculateExperienceLevel;
            return true;
        }
        return false;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
        currentLevel = calculateExperienceLevel(getExperiencePoints());
    }

    public int getExperienceLevel() {
        return currentLevel;
    }

    public int getProficiencyBonus() {
        return (currentLevel - 1) / 4 + 2;
    }

    private int calculateExperienceLevel(int xp) {
        int lvl = 0;
        while (lvl < EXERPIENCE_PER_LEVEL.length && xp >= EXERPIENCE_PER_LEVEL[lvl]) {
            lvl++;
        }
        return lvl;
    }
}
