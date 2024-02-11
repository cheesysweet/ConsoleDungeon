package com.dt180g.project.support;

import java.util.logging.*;

/**
 * Responsible for displaying all the information to the user.
 * @author Anton Byström
 */
public class ActivityLogger {
    public static final ActivityLogger INSTANCE = new ActivityLogger();
    private final Logger logger;

    /**
     * Creates the logger and makes it only logg to the console.
     */
    private ActivityLogger(){
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setUseParentHandlers(false);

        SimpleFormatter formatter = new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord logRecord) {
                return String.format("%s%n", logRecord.getMessage());
            }
        };

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(formatter);
        logger.addHandler(consoleHandler);
    }

    /**
     * Try to delay execution and catches interrupt errors.
     */
    private void delayExecution(){
        try {
            Thread.sleep(Constants.SLEEP_DELAY);
        }
        catch (InterruptedException e){
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * Performs the actual logging and delays execution if constant is true.
     * @param info string to be displayed to the user.
     */
    private void performLog(String info){
        if (Constants.USE_SLEEP_DELAY){
            delayExecution();
        }
        logger.info(info);
    }

    /**
     * Used to log each round and formats the output with color.
     * @param round information about dungeon level, round, remaining heroes/enemies
     */
    public void logRoundInfo(String round){
        performLog(Constants.ANSI_PURPLE + round + Constants.ANSI_RESET);
    }

    /**
     * Used to log attacker and formats the output with colors.
     * @param attacker attacker information.
     */
    public void logTurnInfo(String attacker){
        performLog(Constants.ANSI_BLUE + attacker + Constants.ANSI_RESET);
    }

    /**
     * Used to log attack ability and formats the output with colors.
     * @param att ability string with amount of targets.
     */
    public void logAttack(String att){
        performLog("\t" + Constants.ANSI_GREEN + att + Constants.ANSI_RESET);
    }

    /**
     * Used when characters take damage and formats the output with color.
     * @param dmg string of ability name, damage taken and remaining hp
     */
    public void logDamage(String dmg){
        performLog("\t\t" + Constants.ANSI_YELLOW + dmg + Constants.ANSI_RESET);
    }

    /**
     * Used when a character takes lethal damage and dies and formats the output with color.
     * @param dead
     */
    public void logDeath(String dead){
        performLog("\t\t" + Constants.ANSI_RED + dead + Constants.ANSI_RESET);
    }

    /**
     * Used to log healing and formats the output with color.
     * @param heal information about the healing ability.
     */
    public void logHealing(String heal){
        performLog("\t\t" + Constants.ANSI_MAGENTA + heal + Constants.ANSI_RESET);
    }
}
