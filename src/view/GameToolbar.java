package view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import controller.AddPlayerListener;
import controller.RemovePlayerListener;
import model.interfaces.GameEngine;

/**
 * Toolbar that contains buttons for adding and removing players.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameToolbar extends JToolBar
{
    private GameWindow window;
    private GameEngine engine;
    private JButton addPlayer;
    private JButton removePlayer;

    public GameToolbar(GameWindow window, GameEngine engine)
    {
        // Disable toolbar dragging
        this.setFloatable(false);

        this.window = window;
        this.engine = engine;

        addPlayer = new JButton("Add Player");
        removePlayer = new JButton("Remove Player");

        // Add listeners to buttons
        addPlayer.addActionListener(new AddPlayerListener(this.window,
                this.engine));
        removePlayer.addActionListener(new RemovePlayerListener(this.window,
                this.engine));

        this.add(addPlayer);
        this.add(removePlayer);
    }
}
