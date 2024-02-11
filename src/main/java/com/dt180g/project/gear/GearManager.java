package com.dt180g.project.gear;

import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Used to get weapon and armour gear from xml file and for getting different types of weapon pieces.
 * @author Anton Byström
 */
public class GearManager {
    public static final GearManager INSTANCE = new GearManager();
    private final Map<String, List<Weapon>> weapons = new HashMap<>();
    private final Map<String, List<Armor>> armorPieces = new HashMap<>();

    /**
     * Fetches all different armour and weapon pieces from the two xml files with the help of IOHelper.
     */
    private GearManager(){
        /*
        Starts by mapping the key for armourPieces to all the different types of amour there are.
        It then creates a list of all the different armour pieces that type has and
        then adds the list as values to the key.
        */

           List<Map<String, String>> ArmorXML = IOHelper.readFromFile("armor.xml");
           ArmorXML.forEach(type -> armorPieces.put(type.get("type"), null));
           armorPieces.keySet().forEach(armtype -> {
               List<Armor> armorList = new ArrayList<>();
               for(Map<String, String> arm : ArmorXML){
                   if(arm.get("type").equals(armtype)){
                       Armor armor = new Armor(arm);
                       armorList.add(armor);
                   }
               }
               armorPieces.put(armtype, armorList);
           });

           /*
            Starts by mapping the keys for weapons to all the different types of weapons there are.
            It then creates a list of all the different weapon pieces that type has and
            then adds the list as values to the key.
             */
           List<Map<String, String>> weaponXML = IOHelper.readFromFile("weapons.xml");
           weaponXML.forEach(type -> weapons.put(type.get("type"), null));
           weapons.keySet().forEach(weptype -> {
               List<Weapon> weaponList = new ArrayList<>();
               for(Map<String, String> wep : weaponXML){
                   if(wep.get("type").equals(weptype)){
                       Weapon weapon = new Weapon(wep);
                       weaponList.add(weapon);
                   }
               }
               weapons.put(weptype, weaponList);
           });
        }

    /**
     * Returns the HashMap of all armour pieces stored in the instance.
     * @return HashMap of all armour pieces
     */
    public Map<String, List<Armor>> getAllMappedArmorPieces(){
            return this.armorPieces;
        }

    /**
     * Returns the HashMap of all weapons stored in the instance.
     * @return HashMap of all weapons.
     */
    public Map<String, List<Weapon>> getAllMappedWeapons(){
            return this.weapons;
        }

    /**
     * Gets a list of all weapons for selected type.
     * @param type type of weapons.
     * @return list of weapons.
     */
    public List<Weapon> getWeaponsOfType(String type){
            return this.weapons.get(type);
        }

        /**
         * Creates a list to store all weapons of a certain class and then returns one of those weapons.
         * @param weaponClass weapon class to get.
         * @return random weapon of weaponClass.
         */
        public Weapon getRandomWeapon(Class<?> weaponClass){
            List<Weapon> weaponList = new ArrayList<>();
            for (List<Weapon> wep : weapons.values()){
                for (Weapon wepObj : wep){
                    if(wepObj.checkClassRestriction(weaponClass)){
                        weaponList.add(wepObj);
                    }
                }
            }
            return weaponList.get(Randomizer.INSTANCE.getRandomValue(weaponList.size()-1));
        }

        /**
         * Creates a list to store all weapons of a certain type and then returns one of those weapons
         * @param weaponType type of weapons to get
         * @return random weapon of weaponType
         */
        public Weapon getRandomWeapon(List<String> weaponType){
            List<Weapon> weaponList = new ArrayList<>();
            for(String i:weaponType){
                weaponList.addAll(getWeaponsOfType(i));
            }
            return weaponList.get(Randomizer.INSTANCE.getRandomValue(weaponList.size()-1));
        }

        /**
         * Creates a list of all one handed weapons of a certain class and then returns one of those weapons.
         * @param weaponClass weapon class to get.
         * @return random one handed weapon of weaponClass.
         */
        public Weapon getRandomOneHandedWeapon(Class<?> weaponClass){
            List<Weapon> weaponList = new ArrayList<>();
            for (List<Weapon> wep : weapons.values()){
                for (Weapon wepObj : wep){
                    if(wepObj.checkClassRestriction(weaponClass) && !wepObj.isTwoHanded()){
                        weaponList.add(wepObj);
                    }
                }
            }
            return weaponList.get(Randomizer.INSTANCE.getRandomValue(weaponList.size()-1));
        }

        /**
         * Creates a list of all one handed weapons of a certain type and then returns one of those weapons.
         * @param weaponType type of one handed weapon
         * @return random one handed weapon of weaponType.
         */
        public Weapon getRandomOneHandedWeapon(List<String> weaponType){
            List<Weapon> weaponList = new ArrayList<>();
            for(String i:weaponType){
                for(Weapon wep: getWeaponsOfType(i)){
                    if(!wep.isTwoHanded()){
                        weaponList.add(wep);
                    }
                }
            }
            return weaponList.get(Randomizer.INSTANCE.getRandomValue(weaponList.size()-1));
        }

        /**
         * Creates a list of all armour pieces a certain class can use and then returns a list of armour for that class.
         * @param armorClass character class.
         * @return List of armour pieces.
         */
        public List<Armor> getAllArmorForRestriction(Class<?> armorClass){
            List<Armor> armorList = new ArrayList<>();
            for (List<Armor> armor : armorPieces.values()){
                for (Armor armorObj : armor){
                    if(armorObj.checkClassRestriction(armorClass)){
                        armorList.add(armorObj);
                    }
                }
            }
            return armorList;
        }

        /**
         * Creates a list of certain armour type and class and then returns a random armour piece.
         * @param armorType type of armour.
         * @param armorClass armour class.
         * @return armour piece.
         */
        public Armor getRandomArmorOfType(String armorType, Class<?> armorClass){
            List<Armor> armorList = new ArrayList<>();
            for (Armor armorObj : getAllArmorForRestriction(armorClass)){
                if(armorType.contains(armorObj.getType())){
                    armorList.add(armorObj);
                }
            }
            return armorList.get(Randomizer.INSTANCE.getRandomValue(armorList.size()-1));
        }
}
