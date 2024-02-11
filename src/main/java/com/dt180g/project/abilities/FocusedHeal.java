package com.dt180g.project.abilities;

import com.dt180g.project.support.Constants;

/**
 * FocusedHeal ability used by Cleric and Lich Lord.
 * @author Anton Byström
 */
public class FocusedHeal extends BaseAbility implements Constants{
    private final String magicalPhrase;

    /**
     * Sets the AP and Energy cost to the BaseAbility class.
     * Sets what Magical phrased used by the ability from Constants.
     */
    public FocusedHeal(){
        super(Constants.MEDIUM_AP_COST, Constants.LOW_ENERGY_COST);
        this.magicalPhrase = Constants.MAGICAL_PHRASE_4;
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
    public int getAmountOfTargets() {
        return Constants.ABILITY_SINGLE_TARGET;
    }

    /**
     * Used to execute the ability by calling performAbility in BaseAbility with the formatted attackPhrase from
     * toString with AP/Energy cost and amount of targets to attack, negative amount of damage * single target
     * multiplier, target to attack.
     * @param dmg amount of damage.
     * @param target true if enemy will be attacked and false if hero will be attacked.
     * @return true if the ability was executed.
     */
    @Override
    public boolean execute(int dmg, boolean target){
        return performAbility("%s (-%d AP, -%d Energy)".formatted(this.toString(),
        this.getActionPointCost(), this.getEnergyCost()), this.getAmountOfTargets(),
                -dmg * Constants.SINGLE_TARGET_ABILITY_MULTIPLIER, target);
    }

    /**
     * Creates a string used for the attack with magicalPhrase and name of attack.
     * @return attack phrase.
     */
    @Override
    public String toString(){
        return "%s: %s".formatted(this.magicalPhrase, Constants.ABILITY_FOCUSED_HEAL);
    }
}
