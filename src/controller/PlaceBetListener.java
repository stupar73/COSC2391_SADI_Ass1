package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GamePlayerPanel;
import view.GameWindow;

public class PlaceBetListener implements ActionListener
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private GamePlayerPanel playerPanel;
    private Player player;
    private JSpinner betField;
    private JSpinner luckyNumberField;

    public PlaceBetListener(GameWindow gameWindow, GameEngine gameEngine,
            GamePlayerPanel playerPanel)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
        this.playerPanel = playerPanel;
        this.player = playerPanel.getPlayer();
        this.betField = playerPanel.getBetField();
        this.luckyNumberField = playerPanel.getLuckyNumberfield();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        int betAmount = (int) betField.getValue();
        int luckyNumber = (int) luckyNumberField.getValue();

        if (!gameEngine.placeBet(player, luckyNumber, betAmount))
        {
            JOptionPane.showMessageDialog(gameWindow, "Unable to place bet "
                    + "for player with ID " + player.getPlayerId());
            return;
        }

        // Disable button until turn over
        playerPanel.getBetButton().setEnabled(false);
    }
}
