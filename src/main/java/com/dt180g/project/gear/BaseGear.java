package com.dt180g.project.gear;

import com.dt180g.project.characters.heroes.*;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.Constants;

import java.util.*;

/**
 * Used as the base for armour and weapon classes.
 * @author Anton Byström
 */
public abstract class BaseGear {
    private final String type;
    private final String gearName;
    private final List<Class<?>> classRestrictions = new ArrayList<>();

    /**
     * Gets name, type and restrictions from derivatives.
     * Sets the classRestriction with the help of HashMap.
     * @param name of armour or weapon.
     * @param type of armour or weapon.
     * @param restrictions class restrictions for armour or weapon.
     */
    protected BaseGear(String name, String type, String restrictions){
        this.type = type;
        this.gearName = name;

        /*
        HashMap where the key is name och class as string and value is the class.
         */
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put(Constants.HERO_CLERIC, Cleric.class);
        classMap.put(Constants.HERO_RANGER, Ranger.class);
        classMap.put(Constants.HERO_WARRIOR, Warrior.class);
        classMap.put(Constants.HERO_WIZARD, Wizard.class);

        /*
        Splits the string if there is multiple restrictions and then gets the value from the classMap.
         */
        if(restrictions.contains(",")){
            String[] arrOfStr = restrictions.split(",");
            for(String a: arrOfStr){
                Class<?> userRes = classMap.get(a);
                this.getClassRestrictions().add(userRes);
            }
        }
        else {
            this.getClassRestrictions().add(classMap.get(restrictions));
        }
    }

    /**
     * Gets the type.
     * @return type of the weapon or armour.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Gets the classRestrictions.
     * @return List of classRestrictions.
     */
    public List<Class<?>> getClassRestrictions(){
        return this.classRestrictions;
    }

    /**
     * Checks if the classRestriction contains input type.
     * @param type class.
     * @return true if the restriction contains input value.
     */
    public boolean checkClassRestriction(Class<?> type){
        return classRestrictions.contains(type);
    }

    /**
     * Used by derivatives to get the trait/attribute.
     * @return armour trait or weapon attribute.
     */
    public abstract BaseStat getStat();

    /**
     * Gets the name of the gear and are used by derivative to format gear with weapon/attribute.
     * @return gears name.
     */
    @Override
    public String toString(){
        return this.gearName;
    }
}
