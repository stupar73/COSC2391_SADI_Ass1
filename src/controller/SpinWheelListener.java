package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GameVariables;
import model.interfaces.GameEngine;
import view.GameWindow;

public class SpinWheelListener implements ActionListener
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;

    public SpinWheelListener(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        gameEngine.spin(GameVariables.wheelSize, GameVariables.initialDelay,
                GameVariables.finalDelay, GameVariables.delayIncrement);
    }
}
