package view;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import model.interfaces.GameEngine;

public class GameWindow extends JFrame
{
    private GameEngine gameEngine;
    private GameToolbar toolbar;
    private GameMenuBar menuBar;


    public GameWindow(GameEngine gameEngine)
    {
        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        super("Sping the Wheel Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.menuBar = new GameMenuBar();
        this.setJMenuBar(menuBar);

        this.gameEngine = gameEngine;

        this.toolbar = new GameToolbar(this, gameEngine);
        this.add(this.toolbar, BorderLayout.NORTH);

        // TODO Player panel

        // TODO Wheel display + spin button

        this.pack();
        this.setVisible(true);
    }
}
