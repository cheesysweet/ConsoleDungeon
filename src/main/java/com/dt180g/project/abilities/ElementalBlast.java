package com.dt180g.project.abilities;

import com.dt180g.project.support.Constants;

/**
 * ElementalBlast ability used by Wizard, SkeletonMage and Lich Lord.
 * @author Anton Byström
 */
public class ElementalBlast extends BaseAbility implements Constants{
    private final String magicalPhrase;
    private final String element;

    /**
     * Sets the AP and Energy cost to the BaseAbility class.
     * Gets the element type used for the ability.
     * Sets what Magical phrased used by the ability from Constants.
     * @param element element of the ability.
     */
    public ElementalBlast(String element){
        super(Constants.HIGHEST_AP_COST, Constants.HIGH_ENERGY_COST);
        this.element = element;
        this.magicalPhrase = Constants.MAGICAL_PHRASE_1;
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
     * Used to mark that the ability does not heal.
     * @return false
     */
    @Override
    public boolean isHeal(){
        return false;
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
     * toString with AP/Energy cost and amount of targets to attack, amount of damage, target to attack.
     * @param dmg amount of damage.
     * @param target true if enemy will be attacked and false if hero will be attacked.
     * @return true if the ability was executed.
     */
    @Override
    public boolean execute(int dmg, boolean target){
        return performAbility("%s (-%d AP, -%d Energy)".formatted(this.toString(),
                this.getActionPointCost(), this.getEnergyCost()), this.getAmountOfTargets(), dmg, target);
    }

    /**
     * Creates a string used for the attack with magicalPhrase, element used and name of attack.
     * @return attack phrase.
     */
    @Override
    public String toString(){
        return "%s: %s %s".formatted(this.magicalPhrase, element, Constants.ABILITY_ELEMENTAL_BLAST);
    }
}
