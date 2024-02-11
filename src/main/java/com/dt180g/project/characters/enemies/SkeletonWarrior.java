package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.HeavyAttack;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.abilities.Whirlwind;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new SkeletonWarrior
 * @author Anton Byström
 */
public class SkeletonWarrior extends BaseEnemy{

    /**
     * Gets the number of the new warrior and gives that to BaseEnemy together with attribute values for the enemy.
     * Equips BaseEnemy with SkeletonWarrior abilities/items.
     * @param number number.
     */
    public SkeletonWarrior(int number){
        super("%s %s".formatted(Constants.ENEMY_SKELETON_WARRIOR, number), Constants.ATTRIBUTE_VALUES_SKELETON_WARRIOR);
        this.addAbilities(List.of(new WeaponAttack(), new HeavyAttack(), new Whirlwind()));
        this.equipEnemy(List.of("Axe", "Sword", "Shield"));
    }
}
