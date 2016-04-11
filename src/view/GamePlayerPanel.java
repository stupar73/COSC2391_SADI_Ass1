package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import controller.PlaceBetListener;
import model.GameVariables;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class GamePlayerPanel extends JPanel
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private Player player;
    private JLabel nameLabel;
    private JLabel nameValue;
    private JLabel pointsLabel;
    private JLabel pointsValue;
    private JLabel luckyNumberLabel;
    private JSpinner luckyNumberField;
    private JLabel betLabel;
    private JSpinner betField;
    private JButton placeBet;

    public GamePlayerPanel(GameWindow gameWindow, GameEngine gameEngine,
            Player player)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;
        this.player = player;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("<html><b>Player ID: "
                + player.getPlayerId() + "</b></html>"));

        nameLabel = new JLabel("Name: ", JLabel.TRAILING);

        nameValue = new JLabel(player.getPlayerName());
        nameValue.setAlignmentX(LEFT_ALIGNMENT);

        pointsLabel = new JLabel("Points: ", JLabel.TRAILING);

        pointsValue = new JLabel(Integer.toString(player.getPoints()));
        pointsValue.setAlignmentX(LEFT_ALIGNMENT);

        luckyNumberLabel = new JLabel("Lucky number: ", JLabel.TRAILING);

        luckyNumberField = new JSpinner(new SpinnerNumberModel(1, 1,
                GameVariables.wheelSize, 1));
        luckyNumberField.setAlignmentX(LEFT_ALIGNMENT);

        betLabel = new JLabel("Bet amount: ", JLabel.TRAILING);

        betField = new JSpinner(new SpinnerNumberModel(1, 1,
                player.getPoints(), 1));
        betField.setAlignmentX(LEFT_ALIGNMENT);

        nameLabel.setLabelFor(nameValue);
        pointsLabel.setLabelFor(pointsValue);
        luckyNumberLabel.setLabelFor(luckyNumberField);
        betLabel.setLabelFor(betField);

        placeBet = new JButton("Place bet");
        placeBet.addActionListener(new PlaceBetListener(this.gameWindow,
                this.gameEngine, this));

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.anchor = GridBagConstraints.LINE_END;
        labelConstraints.fill = GridBagConstraints.BOTH;
        GridBagConstraints valueConstraints = new GridBagConstraints();
        valueConstraints.gridx = 1;
        valueConstraints.gridy = 0;
        valueConstraints.anchor = GridBagConstraints.LINE_START;
        valueConstraints.fill = GridBagConstraints.BOTH;
        GridBagConstraints buttonConstraints = new GridBagConstraints();
        buttonConstraints.gridwidth = 2;

        this.add(nameLabel, labelConstraints);
        this.add(nameValue, valueConstraints);
        labelConstraints.gridy++;
        valueConstraints.gridy++;
        this.add(pointsLabel, labelConstraints);
        this.add(pointsValue, valueConstraints);
        labelConstraints.gridy++;
        valueConstraints.gridy++;
        this.add(luckyNumberLabel, labelConstraints);
        this.add(luckyNumberField, valueConstraints);
        labelConstraints.gridy++;
        valueConstraints.gridy++;
        this.add(betLabel, labelConstraints);
        this.add(betField, valueConstraints);
        buttonConstraints.gridy = labelConstraints.gridy + 1;
        this.add(placeBet, buttonConstraints);
    }

    /**
     * TODO
     */
    public void update()
    {
        // Set player fields to updated values
        nameValue.setText(player.getPlayerName());
        pointsValue.setText(Integer.toString(player.getPoints()));

        // Reset JSpinner fields to minimum values
        luckyNumberField.setValue(((SpinnerNumberModel) luckyNumberField
                .getModel()).getMinimum());
        betField.setValue(((SpinnerNumberModel) betField.getModel())
                .getMinimum());

        // Re-enable place bet button
        placeBet.setEnabled(true);

        gameWindow.revalidate();
    }

    /**
     * @return the {@code Player} object for this {@code GamePlayerPanel}
     */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
     * @return the betField for this {@code GamePlayerPanel}
     */
    public JSpinner getBetField()
    {
        return this.betField;
    }

    /**
     * @return the luckyNumberField for this {@code GamePlayerPanel}
     */
    public JSpinner getLuckyNumberfield()
    {
        return this.luckyNumberField;
    }

    /**
     * @return the placeBet button for this {@code GamePlayerPanel}
     */
    public JButton getBetButton()
    {
        return this.placeBet;
    }
}
