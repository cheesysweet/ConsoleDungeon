package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.HeavyAttack;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.abilities.Whirlwind;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new Warrior
 * @author Anton Byström
 */
public class Warrior extends BaseHero{

    /**
     * Gets the name of the new warrior and gives that to BaseHero together with attribute values for
     * the warrior class.
     * Equips the hero with warrior class stats/abilities.
     * @param name for class.
     */
    public Warrior(String name){
        super("%s The %s".formatted(name, Constants.HERO_WARRIOR), Constants.ATTRIBUTE_VALUES_WARRIOR_HERO);
        this.addAbilities(List.of(new WeaponAttack(), new HeavyAttack(), new Whirlwind()));
        equipHero(this.getClass());
    }
}
