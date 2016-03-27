package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import controller.ChangeGameVariablesListener;

public class GameMenuBar extends JMenuBar
{
    private JMenu menu;
    private JMenuItem gameVarsItem;

    public GameMenuBar()
    {
        this.menu = new JMenu("Preferences");
        this.gameVarsItem = new JMenuItem("Change game variables");

        gameVarsItem.addActionListener(new ChangeGameVariablesListener());

        menu.add(gameVarsItem);
        this.add(menu);
    }
}
