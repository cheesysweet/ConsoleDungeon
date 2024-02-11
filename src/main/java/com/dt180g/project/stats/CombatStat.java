package com.dt180g.project.stats;

import com.dt180g.project.support.Constants;

/**
 * Represents the characters Action Points(AP), Physical Power, Magical Power and Healing Power.
 * @author Anton Byström
 */
public class CombatStat extends BaseStat {
    private final BaseStat attributeReliance;
    private final BaseStat traitReliance;

    /**
     * Gets the reliance and calls BaseStat with CombatStat name with attributeReliance together with traitReliance.
     * AP relies on Dexterity, Power relies on Strength, Magical relies on Intelligence, Healing relies on Willpower.
     * @param statName name of the combat stat.
     * @param attributeReliance attributeValue relied to combatStat.
     * @param traitReliance attackRate set by constant
     */
    public CombatStat(String statName, BaseStat attributeReliance, BaseStat traitReliance){
        super(statName, attributeReliance.getBaseValue() + traitReliance.getBaseValue());
        this.attributeReliance = attributeReliance;
        this.traitReliance = traitReliance;
    }

    /**
     * Overrides the getBaseValue in BaseStats to return a new baseValue.
     * @return the modifiedValue * combat_stat_multiplier for attribute and trait reliance.
     */
    @Override
    public int getBaseValue(){
        return (int) Math.round((attributeReliance.getModifiedValue() * Constants.COMBAT_STAT_MULTIPLIER) +
                (traitReliance.getModifiedValue() * Constants.COMBAT_STAT_MULTIPLIER));
    }
}
