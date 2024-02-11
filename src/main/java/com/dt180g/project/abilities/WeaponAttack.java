package com.dt180g.project.abilities;

import com.dt180g.project.support.Constants;

/**
 * WeaponAttack ability used by all characters as basic attack.
 * @author Anton Byström
 */
public class WeaponAttack extends BaseAbility implements Constants{

    /**
     * Sets the AP and Energy cost to the BaseAbility class.
     */
    public WeaponAttack(){
        super(Constants.LOWEST_AP_COST, 0);
    }

    /**
     * Used to mark that the ability is not magical.
     * @return false.
     */
    @Override
    public boolean isMagic() {
        return false;
    }

    /**
     * Used to mark that the ability does not heal.
     * @return false.
     */
    @Override
    public boolean isHeal() {
        return false;
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
     * toString withAP/Energy cost and amount of targets to attack, amount of damage * single target multiplier,
     * target to attack.
     * @param dmg amount of damage.
     * @param target true if enemy will be attacked and false if hero will be attacked.
     * @return true if the ability was executed.
     */
    @Override
    public boolean execute(int dmg, boolean target){
        return performAbility("%s (-%d AP, -%d Energy)".formatted(this.toString(),
                this.getActionPointCost(), this.getEnergyCost()), getAmountOfTargets(),
                dmg, target);
    }

    /**
     * Creates a string used for the attack with name of attack.
     * @return attack phrase.
     */
    @Override
    public String toString(){
        return Constants.ABILITY_WEAPON_ATTACK;
    }
}
