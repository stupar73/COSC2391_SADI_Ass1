package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JOptionPane;
import model.GameVariables;
import model.interfaces.GameEngine;
import view.GameWindow;

/**
 * Listener that spins the game wheel in response to a GUI event.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class SpinWheelListener implements ActionListener
{
    private GameWindow window;
    private GameEngine engine;

    public SpinWheelListener(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;
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

                window.getWheelPanel().showWheel();
                try
                {
                    engine.spin(GameVariables.getWheelSize(), initDelay,
                            finalDelay, GameVariables.getDelayIncrement());
                }
                catch (IllegalArgumentException e)
                {
                    JOptionPane.showMessageDialog(window, e.getMessage(),
                            "Wheel spin error", JOptionPane.ERROR_MESSAGE);
                    window.getWheelPanel().showSpinButton();
                }
            }
        }.start();
    }
}
