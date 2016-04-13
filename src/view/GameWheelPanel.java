package view;

import java.awt.BorderLayout;
import java.awt.Color;
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
        super(new BorderLayout());
        this.gameWindow = gameWindow;
        this.gameEngine = gameEngine;

        // Add padding
        this.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        spinButton = new JButton("Spin");
        currentWheelValue = new JLabel();
        currentWheelValue.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        spinButton.addActionListener(new SpinWheelListener(gameWindow,
                gameEngine));

        this.add(spinButton, BorderLayout.LINE_END);
        this.add(currentWheelValue, BorderLayout.LINE_START);
    }

    public void updateCurrentWheelValue(int newValue)
    {
        currentWheelValue.setText(Integer.toString(newValue));
    }
}
