package model;

import model.interfaces.Player;

public class SimplePlayer implements Player
{
    private String id;
    private String name;
    private int points;
    private int bet;
    public int number;

    public SimplePlayer(String id, String name, int points)
    {
        this.id = id;
        this.name = name;
        this.points = points;
        this.bet = 0;
        this.number = 0;
    }

    @Override
    public String getPlayerName()
    {
        return this.name;
    }

    @Override
    public void setPlayerName(String playerName)
    {
        this.name = playerName;
    }

    @Override
    public int getPoints()
    {
        return this.points;
    }

    @Override
    public void setPoints(int points)
    {
        this.points = points;
    }

    @Override
    public String getPlayerId()
    {
        return this.id;
    }

    @Override
    public boolean placeBet(int number, int bet)
    {
        if (!canPlaceBet(number, bet))
        {
            return false;
        }

        this.points -= bet;
        this.number = number;
        this.bet = bet;

        return true;
    }

    /**
     * @param number
     *            the "lucky" number on the wheel the player is betting on
     * @param bet
     *            the bet in points
     * @return true if the player has sufficient points and the bet is greater
     *         than zero
     */
    private boolean canPlaceBet(int number, int bet)
    {
        return bet > 0 && this.points <= bet;
    }

    @Override
    public int getBet()
    {
        return this.bet;
    }

    @Override
    public void resetBet()
    {
        this.bet = 0;
    }

    @Override
    public int getNumber()
    {
        return this.number;
    }

    @Override
    public String toString()
    {
        String s;

        s = "ID: " + this.id;
        s += "Name: " + this.name;
        s += "Current points: " + this.points;
        s += "Current bet: " + this.bet;
        s += "Lucky number: " + this.number;

        return s;
    }
}
