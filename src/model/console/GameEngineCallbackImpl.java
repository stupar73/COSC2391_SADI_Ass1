package model.console;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;

public class GameEngineCallbackImpl implements GameEngineCallback
{
    private static Logger logger = Logger.getLogger("assignment1");

    @Override
    public void nextNumber(int nextNumber, GameEngine engine)
    {
        logger.log(Level.INFO, "next number=" + nextNumber);
    }

    @Override
    public void result(int result, GameEngine engine)
    {
        logger.log(Level.INFO, "result=" + result);
    }

}
