package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import controller.PlaceBetListener;
import model.GameVariables;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * A panel containing a players information. For assignment 1 there is only one
 * of these, but multiple can readily be added to the GUI up to the maxPlayers
 * game variable limit.
 * <br />
 * <br />
 * Displays a players name, current points, two fields to enter the number they
 * wish to bet on and the number of points to be on said number.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GamePlayerPanel extends JPanel
{
    private GameWindow window;
    private GameEngine engine;
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
    private JLabel betInfo;

    public GamePlayerPanel(GameWindow window, GameEngine engine,
            Player player)
    {
        this.window = window;
        this.engine = engine;
        this.player = player;

        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createTitledBorder("<html><b>Player ID: "
                + player.getPlayerId() + "</b></html>"));

        nameLabel = new JLabel("Name: ", JLabel.TRAILING);

        nameValue = new JLabel(player.getPlayerName());

        pointsLabel = new JLabel("Points: ", SwingConstants.TRAILING);

        pointsValue = new JLabel(Integer.toString(player.getPoints()));

        luckyNumberLabel = new JLabel("Lucky number: ",
                SwingConstants.TRAILING);

        luckyNumberField = new JSpinner(new SpinnerNumberModel(1, 1,
                GameVariables.getWheelSize(), 1));

        betLabel = new JLabel("Bet amount: ", SwingConstants.TRAILING);

        betField = new JSpinner(new SpinnerNumberModel(1, 1,
                player.getPoints(), 1));

        nameLabel.setLabelFor(nameValue);
        pointsLabel.setLabelFor(pointsValue);
        luckyNumberLabel.setLabelFor(luckyNumberField);
        betLabel.setLabelFor(betField);

        placeBet = new JButton("Place bet");
        placeBet.addActionListener(new PlaceBetListener(this.window,
                this.engine, this));

        betInfo = new JLabel("");

        // Create layout constraints for labels, values, and fields
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
        buttonConstraints.anchor = GridBagConstraints.PAGE_END;
        buttonConstraints.insets = new Insets(5, 0, 0, 0);
        GridBagConstraints betInfoConstraints = buttonConstraints;

        // Add components to the panel with specified constraints
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
        betInfoConstraints.gridy = buttonConstraints.gridy + 1;
        this.add(betInfo, betInfoConstraints);
    }

    /**
     * Update the information displayed in this {@code GamePlayerPanel} based
     * on the current game state.
     * <br />
     * <br />
     * Should be called after a spin has been completed.
     *
     * @param result
     *            the lucky number the wheel landed on, used to determine if the
     *            previously placed bet won
     */
    public void update()
    {
        // Set player fields to updated values
        nameValue.setText(player.getPlayerName());
        int prevPoints = Integer.parseInt(pointsValue.getText());
        int currPoints = player.getPoints();
        pointsValue.setText(Integer.toString(currPoints));

        int pointsDiff = currPoints - prevPoints;

        // Update betInfo field
        if (pointsDiff > 0)
        {
            betInfo.setText("<html><i>Previous bet won! " + pointsDiff
                    + " point(s) added.</i></html>");
        }
        else if (pointsDiff < 0)
        {
            betInfo.setText("<html><i>Previous bet lost. "
                    + Math.abs(pointsDiff) + " point(s) removed.</i></html>");
        }
        else
        {
            betInfo.setText("");
        }

        // Set new values for betField JSpinner
        SpinnerNumberModel betModel = (SpinnerNumberModel) betField.getModel();
        betModel.setMaximum(player.getPoints());
        betModel.setValue(betModel.getMinimum());

        // Set new values for luckyNumberField JSpinner
        SpinnerNumberModel luckyNumberModel =
                (SpinnerNumberModel) luckyNumberField.getModel();
        luckyNumberModel.setValue(luckyNumberModel.getMinimum());

        // Re-enable place bet button
        placeBet.setEnabled(true);

        window.revalidate();
    }

    /**
     * @return the Player object for this GamePlayerPanel
     */
    public Player getPlayer()
    {
        return this.player;
    }

    /**
     * @return the betField for this GamePlayerPanel
     */
    public JSpinner getBetField()
    {
        return this.betField;
    }

    /**
     * @return the luckyNumberField for this GamePlayerPanel
     */
    public JSpinner getLuckyNumberfield()
    {
        return this.luckyNumberField;
    }

    /**
     * @return the placeBet button for this GamePlayerPanel
     */
    public JButton getBetButton()
    {
        return this.placeBet;
    }

    /**
     * @return the betInfo field, used to display information about the
     *         bet that has been placed for this Player
     */
    public JLabel getBetPlacedInfo()
    {
        return this.betInfo;
    }
}
