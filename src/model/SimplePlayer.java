package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
{

    public SimplePlayer(String string, String string2, int i)
    {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String getPlayerName()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setPlayerName(String playerName)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getPoints()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setPoints(int points)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public String getPlayerId()
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean placeBet(int number, int bet)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getBet()
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void resetBet()
    {
        // TODO Auto-generated method stub

    }

    @Override
    public int getNumber()
    {
        // TODO Auto-generated method stub
        return 0;
    }

}
