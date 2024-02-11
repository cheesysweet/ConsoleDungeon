package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;

/**
 * Used for storing abilities ActionPoint and Energy cost.
 * @author Anton Byström
 */
public abstract class BaseAbility{
    private final int actionPointCost;
    private final int energyCost;

    /**
     * Gets the AP cost and Energy cost from derivative abilities.
     * @param AP ActionPointCost
     * @param EN Energy cost
     */
    protected BaseAbility(int AP, int EN){
        actionPointCost = AP;
        energyCost = EN;
    }

    /**
     * Used by derivative to perform abilities.
     * @param attackPhrase formatted string of attackPhrase of ability.
     * @param targetAmount amount of targets.
     * @param dmg amount of dmg
     * @param target true if enemy will be targeted and false if hero will be targeted
     * @return true if there are any target to attack, false if there is no target.
     */
    protected boolean performAbility(String attackPhrase, int targetAmount, int dmg, boolean target){
        return GameEngine.INSTANCE.characterAttack(new AbilityInfo(attackPhrase, targetAmount,
                dmg, target, isMagic(), isHeal()));
    }

    /**
     * Gets the abilities ActionPointCost
     * @return AP cost
     */
    public int getActionPointCost() {
        return actionPointCost;
    }

    /**
     * Gets the abilities EnergyCost
     * @return Energy cost
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Used by derivative to return true if the ability is magical.
     * @return true if the ability is magical, false if it´s not.
     */
    public abstract boolean isMagic();

    /**
     * Used by derivative to return true if the ability will heal.
     * @return true if the ability will heal.
     */
    public abstract boolean isHeal();

    /**
     * Used by derivative to return amount of targets
     * @return integer for the amount of targets to attack.
     */
    public abstract int getAmountOfTargets();

    /**
     * Used by derivative to execute the action.
     * @param dmg amount of damage.
     * @param target true if enemy will be attacked and false if hero will be attacked.
     * @return true if the ability was executed
     */
    public abstract boolean execute(int dmg, boolean target);
}
