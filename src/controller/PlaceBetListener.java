package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;

public class PlaceBetListener implements ActionListener
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private Player player;
    
    public PlaceBetListener(GameWindow gameWindow, GameEngine gameEngine, Player player)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
        this.player = player;
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0)
    {
        // TODO
    }
}
