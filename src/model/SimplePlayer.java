package model;

import model.interfaces.Player;

/**
 * A simple implementation of Player.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class SimplePlayer implements Player
{
    private String id;
    private String name;
    private int points;
    private int bet;
    private int number;

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
        if (!canPlaceBet(bet))
        {
            return false;
        }

        this.points -= bet;
        this.number = number;
        this.bet = bet;

        return true;
    }

    /**
     * Determines whether this {@code Player} can place the bet
     *
     * @param number
     *            the lucky number on the wheel that the player is betting on
     * @param bet
     *            the bet in points
     * @return true if the player has sufficient points and the bet is greater
     *         than zero
     */
    private boolean canPlaceBet(int bet)
    {
        return bet > 0 && bet <= this.points && this.bet == 0;
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

        s = "Player: ";
        s += "id=" + this.id;
        s += ", name=" + this.name;
        s += ", points=" + this.points;
        s += ", number=" + this.number;
        s += ", bet=" + this.bet;

        return s;
    }
}
