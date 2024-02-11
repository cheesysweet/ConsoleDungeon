package com.dt180g.project.abilities;

import com.dt180g.project.support.Constants;

/**
 * GroupHeal ability used by Cleric.
 * @author Anton Byström
 */
public class GroupHeal extends BaseAbility implements Constants{
    private final String magicalPhrase;

    /**
     * Sets the AP and Energy cost to the BaseAbility class.
     * Sets what Magical phrased used by the ability from Constants.
     */
    public GroupHeal(){
        super(Constants.HIGHEST_AP_COST, Constants.HIGH_ENERGY_COST);
        this.magicalPhrase = Constants.MAGICAL_PHRASE_3;
    }

    /**
     * Used to mark that the ability is magical.
     * @return true.
     */
    @Override
    public boolean isMagic(){
        return true;
    }

    /**
     * Used to mark that the ability heals.
     * @return true.
     */
    @Override
    public boolean isHeal(){
        return true;
    }

    /**
     * Gets the constant for amount of targets the ability attacks.
     * @return amount as a integer.
     */
    @Override
    public int getAmountOfTargets(){
        return Constants.ABILITY_GROUP_TARGET;
    }

    /**
     * Used to execute the ability by calling performAbility in BaseAbility with the formatted attackPhrase from
     * toString with AP/Energy cost and amount of targets to attack, negative amount of damage, target to attack.
     * @param dmg amount of damage.
     * @param target true if enemy will be attacked and false if hero will be attacked.
     * @return true if the ability was executed.
     */
    @Override
    public boolean execute(int dmg, boolean target){
        return performAbility("%s (-%d AP, -%d Energy)".formatted(this.toString(),
        this.getActionPointCost(), this.getEnergyCost()), this.getAmountOfTargets(), -dmg, target);
    }

    /**
     * Creates a string used for the attack with magicalPhrase and name of attack.
     * @return attack phrase.
     */
    @Override
    public String toString(){
        return "%s: %s".formatted(this.magicalPhrase, Constants.ABILITY_GROUP_HEAL);
    }
}
