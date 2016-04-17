package view;

import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

/**
 * GameEngineCallback for a GUI. Informs GUI of game events in order for the
 * display to be updated.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameEngineCallbackSwingImpl implements GameEngineCallback
{
    private GameWindow window;
    private GameWheelPanel wheelPanel;

    public GameEngineCallbackSwingImpl(GameWindow window)
    {
        this.window = window;
        this.wheelPanel = window.getWheelPanel();
    }

    @Override
    public void nextNumber(int nextNumber, GameEngine engine)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                wheelPanel.updateCurrentWheelValue(nextNumber);
            }
        });
    }

    @Override
    public void result(int result, GameEngine engine)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                wheelPanel.finalCurrentWheelValue(result);
            }
        });

        engine.calculateResult(result);

        for (GamePlayerPanel panel : window.getPlayerPanels())
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                @Override
                public void run()
                {
                    panel.update();
                }
            });
        }

        // Keep displaying final wheel value for 3 seconds after spin finished
        try
        {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        wheelPanel.showSpinButton();
    }
}
