package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GamePlayerPanel;
import view.GameWindow;

/**
 * Listener that places a bet in the game engine for a player in response to a
 * GUI event.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class PlaceBetListener implements ActionListener
{
    private GameWindow window;
    private GameEngine engine;
    private GamePlayerPanel playerPanel;
    private Player player;
    private JSpinner betField;
    private JSpinner luckyNumberField;

    public PlaceBetListener(GameWindow window, GameEngine engine,
            GamePlayerPanel playerPanel)
    {
        this.window = window;
        this.engine = engine;
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

        // Attempt to place bet, show error message if it failed
        if (!engine.placeBet(player, luckyNumber, betAmount))
        {
            JOptionPane.showMessageDialog(window, "Unable to place bet "
                    + "for player with ID " + player.getPlayerId());
            return;
        }

        // Add text descriptive of the bet placed to the betPlacedInfo field
        playerPanel.getBetPlacedInfo().setText("<html><i>Bet placed for "
                + betAmount + " point(s) on number " + luckyNumber
                + ".</i></html>");

        // Disable bet button until turn over
        playerPanel.getBetButton().setEnabled(false);
    }
}
