package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new Ranger
 * @author Anton Byström
 */
public class Ranger extends BaseHero{

    /**
     * Gets the name of the new ranger and gives that to BaseHero together with attribute values for
     * the ranger class.
     * Equips the hero with ranger class abilities/items.
     * @param name for class.
     */
    public Ranger(String name){
        super("%s The %s".formatted(name, Constants.HERO_RANGER), Constants.ATTRIBUTE_VALUES_RANGER_HERO);
        this.addAbilities(List.of(new WeaponAttack(), new FocusedShot(), new SprayOfArrows()));
        equipHero(this.getClass());
    }
}
