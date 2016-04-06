package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controller.ChangeGameVariablesListener;
import model.interfaces.GameEngine;

public class GameMenuBar extends JMenuBar
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private JMenu menu;
    private JMenuItem gameVarsItem;

    public GameMenuBar(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;

        this.menu = new JMenu("Preferences");
        this.gameVarsItem = new JMenuItem("Change game variables");

        gameVarsItem.addActionListener(new ChangeGameVariablesListener(
                this.gameWindow, this.gameEngine));

        this.menu.add(gameVarsItem);
        this.add(menu);
    }
}
