package app;

import model.GameEngineCallbackImpl;
import model.GameEngineImpl;
import model.interfaces.GameEngine;
import view.GameEngineCallbackSwingImpl;
import view.GameWindow;

/**
 * Swing GUI client for SADI assignment 1, Semester 1, 2016
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class SwingClient
{
    public static void main(String[] args)
    {
        // Create game engine and window to display GUI
        final GameEngine engine = new GameEngineImpl();
        GameWindow window = new GameWindow(engine);

        // Add Swing and console game engine callback implementations
        engine.addGameEngineCallback(new GameEngineCallbackImpl());
        engine.addGameEngineCallback(new GameEngineCallbackSwingImpl(
                window));
    }
}
