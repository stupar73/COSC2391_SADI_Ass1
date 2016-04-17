package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;

/**
 * Listener that removes a player from the game engine in response to a GUI
 * event.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class RemovePlayerListener implements ActionListener
{
    private GameWindow window;
    private GameEngine engine;

    public RemovePlayerListener(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String id = JOptionPane.showInputDialog(window, "Enter player ID:",
                "Remove Player", JOptionPane.QUESTION_MESSAGE);

        if (id == null)
        {
            // Cancel pressed
            return;
        }

        Player player = engine.getPlayer(id);
        while (player == null)
        {
            id = JOptionPane.showInputDialog(window,
                    "No player with that ID. Enter player ID:", "Remove Player",
                    JOptionPane.QUESTION_MESSAGE);

            if (id == null)
            {
                // Cancel pressed
                return;
            }

            player = engine.getPlayer(id);
        }

        // Remove player from game engine
        engine.removePlayer(player);
        // Update GUI to remove player
        window.updateVisiblePlayers();
        // Deactivate spin button
        window.getWheelPanel().deactivateSpinButton();
    }

}
