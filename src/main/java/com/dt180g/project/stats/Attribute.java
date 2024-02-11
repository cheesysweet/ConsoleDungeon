package com.dt180g.project.stats;

/**
 * Represents the characters Strength, Dexterity, Intelligence and Willpower.
 * @author Anton Byström
 */
public class Attribute extends BaseStat{

    /**
     * Gets the name and value for the attribute and gives them to BaseStat for storage and usage.
     * Gets bonus from weapons.
     * @param name name of the attribute.
     * @param value value of the attribute.
     */
    public Attribute(String name , int value){
        super(name, value);
    }
}
