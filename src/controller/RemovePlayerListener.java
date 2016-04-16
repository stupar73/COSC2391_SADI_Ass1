package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;

public class RemovePlayerListener implements ActionListener
{
    GameWindow gameWindow;
    GameEngine gameEngine;

    public RemovePlayerListener(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String id = JOptionPane.showInputDialog(gameWindow, "Enter player ID:",
                "Remove Player", JOptionPane.QUESTION_MESSAGE);

        if (id == null)
        {
            // Cancel pressed
            return;
        }

        Player player = gameEngine.getPlayer(id);
        while (player == null)
        {
            id = JOptionPane.showInputDialog(gameWindow,
                    "No player with that ID. Enter player ID:", "Remove Player",
                    JOptionPane.QUESTION_MESSAGE);

            if (id == null)
            {
                // Cancel pressed
                return;
            }

            player = gameEngine.getPlayer(id);
        }

        // Remove player from game engine
        gameEngine.removePlayer(player);
        // Update GUI to remove player
        gameWindow.updateVisiblePlayers();
        // De-activate spin button
        gameWindow.getWheelPanel().deactivateSpinButton();
    }

}
