package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controller.ChangeGameVariablesListener;
import model.interfaces.GameEngine;

/**
 * Menu bar for the application. Includes menu item for the dialog to change
 * game variables.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameMenuBar extends JMenuBar
{
    private GameWindow window;
    private GameEngine engine;
    private JMenu menu;
    private JMenuItem gameVarsItem;

    public GameMenuBar(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;

        this.menu = new JMenu("Preferences");
        this.gameVarsItem = new JMenuItem("Change game variables");

        gameVarsItem.addActionListener(new ChangeGameVariablesListener(
                this.window, this.engine));

        this.menu.add(gameVarsItem);
        this.add(menu);
    }
}
