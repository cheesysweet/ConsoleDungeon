package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new SkeletonArcher
 * @author Anton Byström
 */
public class SkeletonArcher extends BaseEnemy{

    /**
     * Gets the number of the new archer and gives that to BaseEnemy together with attribute values for the enemy.
     * Equips BaseEnemy with SkeletonArcher abilities/items.
     * @param number number.
     */
    public SkeletonArcher(int number){
        super("%s %s".formatted(Constants.ENEMY_SKELETON_ARCHER, number), Constants.ATTRIBUTE_VALUES_SKELETON_ARCHER);
        this.addAbilities(List.of(new WeaponAttack(), new FocusedShot(), new SprayOfArrows()));
        this.equipEnemy(List.of("Bow", "Crossbow"));
    }
}
