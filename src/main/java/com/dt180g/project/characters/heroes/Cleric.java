package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedHeal;
import com.dt180g.project.abilities.GroupHeal;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new Cleric
 * @author Anton Byström
 */
public class Cleric extends BaseHero{

    /**
     * Gets the name of the new cleric and gives that to BaseHero together with attribute values for
     * the cleric class.
     * Equips the hero with cleric class abilities/items.
     * @param name for class.
     */
    public Cleric(String name){
        super("%s The %s".formatted(name, Constants.HERO_CLERIC), Constants.ATTRIBUTE_VALUES_CLERIC_HERO);
        this.addAbilities(List.of(new WeaponAttack(), new FocusedHeal(), new GroupHeal()));
        equipHero(this.getClass());
    }
}
