package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.interfaces.GameEngine;
import view.GameVariablesDialog;
import view.GameWindow;

public class ChangeGameVariablesListener implements ActionListener
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;

    public ChangeGameVariablesListener(GameWindow gameWindow,
            GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        GameVariablesDialog dialog = new GameVariablesDialog(
                this.gameWindow, this.gameEngine);
        dialog.show();
    }
}
