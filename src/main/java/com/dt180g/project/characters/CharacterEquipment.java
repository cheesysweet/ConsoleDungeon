package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.Constants;
import com.dt180g.project.support.IOHelper;

import java.util.*;

/**
 * Handles the storage and creation of armour pieces and weapons.
 * @author Anton Byström.
 */

public class CharacterEquipment {
    private final List<Weapon> weapons = new ArrayList<>();
    private final Map<String, Armor> armorPieces = new HashMap<>();

    /**
     * Makes the list of weapons accessible for other classes.
     * @return List of characters weapons.
     */
    public List<Weapon> getWeapons(){
        return this.weapons;
    }

    /**
     * Makes the list of armourPieces accessible for other classes.
     * @return List of characters armour pieces.
     */
    public List<Armor> getArmorPieces(){
        return new ArrayList<>(armorPieces.values());
    }

    /**
     * Calculates the amount of total weapon damage by adding the weapon damage from each weapon.
     * @return the amount weapon damage.
     */
    public int getTotalWeaponDamage(){
        int damage = 0;
        for (Weapon weapon : weapons) {
            if (weapon.isTwoHanded()) {
                damage = weapon.getDamage();
            } else {
                damage += weapon.getDamage();
            }
        }
        return damage;
    }

    /**
     * Calculates the amount of total protection by adding the protecting of each armour piece.
     * @return value of protection.
     */
    public int getTotalArmorProtection(){
        return armorPieces.values().stream().mapToInt(Armor::getProtection).sum();
    }

    /**
     * Gets the amount of empty weapon slots by subtracting the amount of weapons from the stated 2.
     * @return amount of empty weapon slots.
     */
    public int amountOfEmptyWeaponSlots(){
        return 2 - weapons.size();
    }

    /**
     * Gets the amount of empty amour slots by subtracting the amount of armour pieces from the stated 5.
     * @return amount of empty armour slots.
     */
    public int amountOfEmptyArmorSlots(){
        return 5 - armorPieces.values().size();
    }

    /**
     * Adds weapons to the character, it checks that there are empty slots the if it´s a two handed weapon
     * it adds the weapon twice. Otherwise it adds one weapon and runs again and adds a second one.
     * @param wep A random weapon.
     * @return True if the weapon was added else it returns false.
     */
    public boolean addWeapon(Weapon wep){
        if(amountOfEmptyWeaponSlots() > 1) {
            if (wep.isTwoHanded()) {
                weapons.add(wep);
                weapons.add(wep);
            }
            else {
                weapons.add(wep);
            }
            return true;
        }
        if(amountOfEmptyWeaponSlots() == 1){
            weapons.add(wep);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Adds a armour piece to the character, it checks if theres empty armors slots available and the adds the
     * armour piece as long as the type of armour don´t already exists.
     * @param type The type of armour.
     * @param armor A random armour piece.
     * @return True if the armour piece was added else it returns false.
     */
    public boolean addArmorPiece(String type, Armor armor){
        if(amountOfEmptyArmorSlots() > 0) {
            if (armorPieces.containsKey(type)) {
                return false;
            }
            else {
                armorPieces.put(type, armor);
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Used when displaying the Hero´s details.
     * Creates a table that displays the Hero´s equipment.
     * @return A string with the help of IOHelper that formats the Lists. Used in BaseCharacter to print full details
     */
    @Override
    public String toString(){
        List<List<String>> gear = new ArrayList<>();

        /*
        Formats a string out of each weapon that exists in the list of weapons.
        Displays the weapon type, if it´s two handed or not, the weapons damage , name and attribute of the weapon.
         */
        Weapon twoHandedWeapon = null;
        for(Weapon weapon : weapons) {
            if (weapon != twoHandedWeapon) {
                gear.add(List.of("%-10s %s | %s%-17s%s | %sDamage %12s+%s%s | %s%-33s %s+%s%s".formatted(
                        "[" + weapon.getType() + "]", Constants.ANSI_RESET,
                        Constants.ANSI_PURPLE, weapon.getWield(), Constants.ANSI_RESET,
                        Constants.ANSI_RED, Constants.ANSI_GREEN, weapon.getDamage(), Constants.ANSI_RESET,
                        Constants.ANSI_CYAN, weapon.toString(), Constants.ANSI_YELLOW,
                        weapon.getStat().getModifiedValue(), Constants.ANSI_RESET
                )));
                if (weapon.isTwoHanded()) {
                    twoHandedWeapon = weapon;
                }
            }
        }


        /*
        Formats a string out of each armour piece that exists in the Map of armorPieces.
        Displays the armour type, material, protection value, name and trait of the piece.
         */
        for(Armor piece : armorPieces.values()) {
                gear.add(List.of("%-10s %s | %s%-17s%s | %sProtection %8s+%s%s | %s%-33s %s+%s%s".formatted(
                        "["+piece.getType()+"]", Constants.ANSI_RESET,
                        Constants.ANSI_PURPLE, piece.getMaterial(), Constants.ANSI_RESET,
                        Constants.ANSI_RED, Constants.ANSI_GREEN, piece.getProtection(), Constants.ANSI_RESET,
                        Constants.ANSI_CYAN, piece.toString(), Constants.ANSI_YELLOW,
                        piece.getStat().getModifiedValue(), Constants.ANSI_RESET
                )));
        }

        return Constants.ANSI_BLUE + "EQUIPMENT\n" + Constants.ANSI_RESET + IOHelper.formatAsTable(gear);
    }
}
