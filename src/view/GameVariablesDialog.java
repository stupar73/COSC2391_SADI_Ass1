package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import model.GameVariables;
import model.interfaces.GameEngine;

/**
 * Dialog that contains all game variables and allows user to adjust them.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameVariablesDialog extends JPanel
{
    private GameWindow window;
    private GameEngine engine;
    private JSpinner wheelSize;
    private JSpinner startingPoints;
    private JSpinner initialDelay;
    private JSpinner finalDelay;
    private JSpinner delayIncrement;
    private JSpinner maxPlayers;

    public GameVariablesDialog(GameWindow window, GameEngine engine)
    {
        this.window = window;
        this.engine = engine;

        this.setLayout(new GridBagLayout());

        GridBagConstraints infoConstraints = new GridBagConstraints();
        infoConstraints.anchor = GridBagConstraints.LINE_START;
        infoConstraints.gridy = 0;
        infoConstraints.gridwidth = 2;
        infoConstraints.ipady = 10;
        this.add(new JLabel("Enter new game variables:"), infoConstraints);

        wheelSize = this.addLabeledSpinner("Wheel size: ",
                new SpinnerNumberModel(
                        GameVariables.getWheelSize(), 1, Integer.MAX_VALUE, 1),
                1);

        startingPoints = this.addLabeledSpinner("Starting points: ",
                new SpinnerNumberModel(
                        GameVariables.getStartingPoints(), 1, Integer.MAX_VALUE,
                        1), 2);

        initialDelay = this.addLabeledSpinner("Initial delay upper bound: ",
                new SpinnerNumberModel(
                        GameVariables.getInitialDelayUpper(), 1,
                        Integer.MAX_VALUE, 1), 3);

        finalDelay = this.addLabeledSpinner("Final delay upper bound: ",
                new SpinnerNumberModel(
                        GameVariables.getFinalDelayUpper(), 1,
                        Integer.MAX_VALUE, 1), 4);

        delayIncrement = this.addLabeledSpinner("Delay increment: ",
                new SpinnerNumberModel(
                        GameVariables.getDelayIncrement(), 1, Integer.MAX_VALUE,
                        1), 5);
        maxPlayers = this.addLabeledSpinner("Max players: ",
                new SpinnerNumberModel(
                        GameVariables.getMaxPlayers(), 1, Integer.MAX_VALUE, 1),
                6);
    }

    /**
     * Display dialog containing fields to modify game variables. Fields are
     * pre-populated with current values for the variables.
     */
    public void display()
    {
        int selection = JOptionPane.showConfirmDialog(this.window, this,
                "Game Variables", JOptionPane.OK_CANCEL_OPTION);

        if (selection == JOptionPane.OK_OPTION)
        {
            changeGameVariables();
        }
    }

    /**
     * Set modified game variables.
     */
    private void changeGameVariables()
    {
        GameVariables.setWheelSize((int) wheelSize.getValue());
        GameVariables.setStartingPoints((int) startingPoints.getValue());
        GameVariables.setInitialDelayUpper((int) initialDelay.getValue());
        GameVariables.setFinalDelayUpper((int) finalDelay.getValue());
        GameVariables.setDelayIncrement((int) delayIncrement.getValue());
        /*
         * Make sure new max player count is not less than the current number of
         * players
         */
        if ((int) maxPlayers.getValue() < this.engine.getAllPlayers()
                .size())
        {
            JOptionPane.showMessageDialog(this.window, "Cannot set new max "
                    + "player count, new max player count must not exceed "
                    + "current number of players.");
        }
        else
        {
            GameVariables.setMaxPlayers((int) maxPlayers.getValue());
        }
    }

    /**
     * Adds a JLabel and JSpinner to this JPanel.
     *
     * @param labelText
     *            text the JLabel for the JSpinner should contain
     * @param model
     *            model for the JSpinner
     * @param row
     *            the row in the dialog that it should appear
     * @return the JSpinner that has been added to the JPanel
     */
    private JSpinner addLabeledSpinner(String labelText, SpinnerModel model,
            int row)
    {
        JLabel label = new JLabel(labelText);
        JSpinner spinner = new JSpinner(model);
        label.setLabelFor(spinner);

        // Add label
        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = row;
        labelConstraints.anchor = GridBagConstraints.LINE_END;
        labelConstraints.ipadx = 5;
        this.add(label, labelConstraints);

        // Add JSpinner
        GridBagConstraints spinnerConstraints = new GridBagConstraints();
        spinnerConstraints.gridx = 1;
        spinnerConstraints.gridy = row;
        spinnerConstraints.anchor = GridBagConstraints.LINE_END;
        this.add(spinner, spinnerConstraints);

        return spinner;
    }
}
