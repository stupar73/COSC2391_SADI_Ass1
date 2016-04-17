package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

/**
 * Engine for the game, controls all game logic.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameEngineImpl implements GameEngine
{
    private Collection<Player> players;
    private Collection<GameEngineCallback> gameEngineCallbacks;
    private int previousWheelValue;
    private static Logger logger = Logger.getLogger("assignment1");

    public GameEngineImpl()
    {
        players = new ArrayList<Player>();
        gameEngineCallbacks = new ArrayList<GameEngineCallback>();
    }

    @Override
    public int spin(int wheelSize, int initialDelay, int finalDelay,
            int delayIncrement)
    {
        // Check pre-conditions
        if (wheelSize < 1)
        {
            throw new IllegalArgumentException(
                    "Wheel size must be 1 or greater");
        }
        if (initialDelay < 1 || finalDelay < 1 || delayIncrement < 1)
        {
            throw new IllegalArgumentException(
                    "Delay values (in ms) must be 1 or greater");
        }
        if (finalDelay < initialDelay)
        {
            throw new IllegalArgumentException(
                    "Final delay must be greater than inital delay");
        }

        /*
         * Assign starting value to previous wheel number or pseudo-randomly
         * generate a starting number if this is the first spin
         */
        int num;
        if (previousWheelValue <= 0 || previousWheelValue > wheelSize)
        {
            Random r = new Random();
            num = r.nextInt(wheelSize) + 1;
        }
        else
        {
            num = previousWheelValue;
        }

        int delay = initialDelay;

        while (delay < finalDelay)
        {
            // Inform each game engine callback of the next number on the wheel
            for (GameEngineCallback callback : gameEngineCallbacks)
            {
                callback.nextNumber(num, this);
            }

            // Delay next number to simulate spinning on a physical wheel
            try
            {
                TimeUnit.MILLISECONDS.sleep(delay);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }

            num++;

            // Once wheelSize is reached, wrap around to 1
            if (num > wheelSize)
            {
                num = 1;
            }

            delay += delayIncrement;
        }

        // Inform each game engine callback of the final result
        for (GameEngineCallback callback : gameEngineCallbacks)
        {
            callback.result(num, this);
        }

        previousWheelValue = num;

        return num;
    }

    @Override
    public void addPlayer(Player player)
    {
        assert (player != null);

        if (!players.add(player))
        {
            throw new IllegalArgumentException(
                    "Player was unable to be added to the collection");
        }
        logger.log(Level.INFO, "{" + player + "} added");
    }

    @Override
    public boolean removePlayer(Player player)
    {
        assert (player != null);

        boolean wasRemoved = players.remove(player);

        if (wasRemoved)
        {
            logger.log(Level.INFO, "{" + player + "} removed");
        }

        return wasRemoved;
    }

    @Override
    public Player getPlayer(String id)
    {
        for (Player player : players)
        {
            if (player.getPlayerId().equals(id))
            {
                return player;
            }
        }
        return null;
    }

    @Override
    public boolean placeBet(Player player, int number, int bet)
    {
        // Check pre-conditions
        assert (player != null);
        if (number < 1)
        {
            throw new IllegalArgumentException(
                    "Lucky number must be a number on the wheel");
        }
        if (bet < 1)
        {
            throw new IllegalArgumentException(
                    "Bet amount must be 1 or greater");
        }

        boolean wasPlaced = player.placeBet(number, bet);

        if (wasPlaced)
        {
            logger.log(Level.INFO, "Bet placed for " + bet + " points on "
                    + "lucky number " + number + " by player with id "
                    + player.getPlayerId());
        }

        return wasPlaced;
    }

    @Override
    public void calculateResult(int result)
    {
        for (Player player : players)
        {
            if (player.getNumber() == result)
            {
                // 'player' won, add twice their bet to their points
                int currPoints = player.getPoints();
                int betPlaced = player.getBet();
                player.setPoints(currPoints + (2 * betPlaced));
            }
            player.resetBet();
        }
    }

    @Override
    public Collection<Player> getAllPlayers()
    {
        return Collections.unmodifiableCollection(
                new ArrayList<Player>(players));
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback engineCallback)
    {
        assert (engineCallback != null);

        gameEngineCallbacks.add(engineCallback);
    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback engineCallback)
    {
        assert (engineCallback != null);

        gameEngineCallbacks.remove(engineCallback);
    }

}
