package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.ElementalBlast;
import com.dt180g.project.abilities.ElementalBolt;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new SkeletonMage
 * @author Anton Byström
 */
public class SkeletonMage extends BaseEnemy{

    /**
     * Gets the number of the new mage and gives that to BaseEnemy together with attribute values for the enemy.
     * Equips BaseEnemy with SkeletonMage abilities/items.
     * @param number number.
     */
    public SkeletonMage(int number){
        super("%s %s".formatted(Constants.ENEMY_SKELETON_MAGE, number), Constants.ATTRIBUTE_VALUES_SKELETON_MAGE);
        this.addAbilities(List.of(new WeaponAttack(), new ElementalBolt(Constants.ELEMENT_AIR),
                new ElementalBolt(Constants.ELEMENT_FIRE), new ElementalBolt(Constants.ELEMENT_ICE),
                new ElementalBlast(Constants.ELEMENT_AIR), new ElementalBlast(Constants.ELEMENT_FIRE),
                new ElementalBlast(Constants.ELEMENT_ICE)));
        this.equipEnemy(List.of("Staff", "Wand"));
    }
}
