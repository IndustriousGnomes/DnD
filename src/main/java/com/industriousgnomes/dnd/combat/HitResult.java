package com.industriousgnomes.dnd.combat;

public enum HitResult {
    MISS(0), HIT(1), CRITICAL(2);

    private final int damageMultiplier;

    HitResult(int damageMultiplier) {
        this.damageMultiplier = damageMultiplier;
    }

    public int damageMultiplier() {
        return damageMultiplier;
    }
}
