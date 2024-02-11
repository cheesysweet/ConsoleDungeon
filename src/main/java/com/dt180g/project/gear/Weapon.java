package com.dt180g.project.gear;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.Constants;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

/**
 * Used to get the weapons stats from the HashMapped input.
 * @author Anton Byström
 */
public class Weapon extends BaseGear{
    private final int damage;
    private final String wield;
    private final Attribute attribute;

    /**
     * Gives the weapons name, type and restrictions to BaseGear.
     * Sets the weapons damage value.
     * Sets the weapons wield type.
     * Sets the attribute of the weapon by making a new Attribute with random weapon attribute and a random value.
     * @param weapon HashMap that contains the weapons different name, type, restriction, damage, wield and the values
     *               for those keys.
     */
    public Weapon(Map<String, String> weapon){
        super(weapon.get("name"), weapon.get("type"), weapon.get("restriction"));
        this.damage = Integer.parseInt(weapon.get("damage"));
        this.wield = weapon.get("wield");
        this.attribute = new Attribute(
                StatsManager.INSTANCE.getRandomAttributeName(),
                Randomizer.INSTANCE.getRandomValue(1, Constants.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND)
        );
    }

    /**
     * Gets the weapons damage value.
     * @return weapon damage.
     */
    public int getDamage(){
        return this.damage;
    }

    /**
     * Gets the wield attribute of the weapon if it´s one or two handed.
     * @return String one or two : handed weapon.
     */
    public String getWield(){
        return this.wield;
    }

    /**
     * Gets the attribute of the weapon.
     * @return attribute.
     */
    @Override
    public BaseStat getStat(){
        return this.attribute;
    }

    /**
     * Checks if the weapon is two handed by using getWield and check if the string contains Two Handed.
     * @return true if the weapon is two handed else false.
     */
    public boolean isTwoHanded(){
        return this.getWield().contains("Two Handed");
    }

    /**
     * Formats a string with the gear name from BaseGear and the name of the attribute.
     * @return GearName of attribute.
     */
    @Override
    public String toString(){
        return "%s of %s".formatted(super.toString(), this.getStat().getStatName());
    }
}
