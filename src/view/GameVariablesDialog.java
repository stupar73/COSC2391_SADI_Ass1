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

public class GameVariablesDialog extends JPanel
{
    private JSpinner wheelSize;
    private JSpinner startingPoints;
    private JSpinner initialDelay;
    private JSpinner finalDelay;
    private JSpinner delayIncrement;

    public GameVariablesDialog()
    {
        this.setLayout(new GridBagLayout());

        wheelSize = this.addLabeledSpinner("Wheel size: ", wheelSize,
                new SpinnerNumberModel(GameVariables.wheelSize, 1,
                        Integer.MAX_VALUE, 1), 0);

        startingPoints = this.addLabeledSpinner("Starting points: ",
                startingPoints, new SpinnerNumberModel(
                        GameVariables.startingPoints, 1, Integer.MAX_VALUE, 1),
                1);

        initialDelay = this.addLabeledSpinner("Initial delay: ", initialDelay,
                new SpinnerNumberModel(GameVariables.initialDelay, 1,
                        Integer.MAX_VALUE, 1), 2);

        finalDelay = this.addLabeledSpinner("Final delay: ", finalDelay,
                new SpinnerNumberModel(GameVariables.finalDelay, 1,
                        Integer.MAX_VALUE, 1), 3);

        delayIncrement = this.addLabeledSpinner("Delay increment: ",
                delayIncrement, new SpinnerNumberModel(
                        GameVariables.delayIncrement, 1, Integer.MAX_VALUE, 1),
                4);
    }

    public void show()
    {
        int okOrCancel = JOptionPane.showConfirmDialog(null, this,
                "Enter new game variables", JOptionPane.OK_CANCEL_OPTION);

        if (okOrCancel == JOptionPane.OK_OPTION)
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
    }

    private JSpinner addLabeledSpinner(String labelText, JSpinner spinner,
            SpinnerModel model, int row)
    {
        JLabel label = new JLabel(labelText);
        spinner = new JSpinner(model);
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
        this.add(spinner, spinnerConstraints);

        return spinner;
    }
}
