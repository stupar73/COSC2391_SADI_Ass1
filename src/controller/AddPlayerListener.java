package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.GameVariables;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;

public class AddPlayerListener implements ActionListener
{
    GameWindow gameWindow;
    GameEngine gameEngine;

    public AddPlayerListener(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (gameEngine.getAllPlayers().size() >= GameVariables.getMaxPlayers())
        {
            JOptionPane.showMessageDialog(gameWindow, "Cannot add new player, "
                    + "maximum player count has been reached.");
            return;
        }

        String name = JOptionPane.showInputDialog(gameWindow,
                "Enter player name:", "Add Player",
                JOptionPane.QUESTION_MESSAGE);

        if (name == null)
        {
            // Cancel pressed
            return;
        }

        while (name.isEmpty())
        {
            name = JOptionPane.showInputDialog(gameWindow,
                    "Invalid name. Enter player name:", "Add Player",
                    JOptionPane.QUESTION_MESSAGE);
            if (name == null)
            {
                // Cancel pressed
                return;
            }
        }

        int playerID = GameVariables.getNextPlayerID();
        Player newPlayer = new SimplePlayer(Integer.toString(playerID), name,
                GameVariables.getStartingPoints());
        GameVariables.setNextPlayerID(playerID + 1);

        // Add player to game engine
        gameEngine.addPlayer(newPlayer);
        // Update GUI to display new player
        gameWindow.updateVisiblePlayers();
        // Activate spin button
        gameWindow.getWheelPanel().activateSpinButton();
    }
}
