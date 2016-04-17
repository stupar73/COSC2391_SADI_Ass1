package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.GameVariables;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameWindow;

/**
 * Listener that adds a player to the game engine in response to a GUI event.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class AddPlayerListener implements ActionListener
{
    private GameWindow window;
    private GameEngine engine;

    public AddPlayerListener(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (engine.getAllPlayers().size() >= GameVariables.getMaxPlayers())
        {
            JOptionPane.showMessageDialog(window, "Cannot add new player, "
                    + "maximum player count has been reached.");
            return;
        }

        String name = JOptionPane.showInputDialog(window,
                "Enter player name:", "Add Player",
                JOptionPane.QUESTION_MESSAGE);

        if (name == null)
        {
            // Cancel pressed
            return;
        }

        while (name.isEmpty())
        {
            name = JOptionPane.showInputDialog(window,
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
        engine.addPlayer(newPlayer);
        // Update GUI to display new player
        window.updateVisiblePlayers();
        // Activate spin button
        window.getWheelPanel().activateSpinButton();
    }
}
