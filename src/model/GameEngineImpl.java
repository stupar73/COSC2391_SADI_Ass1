package model;

import java.util.Collection;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

public class GameEngineImpl implements GameEngine
{

    @Override
    public int spin(int wheelSize, int initialDelay, int finalDelay,
                    int delayIncrement)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void addPlayer(Player player)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean removePlayer(Player player)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Player getPlayer(String id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean placeBet(Player player, int number, int bet)
    {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeGameEngineCallback(GameEngineCallback gameEngineCallback)
    {
        // TODO Auto-generated method stub

    }

}
