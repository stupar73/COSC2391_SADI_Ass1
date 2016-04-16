package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
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
        new Thread()
        {
            @Override
            public void run()
            {
                // Pseudo-randomly generate initial and final delay values where
                // the final delay value is at least 3*delayIncrement greater
                // than the initial delay value. This ensures that the wheel
                // spins through at least three values
                Random r = new Random();
                int initDelay = r.nextInt(GameVariables.getInitialDelayUpper())
                        + 1;
                int delayDiff = initDelay
                        + 3 * GameVariables.getDelayIncrement();
                int finalDelay = r.nextInt(GameVariables.getFinalDelayUpper()
                        - delayDiff) + delayDiff;

                gameWindow.getWheelPanel().showWheel();
                try
                {
                    gameEngine.spin(GameVariables.getWheelSize(), initDelay,
                            finalDelay, GameVariables.getDelayIncrement());
                }
                catch (IllegalArgumentException e)
                {
                    JOptionPane.showMessageDialog(gameWindow, e.getMessage(),
                            "Wheel spin error", JOptionPane.ERROR_MESSAGE);
                    gameWindow.getWheelPanel().showSpinButton();
                }
            }
        }.start();
    }
}
