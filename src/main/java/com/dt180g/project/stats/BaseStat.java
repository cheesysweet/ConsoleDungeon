package com.dt180g.project.stats;

import com.dt180g.project.support.Constants;

/**
 * Responsible for holding the attribute/trait base stats and modified values.
 * @author Anton Byström
 */
public abstract class BaseStat {
    private final String statName;
    private final int baseValue;
    private int staticModifier;
    private int dynamicModifier;

    /**
     * Gets the name and value for the attribute/trait and stores it.
     * @param name name of the attribute/trait.
     * @param value value of the attribute/trait.
     */
    protected BaseStat(String name, int value){
        this.statName = name;
        this.baseValue = value;
    }

    /**
     * Gets the stats name
     * @return statName.
     */
    public String getStatName(){
        return this.statName;
    }

    /**
     * Gets the baseValue.
     * @return baseValue.
     */
    public int getBaseValue(){ return this.baseValue; }

    /**
     * Gets the modified value that are the total of the baseValue + totalModifier.
     * @return modifiedValue.
     */
    public int getModifiedValue(){
        return this.getBaseValue() + this.getTotalModifier();
    }

    /**
     * Gets the total modifier that are the total of the staticModifier and dynamicModifier.
     * @return totalModifier value.
     */
    public int getTotalModifier(){
        return this.staticModifier + this.dynamicModifier;
    }

    /**
     * Sets the value of the staticModifier, used before gameStart.
     * @param modifier value to add to staticModifier.
     */
    public void adjustStaticModifier(int modifier){
        this.staticModifier += modifier;
    }

    /**
     * Sets the value of the dynamicModifier, used during gameRun.
     * @param modifier value to add to dynamicModifier.
     */
    public void adjustDynamicModifier(int modifier){
        this.dynamicModifier += modifier;
    }

    /**
     * Sets the dynamicModified value to 0 before each dungeon level.
     */
    public void resetDynamicModifier(){
        this.dynamicModifier = 0;
    }

    /**
     * Creates a formatted string with the statName, getModifiedValue and getTotalModifier with colors.
     * @return String of stat name, modifiedValue and totalModifier.
     */
    @Override
    public String toString(){

        return "%s%-16s %s%-4s %s+%-4s%s".formatted(Constants.ANSI_GREEN, this.statName,
                Constants.ANSI_CYAN, this.getModifiedValue(),
                Constants.ANSI_YELLOW, this.getTotalModifier(), Constants.ANSI_RESET);
    }
}
