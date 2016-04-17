package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import view.GameVariablesDialog;
import view.GameWindow;

/**
 * Listener that displays a change game variables prompt in response to a GUI
 * event.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class ChangeGameVariablesListener implements ActionListener
{
    private GameWindow window;
    private GameEngine engine;

    public ChangeGameVariablesListener(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameVariablesDialog dialog = new GameVariablesDialog(window, engine);
        dialog.display();
    }
}
