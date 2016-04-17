package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.SpinWheelListener;
import model.interfaces.GameEngine;

/**
 * Panel that displays either a spin button (when the wheel is not spinning), or
 * the current value of the wheel (when the wheel is spinning).
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameWheelPanel extends JPanel
{
    private GameWindow window;
    private GameEngine engine;
    private JButton spinButton;
    private JLabel currentWheelValue;

    public GameWheelPanel(GameWindow window, GameEngine engine)
    {
        this.setLayout(new GridBagLayout());
        this.window = window;
        this.engine = engine;

        // Add padding
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        spinButton = new JButton("Spin");
        Font spinFont = spinButton.getFont();
        spinButton.setFont(new Font(spinFont.getFontName(),
                Font.BOLD, 32));
        deactivateSpinButton(); // Disabled until there's at least one player

        currentWheelValue = new JLabel("", JLabel.CENTER);
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(new Font(wheelFont.getFontName(),
                wheelFont.getStyle(), 38));
        currentWheelValue.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        currentWheelValue.setPreferredSize(new Dimension(80, 80));

        spinButton.addActionListener(new SpinWheelListener(this.window,
                this.engine));

        this.add(spinButton);
    }

    /**
     * Updates the JLabel to display the new value of the wheel in the game
     * engine.
     *
     * @param newValue
     *            the new value of the wheel
     */
    public void updateCurrentWheelValue(int newValue)
    {
        currentWheelValue.setText(Integer.toString(newValue));
        unboldenWheelText();
    }

    /**
     * Updates the JLabel to display the final value of the wheel in the game
     * engine.
     *
     * @param newValue
     *            the final value of the wheel
     */
    public void finalCurrentWheelValue(int newValue)
    {
        updateCurrentWheelValue(newValue);
        emboldenWheelText();
    }

    /**
     * Hide the wheel JLabel and display the spin JButton
     */
    public void showSpinButton()
    {
        this.remove(currentWheelValue);

        this.add(spinButton);
        this.revalidate();
    }

    /**
     * Hide the spin JButton and display the wheel JLabel
     */
    public void showWheel()
    {
        this.remove(spinButton);
        this.add(currentWheelValue);
        this.revalidate();
    }

    /**
     * Make the wheel value text bold. Used to indicate the wheel has stopped
     * spinning.
     */
    public void emboldenWheelText()
    {
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(wheelFont.deriveFont(
                wheelFont.getStyle() | Font.BOLD));
    }

    /**
     * Make the wheel value text plain. Used to indicate the wheel has stopped
     * spinning.
     */
    public void unboldenWheelText()
    {
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(wheelFont.deriveFont(
                wheelFont.getStyle() & ~Font.BOLD));
    }

    /**
     * Enable the JButton for the spin and remove the tooltip.
     */
    public void activateSpinButton()
    {
        spinButton.setEnabled(true);
        spinButton.setToolTipText(null);
    }

    /**
     * Disable the JButton for the spin and add a tooltip explaining why it's
     * inactive.
     */
    public void deactivateSpinButton()
    {
        spinButton.setEnabled(false);
        spinButton.setToolTipText("Cannot spin wheel until a player has been "
                + "added");
    }
}
