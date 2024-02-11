package com.dt180g.project.stats;

/**
 * Represents the characters Vitality, energy, Attack Rate and Defense Rate.
 * @author Anton Byström
 */
public class Trait extends BaseStat{

    /**
     * Gets the name and value for the trait and then gives them to BaseStat for storing and usage.
     * Gets a bonus for armour pieces.
     * @param name name of the trait.
     * @param value value of the trait.
     */
    public Trait(String name, int value){
        super(name, value);
    }

}
