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

public class GameWheelPanel extends JPanel
{
    private GameWindow gameWindow;
    private GameEngine gameEngine;
    private JButton spinButton;
    private JLabel currentWheelValue;

    public GameWheelPanel(GameWindow gameWindow, GameEngine gameEngine)
    {
        this.setLayout(new GridBagLayout());
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;

        // Add padding
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        spinButton = new JButton("Spin");
        Font spinFont = spinButton.getFont();
        spinButton.setFont(new Font(spinFont.getFontName(),
                Font.BOLD, 32));
        spinButton.setEnabled(false); // Active when there's at least one player

        currentWheelValue = new JLabel("", JLabel.CENTER);
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(new Font(wheelFont.getFontName(),
                Font.PLAIN, 38));
        currentWheelValue.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        currentWheelValue.setPreferredSize(new Dimension(80, 80));

        spinButton.addActionListener(new SpinWheelListener(gameWindow,
                gameEngine));

        this.add(spinButton);
    }

    public void updateCurrentWheelValue(int newValue)
    {
        currentWheelValue.setText(Integer.toString(newValue));
        unboldenWheelText();
    }

    public void finalCurrentWheelValue(int newValue)
    {
        updateCurrentWheelValue(newValue);
        emboldenWheelText();
    }

    public void showSpinButton()
    {
        this.remove(currentWheelValue);

        this.add(spinButton);
        this.revalidate();
    }

    public void showWheel()
    {
        this.remove(spinButton);
        this.add(currentWheelValue);
        this.revalidate();
    }

    public void emboldenWheelText()
    {
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(wheelFont.deriveFont(
                wheelFont.getStyle() | Font.BOLD));
    }

    public void unboldenWheelText()
    {
        Font wheelFont = currentWheelValue.getFont();
        currentWheelValue.setFont(wheelFont.deriveFont(
                wheelFont.getStyle() & ~Font.BOLD));
    }

    public void activateSpinButton()
    {
        spinButton.setEnabled(true);
    }

    public void deactivateSpinButton()
    {
        spinButton.setEnabled(false);
    }
}
