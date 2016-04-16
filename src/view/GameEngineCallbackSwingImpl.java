package view;

import java.util.concurrent.TimeUnit;
import javax.swing.SwingUtilities;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

public class GameEngineCallbackSwingImpl implements GameEngineCallback
{
    GameWindow gameWindow;
    GameWheelPanel wheelPanel;

    public GameEngineCallbackSwingImpl(GameWindow gameWindow)
    {
        this.gameWindow = gameWindow;
        this.wheelPanel = gameWindow.getWheelPanel();
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

        for (GamePlayerPanel panel : gameWindow.getPlayerPanels())
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
