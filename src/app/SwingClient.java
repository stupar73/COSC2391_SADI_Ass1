package app;

import model.console.GameEngineCallbackImpl;
import model.console.GameEngineImpl;
import model.interfaces.GameEngine;
import model.swing.GameEngineCallbackSwingImpl;
import view.GameWindow;

public class SwingClient
{
    public static void main(String[] args)
    {
        // Create game engine and window to display GUI
        final GameEngine gameEngine = new GameEngineImpl();
        GameWindow gameWindow = new GameWindow(gameEngine);

        // Add Swing and console game engine callback implementations
        gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
        gameEngine.addGameEngineCallback(new GameEngineCallbackSwingImpl());
    }
}
