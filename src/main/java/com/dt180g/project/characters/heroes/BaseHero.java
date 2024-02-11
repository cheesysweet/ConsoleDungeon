package com.dt180g.project.characters.heroes;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;

import java.util.List;

/**
 * Used for equipping heroes with abilities and equipment.
 * Getting the heroes information.
 * Performs turns.
 * @author Anton Byström
 */
public abstract class BaseHero extends BaseCharacter {
    private final String characterName;

    /**
     * Used by derivative to get Hero name and create new CharacterStats with the Heroes base attributes.
     * @param name of hero.
     * @param attributes List of base attributes of hero.
     */
    protected BaseHero(String name, List<Integer> attributes){
        super(new CharacterStats(attributes));
        this.characterName = name;
    }

    /**
     * Uses the heroes class to get the heroes special abilities.
     * Gets random weapon and armour and adds it to CharacterEquipment.
     * @param heroClass the heroes class.
     */
    protected void equipHero(Class<?> heroClass){
        /*
        Adds a random weapon to CharacterEquipment
         */
        Weapon wep = GearManager.INSTANCE.getRandomWeapon(heroClass);
        while (super.getEquipment().addWeapon(wep)){
            if (wep.isTwoHanded()){
                break;
            }
            wep = GearManager.INSTANCE.getRandomOneHandedWeapon(heroClass);
        }

        /*
        Adds baseValue of weapon to the StaticModifier in BaseStat.
         */
        for (Weapon weapon : super.getEquipment().getWeapons()){
            super.getCharacterStats().adjustStatStaticModifier(weapon.getStat().getStatName(),
                    weapon.getStat().getBaseValue());
        }

        /*
        Adds a random armour piece of each type to CharacterEquipment
         */
        for (String armor : GearManager.INSTANCE.getAllMappedArmorPieces().keySet()){
            getEquipment().addArmorPiece(armor, GearManager.INSTANCE.getRandomArmorOfType(armor, heroClass));
        }

        /*
        Adds baseValue of armour piece to the StaticModifier in BaseStat.
         */
        for (Armor armor : super.getEquipment().getArmorPieces()){
            super.getCharacterStats().adjustStatStaticModifier(armor.getStat().getStatName(),
                    armor.getStat().getBaseValue());
        }
    }

    /**
     * Resets the heroes Action points, Energy Level and Health if the heroes hp is lower than the base value before
     * starting a dungeon level.
     */
    public void resetHeroStats(){
        this.getCharacterStats().resetActionPoints();
        this.getCharacterStats().resetEnergyLevel();
        this.getCharacterStats().resetHitPoints();
    }

    /**
     * Gets the heroes name.
     * @return heroes name.
     */
    @Override
    public String getCharacterName(){
        return this.characterName;
    }

    /**
     * Used to perform hero turns.
     * Logs TurnInformation for hero class.
     * Runs executeActions with true to target enemies.
     */
    @Override
    public void doTurn(){
        ActivityLogger.INSTANCE.logTurnInfo(getTurnInformation("[HERO TURN] "+getCharacterName()));
        executeActions(true);
    }
}
