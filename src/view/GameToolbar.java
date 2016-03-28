package view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.AddPlayerListener;
import controller.RemovePlayerListener;
import model.interfaces.GameEngine;

public class GameToolbar extends JToolBar
{
    private JButton addPlayer;
    private JButton removePlayer;

    public GameToolbar(GameWindow gameWindow, GameEngine gameEngine)
    {
        // Give the toolbar a title
        super("Spin the Wheel Toolbar");

        addPlayer = new JButton("Add Player");
        removePlayer = new JButton("Remove Player");

        // Add listeners to buttons
        addPlayer.addActionListener(new AddPlayerListener(gameWindow,
                gameEngine));
        removePlayer.addActionListener(new RemovePlayerListener(gameWindow,
                gameEngine));

        this.add(addPlayer);
        this.add(removePlayer);
    }
}
