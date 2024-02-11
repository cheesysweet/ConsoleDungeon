package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.Constants;

import java.util.List;

/**
 * Creates a new Lich Lord
 * @author Anton Byström
 */
public class LichLord extends BaseEnemy{

    /**
     * Gives the name of Lich Lord together with base attributes to baseEnemy.
     * Equips BaseEnemy with Lich Lord class abilities/items.
     * Modifies the HP of Lich Lord.
     */
    public LichLord(){
        super(Constants.ENEMY_LICH_LORD, Constants.ATTRIBUTE_VALUES_LICH_LORD);
        this.addAbilities(List.of(new WeaponAttack(), new HeavyAttack(), new Whirlwind(), new FocusedHeal(),
                new ElementalBolt(Constants.ELEMENT_FIRE), new ElementalBlast(Constants.ELEMENT_FIRE)));
        this.getCharacterStats().adjustStatStaticModifier(Constants.TRAIT_VITALITY,
                (Constants.TRAIT_VITALITY_BASE_VALUE * Constants.BOSS_HEALTH_MULTIPLIER));
        this.equipEnemy(List.of("Axe", "Sword", "Shield"));
    }
}
