package model;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine
{
    private Collection<Player> players;
    private Collection<GameEngineCallback> gameEngineCallbacks;

    @Override
    public int spin(int wheelSize, int initialDelay, int finalDelay,
            int delayIncrement)
    {
        Random r = new Random();

        int randNum = r.nextInt(wheelSize) + 1;

        for (int delay = initialDelay; delay < finalDelay; delay +=
                delayIncrement)
        {

        }

        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addPlayer(Player player)
    {
        // TODO check input values

        if (!players.add(player))
        {
            // TODO Player not added to collection 'players'
        }
    }

    @Override
    public boolean removePlayer(Player player)
    {
        // TODO check input values

        return players.remove(player);
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
        // TODO check input values

        // TODO Check the return
        player.placeBet(number, bet);

        return false;
    }

    @Override
    public void calculateResult(int result)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public Collection<Player> getAllPlayers()
    {
        return Collections.unmodifiableCollection(players);
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        // TODO check input values

        gameEngineCallbacks.add(gameEngineCallback);
    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        // TODO check input values

        gameEngineCallbacks.remove(gameEngineCallback);
    }

}
