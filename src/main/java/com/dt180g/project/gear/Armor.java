package com.dt180g.project.gear;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.Constants;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

/**
 * Used to get the armour stats from the HashMapped input.
 * @author Anton Byström.
 */
public class Armor extends BaseGear {
    private final int protection;
    private final String material;
    private final Trait trait;

    /**
     * Gives the armour name, type and restrictions to BaseGear.
     * Sets the armour protection value.
     * Sets the amour material type.
     * Sets the trait of the armour by making a new trait with random armour trait and a random value.
     * @param armor HashMap that contains the armours different name, type, restriction, damage, wield and the values
     *              for those keys.
     */
    public Armor(Map<String, String> armor){
        super(armor.get("name"), armor.get("type"), armor.get("restriction"));
        this.protection = Integer.parseInt(armor.get("protection"));
        this.material = armor.get("material");
        this.trait = new Trait(
                StatsManager.INSTANCE.getRandomTraitName(),
                Randomizer.INSTANCE.getRandomValue(1, Constants.ARMOR_STAT_VALUE_UPPER_BOUND)
        );
    }

    /**
     * Gets the armours protection value.
     * @return protection value.
     */
    public int getProtection(){
        return this.protection;
    }

    /**
     * Gets the armours material type.
     * @return material type.
     */
    public String getMaterial(){
        return this.material;
    }

    /**
     * Gets the armours trait.
     * @return armour trait.
     */
    @Override
    public BaseStat getStat(){
        return this.trait;
    }

    /**
     * Formats a string with the gear name from BaseGear and the name of the trait.
     * @return armourName of trait.
     */
    @Override
    public String toString(){
        return "%s of %s".formatted(super.toString(), this.getStat().getStatName());
    }
}
