package model;

/**
 * Stores variables for the current game, modifiable through preferences menu
 */
public abstract class GameVariables
{
    public static int wheelSize = 40;
    public static int startingPoints = 1000;
    public static int initialDelay = 1;
    public static int finalDelay = 300;
    public static int delayIncrement = 30;
    public static int nextPlayerID = 0;
    public static int maxPlayers = 1;
}
