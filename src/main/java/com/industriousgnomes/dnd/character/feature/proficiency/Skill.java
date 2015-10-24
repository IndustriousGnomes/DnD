package com.industriousgnomes.dnd.character.feature.proficiency;

import com.industriousgnomes.dnd.character.PlayerCharacter;
import com.industriousgnomes.dnd.character.Source;
import com.industriousgnomes.dnd.character.feature.FeatureType;

public class Skill extends Proficiency {

    public Skill(PlayerCharacter character, Source source, FeatureType skillType, FeatureType... relevancy) {

        super(character, source, skillType, relevancy);
    }

    @Override
    public Object execute(FeatureType featureType) {
        return character.getProficiencyBonus();
    }

}
