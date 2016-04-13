package view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.AddPlayerListener;
import controller.RemovePlayerListener;
import model.interfaces.GameEngine;

public class GameToolbar extends JToolBar
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private JButton addPlayer;
    private JButton removePlayer;

    public GameToolbar(GameWindow gameWindow, GameEngine gameEngine)
    {
        // Give the toolbar a title
        super("Spin the Wheel Toolbar");

        // Disable toolbar dragging
        this.setFloatable(false);

        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;

        addPlayer = new JButton("Add Player");
        removePlayer = new JButton("Remove Player");

        // Add listeners to buttons
        addPlayer.addActionListener(new AddPlayerListener(this.gameWindow,
                this.gameEngine));
        removePlayer.addActionListener(new RemovePlayerListener(this.gameWindow,
                this.gameEngine));

        this.add(addPlayer);
        this.add(removePlayer);
    }
}
