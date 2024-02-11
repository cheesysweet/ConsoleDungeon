package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.ElementalBlast;
import com.dt180g.project.abilities.ElementalBolt;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new Wizard
 * @author Anton Byström
 */
public class Wizard extends BaseHero{

    /**
     * Gets the name of the new wizard and gives that to BaseHero together with attribute values for
     * the wizard class.
     * Equips the hero with wizard class abilities/items.
     * @param name for class.
     */
    public Wizard(String name){
        super("%s The %s".formatted(name, Constants.HERO_WIZARD), Constants.ATTRIBUTE_VALUES_WIZARD_HERO);
        this.addAbilities(List.of(new WeaponAttack(), new ElementalBolt(Constants.ELEMENT_AIR),
                new ElementalBolt(Constants.ELEMENT_FIRE), new ElementalBolt(Constants.ELEMENT_ICE),
                new ElementalBlast(Constants.ELEMENT_AIR), new ElementalBlast(Constants.ELEMENT_FIRE),
                new ElementalBlast(Constants.ELEMENT_ICE)));
        equipHero(this.getClass());
    }
}
