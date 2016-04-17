package model;

/**
 * Stores variables for the current game, modifiable through preferences menu.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameVariables
{
    // Ensures class cannot be instantiated
    private GameVariables()
    {
    }

    private static int wheelSize = 40;
    private static int startingPoints = 1000;
    private static int initialDelayUpper = 10;
    private static int finalDelayUpper = 500;
    private static int delayIncrement = 20;
    private static int nextPlayerID = 0;
    private static int maxPlayers = 1;

    public static int getWheelSize()
    {
        return wheelSize;
    }

    public static void setWheelSize(int wheelSize)
    {
        GameVariables.wheelSize = wheelSize;
    }

    public static int getStartingPoints()
    {
        return startingPoints;
    }

    public static void setStartingPoints(int startingPoints)
    {
        GameVariables.startingPoints = startingPoints;
    }

    public static int getInitialDelayUpper()
    {
        return initialDelayUpper;
    }

    public static void setInitialDelayUpper(int initialDelay)
    {
        GameVariables.initialDelayUpper = initialDelay;
    }

    public static int getFinalDelayUpper()
    {
        return finalDelayUpper;
    }

    public static void setFinalDelayUpper(int finalDelay)
    {
        GameVariables.finalDelayUpper = finalDelay;
    }

    public static int getDelayIncrement()
    {
        return delayIncrement;
    }

    public static void setDelayIncrement(int delayIncrement)
    {
        GameVariables.delayIncrement = delayIncrement;
    }

    public static int getNextPlayerID()
    {
        return nextPlayerID;
    }

    public static void setNextPlayerID(int nextPlayerID)
    {
        GameVariables.nextPlayerID = nextPlayerID;
    }

    public static int getMaxPlayers()
    {
        return maxPlayers;
    }

    public static void setMaxPlayers(int maxPlayers)
    {
        GameVariables.maxPlayers = maxPlayers;
    }
}
