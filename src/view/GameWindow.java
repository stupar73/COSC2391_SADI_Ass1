package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.interfaces.GameEngine;

public class GameWindow extends JFrame
{
    private GameEngine gameEngine;
    private GameMenuBar menuBar;
    private GameToolbar toolbar;

    public GameWindow(GameEngine gameEngine)
    {
        super("Spin the Wheel Game");

        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        this.gameEngine = gameEngine;

        this.menuBar = new GameMenuBar();
        this.setJMenuBar(menuBar);

        this.toolbar = new GameToolbar(this, gameEngine);
        this.add(this.toolbar, BorderLayout.NORTH);

        // TODO Player panel

        // TODO Wheel display + spin button

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
    }
}
