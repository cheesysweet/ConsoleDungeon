package com.dt180g.project.stats;

import com.dt180g.project.support.Constants;
import com.dt180g.project.support.Randomizer;

import java.util.Arrays;
import java.util.List;

/**
 * Creates a Instance that can be accessed to get all the different attributes, traits and combatStats.
 * @author Anton Byström
 */
public class StatsManager {
    public static final StatsManager INSTANCE = new StatsManager();
    private final List<String> attributeNames;
    private final List<String> traitNames;
    private final List<String> combatStatNames;

    /**
     * Creates lists for all the different types of attributes, traits and combatStats.
     */
    private StatsManager(){
        this.attributeNames = Arrays.asList(Constants.ATTRIBUTE_STRENGTH, Constants.ATTRIBUTE_DEXTERITY,
                Constants.ATTRIBUTE_INTELLIGENCE, Constants.ATTRIBUTE_WILLPOWER);

        this.traitNames = Arrays.asList(Constants.TRAIT_ENERGY, Constants.TRAIT_VITALITY,
                Constants.TRAIT_ATTACK_RATE, Constants.TRAIT_DEFENSE_RATE);

        this.combatStatNames = Arrays.asList(Constants.COMBAT_STAT_PHYSICAL_POWER, Constants.COMBAT_STAT_ACTION_POINTS,
                Constants.COMBAT_STAT_MAGIC_POWER, Constants.COMBAT_STAT_HEALING_POWER);
    }

    /**
     * Gets a random attribute from the list of attributes.
     * @return a attribute.
     */
    public String getRandomAttributeName(){
        return this.attributeNames.get(Randomizer.INSTANCE.getRandomValue(attributeNames.size()-1));
    }

    /**
     * Gets a random trait from the list of traits.
     * @return a trait.
     */
    public String getRandomTraitName(){
        return this.traitNames.get(Randomizer.INSTANCE.getRandomValue(traitNames.size()-1));
    }

    /**
     * A List of all the attribute names.
     * @return List of attributes
     */
    public List<String> getAttributeNames(){
        return this.attributeNames;
    }

    /**
     * A List of all the trait names.
     * @return List of traits.
     */
    public List<String> getTraitNames(){
        return this.traitNames;
    }

    /**
     * A list of all the combat stat names.
     * @return List of combatStats.
     */
    public List<String> getCombatStatNames(){
        return this.combatStatNames;
    }
}
