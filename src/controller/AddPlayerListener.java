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

        Player newPlayer = new SimplePlayer(
                Integer.toString(gameEngine.getAllPlayers().size()),
                name, GameVariables.startingPoints);
        gameEngine.addPlayer(newPlayer);
    }
}
