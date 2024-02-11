package com.dt180g.project.characters;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.Constants;
import com.dt180g.project.support.Randomizer;


import java.util.*;

/**
 * Acts as base for all characters in the game
 * Used to handle execution of actions and register damage and healing.
 * @author Anton Byström
 */
public abstract class BaseCharacter {
    private final CharacterStats characterStats;
    private final CharacterEquipment equipment = new CharacterEquipment();
    private List<BaseAbility> abilities;

    /**
     * Gets the characters stats from BaseHero.
     * @param stats characterStats given from derivative.
     */
    protected BaseCharacter(CharacterStats stats){
        this.characterStats = stats;
    }

    /**
     * BaseHero sends the available abilities for the class.
     * @param abilities List of abilities for the character.
     */
    protected void addAbilities(List<BaseAbility> abilities){
        this.abilities = abilities;
    }

    /**
     * Used to combine the Hero/Enemy with it´s AP, HP and Energy values.
     * @param info If it´s a hero or enemy and the characters name.
     * @return A string of the character name with the amount of AP, HP and Energy.
     */
    protected String getTurnInformation(String info){
        return "%s | %d AP | %d HP | %d Energy".formatted(info,
                getActionPoints(), getHitPoints(), getEnergyLevel());
    }

    /**
     * Executes the ability if the character has enough AP and EN to do.
     * Uses a for loop to run through the list of all abilities and execute ability with stated power depending on
     * if the ability is magical or not and if it´s a healing ability.
     * All abilities use attackRate as one of the damage values.
     * If it´s a healing ability it also uses the characters healingPower.
     * If it´s a magical ability it will use the characters magicalPower and weaponDamage.
     * If it´s a physical ability it will use th characters physicalPower and weaponDamage.
     * If a ability has been performed the characters AP and Energy levels need to be reduced with the abilities cost.
     * @param target True if enemies are targeted or false if heroes are targeted
     */
    protected void executeActions(boolean target) {
        for (BaseAbility ability : determineActions()) {
            if (ability.getActionPointCost() < getActionPoints() && ability.getEnergyCost() < getEnergyLevel()) {
                if (ability.isMagic()){
                    if (ability.isHeal()) {
                        ability.execute(getCharacterStats().getAttackRate()+getCharacterStats().getHealingPower(),
                                !target);
                    }else {
                        ability.execute(getCharacterStats().getAttackRate() +
                                        getCharacterStats().getMagicPower() + getEquipment().getTotalWeaponDamage(),
                                target);
                    }
                } else {
                    ability.execute(getCharacterStats().getAttackRate() +
                                    getCharacterStats().getPhysicalPower() + getEquipment().getTotalWeaponDamage(),
                            target);

                }
                getCharacterStats().adjustActionPoints(-ability.getActionPointCost());
                getCharacterStats().adjustEnergyLevel(-ability.getEnergyCost());
            }
        }
    }

    /**
     * Gets a list random abilities that the character has access to.
     * @return A list of abilities to be used.
     */
    private Deque<BaseAbility> determineActions(){
        Deque<BaseAbility> ability = new ArrayDeque<>();
        while (ability.size() < Constants.ACTIONS_PER_TURN){
            ability.addFirst(getAbilities().get(Randomizer.INSTANCE.getRandomValue(getAbilities().size()-1)));
        }
        return ability;
    }

    /**
     * Registers the amount of damage the character takes, mitigated by characterStats and armor protection
     * if it´s not a magical attack.
     * @param incomingDmg Damage of attacker.
     * @param magical True if it´s a magical attack.
     * @return A list with the amount of mitigated damage and amount of damage taken.
     */
    public List<Integer> registerDamage(int incomingDmg, boolean magical){
        List<Integer> dmgTaken = new ArrayList<>();
        if (magical){
            dmgTaken.add(getCharacterStats().getDefenceRate());
            if (incomingDmg < getCharacterStats().getDefenceRate()){
                dmgTaken.add(0);
            }else {
                dmgTaken.add(incomingDmg - getCharacterStats().getDefenceRate());
                getCharacterStats().adjustHitPoints(-incomingDmg + getCharacterStats().getDefenceRate());
            }
        }
        else {
            dmgTaken.add(getCharacterStats().getDefenceRate()+getEquipment().getTotalArmorProtection());
            if (incomingDmg < getCharacterStats().getDefenceRate()){
                dmgTaken.add(0);
            }else {
                dmgTaken.add(incomingDmg-(getCharacterStats().getDefenceRate() +
                        getEquipment().getTotalArmorProtection()));
                getCharacterStats().adjustHitPoints(-incomingDmg+(getCharacterStats().getDefenceRate() +
                        getEquipment().getTotalArmorProtection()));
            }
        }
        return dmgTaken;
    }

    /**
     * Registers the new health of the character.
     * @param value Amount of healing.
     * @return The new hp.
     */
    public int registerHealing(int value){
        this.characterStats.adjustHitPoints(value);
        return this.characterStats.getCurrentHitPoints();
    }

    /**
     * Restores some AP and Energy if there´s a new round.
     */
    public void roundReset(){
        this.characterStats.adjustActionPoints(Constants.ROUND_RESET_AP);
        this.characterStats.adjustEnergyLevel(Constants.ROUND_RESET_ENERGY);
    }

    /**
     * Used by derivatives to perform the turn.
     */
    public abstract void doTurn();

    /**
     * Combines the characterStats and characterEquipment information to a single string with the Hero´s name.
     * @return Combined characterStats and characterEquipment.
     */
    @Override
    public String toString() {
        String filler = "*".repeat(40);
        return "%s\n\t\t\t%s\n%s\n%s%s".formatted(filler, this.getCharacterName().toUpperCase(), filler,
                this.getCharacterStats().toString(), this.getEquipment().toString());
    }

    /**
     * Gets the characters name.
     * @return Name of the character as string.
     */
    public abstract String getCharacterName();

    /**
     * Gets the characters stats as CharacterStats.
     * @return the characters stats.
     */
    public CharacterStats getCharacterStats(){
        return this.characterStats;
    }

    /**
     * Gets the characters equipment as CharacterEquipment.
     * @return The characters equipment.
     */
    public CharacterEquipment getEquipment(){
        return this.equipment;
    }

    /**
     * Gets the characters current amount of AP.
     * @return Current amount of AP as int.
     */
    public int getActionPoints(){
        return this.characterStats.getCurrentActionPoints();
    }

    /**
     * Gets the characters current amount of HP.
     * @return Current amount of HP as int.
     */
    public int getHitPoints(){
        return this.characterStats.getCurrentHitPoints();
    }

    /**
     * Gets the characters current amount of Energy.
     * @return Current amount of Energy as int.
     */
    public int getEnergyLevel(){
        return this.characterStats.getCurrentEnergyLevel();
    }

    /**
     * Gets the characters abilities.
     * @return A list of Base abilities for the character.
     */
    public List<BaseAbility> getAbilities(){
        return this.abilities;
    }

    /**
     * Determines if the character/enemy is dead or not.
     * @return true if the hp is less than 0 and false if its above 0.
     */
    public boolean isDead(){
        return getHitPoints() <= 0;
    }
}