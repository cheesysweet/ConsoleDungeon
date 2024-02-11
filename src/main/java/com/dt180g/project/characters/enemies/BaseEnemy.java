package com.dt180g.project.characters.enemies;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;

import java.util.List;

/**
 * Used for equipping enemies with abilities and equipment.
 * Getting the characters information.
 * Performs turns.
 * @author Anton Byström
 */
public abstract class BaseEnemy extends BaseCharacter {
    private final String characterName;

    /**
     *  Used by derivative to get Energy name and create new CharacterStats with the Enemy base attributes.
     * @param name of hero.
     * @param attribute lists of base attributes of enemy.
     */
    protected BaseEnemy(String name, List<Integer> attribute){
        super(new CharacterStats(attribute));
        this.characterName = name;
    }

    /**
     * Uses the enemy class to get the enemies special abilities.
     * Gets random weapon and armour and adds it to CharacterEquipment.
     * @param enemy list of enemies
     */
    protected void equipEnemy(List<String> enemy){
        /*
        Adds a random weapon to CharacterEquipment.
        */
        Weapon wep = GearManager.INSTANCE.getRandomWeapon(enemy);
        while (super.getEquipment().addWeapon(wep)){
            if (wep.isTwoHanded()){
                break;
            }
            wep = GearManager.INSTANCE.getRandomOneHandedWeapon(enemy);
        }

        /*
        Adds baseValue of weapon to the StaticModifier in BaseStat.
        */
        for (Weapon weapon : super.getEquipment().getWeapons()){
            super.getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(),
                    weapon.getStat().getBaseValue());
        }
    }

    /**
     * Gets the enemy name.
     * @return enemy name.
     */
    @Override
    public String getCharacterName(){
        return this.characterName;
    }

    /**
     * Used to perform enemy turns.
     * Logs TurnInformation for enemy.
     * Runs executeActions with false to target heroes.
     */
    @Override
    public void doTurn(){
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation("[ENEMY TURN] "+getCharacterName()));
        executeActions(false);
    }
}
