package app;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.GameVariables;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Simple console client for SADI assignment 1, Semester 1, 2016
 *
 * @author Caspar Ryan
 *
 */
public class Client
{
    private static Logger logger = Logger.getLogger("assignment1");
    private static final int WHEEL_SIZE = GameVariables.getWheelSize();
    private static final int BET = 100;

    public static void main(String args[])
    {
        final GameEngine engine = new GameEngineImpl();

        // Create two test players
        Player[] players = new Player[] {
                new SimplePlayer("1", "The Hustler", 1000),
                new SimplePlayer("2", "The Loser", 500)
        };

        engine.addGameEngineCallback(new GameEngineCallbackImpl());

        // Main loop to add player place a bet
        for (Player player : players)
        {
            engine.addPlayer(player);
            int number = (int) (Math.random() * WHEEL_SIZE);
            logger.log(Level.INFO, "lucky number=" + number);
            logger.log(Level.INFO, "placeBet returned "
                    + engine.placeBet(player, number, BET));
        }

        // All players have bet so now do spin, so that GameEngineCallBacks are
        // called
        int result = engine.spin(WHEEL_SIZE, 1, 300, 30);

        // Update player point balances
        engine.calculateResult(result);

        // Log final points balances so we can check correctness
        for (Player player : engine.getAllPlayers())
        {
            logger.log(Level.INFO, player.toString());
        }
        // Remove players
        for (Player player : players)
        {
            engine.removePlayer(player);
        }
    }
}
