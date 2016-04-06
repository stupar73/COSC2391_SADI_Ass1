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

public class GameVariablesDialog extends JPanel
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private JSpinner wheelSize;
    private JSpinner startingPoints;
    private JSpinner initialDelay;
    private JSpinner finalDelay;
    private JSpinner delayIncrement;
    private JSpinner maxPlayers;

    public GameVariablesDialog(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;

        this.setLayout(new GridBagLayout());

        GridBagConstraints infoConstraints = new GridBagConstraints();
        infoConstraints.anchor = GridBagConstraints.LINE_START;
        infoConstraints.gridy = 0;
        infoConstraints.gridwidth = 2;
        infoConstraints.ipady = 10;
        this.add(new JLabel("Enter new game variables:"), infoConstraints);

        wheelSize = this.addLabeledSpinner("Wheel size: ",
                new SpinnerNumberModel(
                        GameVariables.wheelSize, 1, Integer.MAX_VALUE, 1), 1);

        startingPoints = this.addLabeledSpinner("Starting points: ",
                new SpinnerNumberModel(
                        GameVariables.startingPoints, 1, Integer.MAX_VALUE, 1),
                2);

        initialDelay = this.addLabeledSpinner("Initial delay: ",
                new SpinnerNumberModel(
                        GameVariables.initialDelay, 1, Integer.MAX_VALUE, 1),
                3);

        finalDelay = this.addLabeledSpinner("Final delay: ",
                new SpinnerNumberModel(
                        GameVariables.finalDelay, 1, Integer.MAX_VALUE, 1), 4);

        delayIncrement = this.addLabeledSpinner("Delay increment: ",
                new SpinnerNumberModel(
                        GameVariables.delayIncrement, 1, Integer.MAX_VALUE, 1),
                5);
        maxPlayers = this.addLabeledSpinner("Max players: ",
                new SpinnerNumberModel(
                        GameVariables.maxPlayers, 1, Integer.MAX_VALUE, 1),
                6);
    }

    public void show()
    {
        int selection = JOptionPane.showConfirmDialog(this.gameWindow, this,
                "Game Variables", JOptionPane.OK_CANCEL_OPTION);

        if (selection == JOptionPane.OK_OPTION)
        {
            changeGameVariables();
        }
    }

    private void changeGameVariables()
    {
        GameVariables.wheelSize = (int) wheelSize.getValue();
        GameVariables.startingPoints = (int) startingPoints.getValue();
        GameVariables.initialDelay = (int) initialDelay.getValue();
        GameVariables.finalDelay = (int) finalDelay.getValue();
        GameVariables.delayIncrement = (int) delayIncrement.getValue();
        /*
         * Make sure new max player count is not less than the current number of
         * players
         */
        if ((int) maxPlayers.getValue() < this.gameEngine.getAllPlayers()
                .size())
        {
            JOptionPane.showMessageDialog(this.gameWindow, "Cannot set new max "
                    + "player count, max player count must not exceed current "
                    + "number of players.");
        }
        else
        {
            GameVariables.maxPlayers = (int) maxPlayers.getValue();
        }
    }

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
