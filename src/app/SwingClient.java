package app;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackSwingImpl;
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
        gameEngine.addGameEngineCallback(new GameEngineCallbackSwingImpl(
                gameWindow));
    }
}
