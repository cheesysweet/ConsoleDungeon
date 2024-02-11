package com.dt180g.project.characters;

import com.dt180g.project.stats.*;
import com.dt180g.project.support.Constants;
import com.dt180g.project.support.IOHelper;

import java.util.*;

/**
 * Handles creation of attributes/traits/stats and
 * gives access to the different stats/traits/attributes.
 * @author Anton Byström
 */
public class CharacterStats {
    private final Map<String, BaseStat> stats = new HashMap<>();

    /**
     * Creates the characters attributes/traits/stats by using the constant values
     * and getting the characters base attribute values for creating values
     * @param stat characters base attributes.
     */
    public CharacterStats(List<Integer> stat){
        /*
        Uses a for loop to add the names for attributes from statsManager together with a new Attribute with
        base value and put it in Map<String, BaseStat> HashMap.
         */
        for(int i=0; i<StatsManager.INSTANCE.getAttributeNames().size(); i++){
            this.stats.put(StatsManager.INSTANCE.getAttributeNames().get(i),
                    new Attribute(StatsManager.INSTANCE.getAttributeNames().get(i),
                    stat.get(i) * Constants.ATTRIBUTE_BASE_VALUE));
        }

        /*
        Creates a List  of the different base values and the uses a for loop to add the trait names from StatsManager
        together with a new Trait with base value and put it in Map<String, BaseStat> HashMap.
         */
        List<Integer> traitBaseValue = Arrays.asList(Constants.TRAIT_ENERGY_BASE_VALUE,
                Constants.TRAIT_VITALITY_BASE_VALUE, Constants.TRAIT_ATTACK_RATE_BASE_VALUE,
                Constants.TRAIT_DEFENCE_RATE_BASE_VALUE);

        for (int i=0; i<StatsManager.INSTANCE.getTraitNames().size(); i++){
            this.stats.put(StatsManager.INSTANCE.getTraitNames().get(i),
                    new Trait(StatsManager.INSTANCE.getTraitNames().get(i), traitBaseValue.get(i)));
        }

        /*
        Uses a for loop to add the names for combatStats from statsManager together with a new CombatStat with
        attribute value and attack rate and put it in Map<String, BaseStat> HashMap.
         */
        for(int i=0; i<StatsManager.INSTANCE.getCombatStatNames().size(); i++){
            this.stats.put(StatsManager.INSTANCE.getCombatStatNames().get(i),
                    new CombatStat(StatsManager.INSTANCE.getCombatStatNames().get(i),
                            getStat(StatsManager.INSTANCE.getAttributeNames().get(i)),
                            getStat(Constants.TRAIT_ATTACK_RATE)));
        }

    }

    /**
     * Gets the stats value of stated string.
     * @param keyValue given keyValue.
     * @return value of keyValue.
     */
    public BaseStat getStat(String keyValue){
        return this.stats.get(keyValue);
    }

    /**
     * Gets the modifiedValue of stated keyValue.
     * @param keyValue given keyValue.
     * @return modified value of keyValue
     */
    public int getStatValue(String keyValue){
        return this.getStat(keyValue).getModifiedValue();
    }

    /**
     * Gets the base value of the characters action points.
     * @return base value of AP.
     */
    public int getTotalActionPoints(){
        return this.getStat(Constants.COMBAT_STAT_ACTION_POINTS).getBaseValue();
    }

    /**
     * Gets the modified value of the characters action points.
     * @return modified value of AP.
     */
    public int getCurrentActionPoints(){
        return this.getStatValue(Constants.COMBAT_STAT_ACTION_POINTS);
    }

    /**
     * Gets the base value of he characters hitPoints.
     * @return base value of HP
     */
    public int getTotalHitPoints(){
        return this.getStat(Constants.TRAIT_VITALITY).getBaseValue();
    }

    /**
     * Gets the modified value of the characters hitPoints.
     * @return modified value of HP.
     */
    public int getCurrentHitPoints(){
        return this.getStatValue(Constants.TRAIT_VITALITY);
    }

    /**
     * Gets the base value of characters Energy level.
     * @return base value of Energy level.
     */
    public int getTotalEnergyLevel(){
        return this.getStat(Constants.TRAIT_ENERGY).getBaseValue();
    }

    /**
     * Gets the modified value of characters Energy level.
     * @return modified value of Energy level.
     */
    public int getCurrentEnergyLevel(){
        return this.getStatValue(Constants.TRAIT_ENERGY);
    }

    /**
     * Gets the characters defence value.
     * @return defence value.
     */
    public int getDefenceRate(){
        return this.getStatValue(Constants.TRAIT_DEFENSE_RATE);
    }

    /**
     * Gets the characters attack rate.
     * @return attack rate value.
     */
    public int getAttackRate(){
        return this.getStatValue(Constants.TRAIT_ATTACK_RATE);
    }

    /**
     * Gets the characters physical power.
     * @return physical power value.
     */
    public int getPhysicalPower(){
        return this.getStatValue(Constants.COMBAT_STAT_PHYSICAL_POWER);
    }

    /**
     * Gets the characters magical power.
     * @return magical power value.
     */
    public int getMagicPower(){
        return this.getStatValue(Constants.COMBAT_STAT_MAGIC_POWER);
    }

    /**
     * Gets the characters healing power.
     * @return healing power value.
     */
    public int getHealingPower(){
        return this.getStatValue(Constants.COMBAT_STAT_HEALING_POWER);
    }

    /**
     * Uses this dynamic modifier to alter action points during gameplay.
     * @param value amount of AP to be modified.
     */
    public void adjustActionPoints(int value){
        this.adjustStatDynamicModifier(Constants.COMBAT_STAT_ACTION_POINTS, value);
    }

    /**
     * Uses this dynamic modifier to alter hit points during gameplay.
     * @param value amount of hp to be modified.
     */
    public void adjustHitPoints(int value){
        this.adjustStatDynamicModifier(Constants.TRAIT_VITALITY, value);
    }

    /**
     * Uses this dynamic modifier to alter energy levels during gameplay.
     * @param value amount of energy to be modified.
     */
    public void adjustEnergyLevel(int value){
        this.adjustStatDynamicModifier(Constants.TRAIT_ENERGY, value);
    }

    /**
     * Changes the value of stated name before gameplay.
     * @param name name of item to changed.
     * @param value amount the item value will be change.
     */
    public void adjustStatStaticModifier(String name, int value){
        this.getStat(name).adjustStaticModifier(value);
    }

    /**
     * Changes the value of stated name during gameplay.
     * @param name name of item to be changed.
     * @param value value stated name will be changed.
     */
    public void adjustStatDynamicModifier(String name, int value){
        this.getStat(name).adjustDynamicModifier(value);
    }

    /**
     * Resets the dynamic modified action points.
     */
    public void resetActionPoints(){
        this.getStat(Constants.COMBAT_STAT_ACTION_POINTS).resetDynamicModifier();
    }

    /**
     * Resets the dynamic modified hit points.
     */
    public void resetHitPoints(){
        this.getStat(Constants.TRAIT_VITALITY).resetDynamicModifier();
    }

    /**
     * Resets the dynamic modified energy level.
     */
    public void resetEnergyLevel(){
        this.getStat(Constants.TRAIT_ENERGY).resetDynamicModifier();
    }

    /**
     * Used to display the Hero´s details.
     * Creates a table that displays the Hero´s stats.
     * @return A string with the help of IOHelper that formats the Lists. Used in BaseCharacter to print full details.
     */
    @Override
    public String toString(){
        List<List<String>> statsList = new ArrayList<>();

        /*
        Creates a list with all the different attributes, traits, combatStats that are then used with IOHelper
        to create it as a String.
         */
        statsList.add(List.of(("""
                %s | %s | %s
                %s | %s | %s
                %s | %s | %s
                %s | %s | %s
                """).formatted(stats.get("Strength").toString(), stats.get("Vitality").toString(),
                stats.get("Action Points").toString(), stats.get("Dexterity").toString(),
                stats.get("Energy").toString(), stats.get("Physical Power").toString(),
                stats.get("Intelligence").toString(), stats.get("Attack Rate").toString(),
                stats.get("Magical Power").toString(), stats.get("Willpower").toString(),
                stats.get("Defense Rate").toString(), stats.get("Healing Power").toString())));



        return Constants.ANSI_BLUE + "STATISTICS\n" + Constants.ANSI_RESET + IOHelper.formatAsTable(statsList);
    }
}
