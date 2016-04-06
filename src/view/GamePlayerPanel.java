package view;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import model.GameVariables;
import model.interfaces.Player;

public class GamePlayerPanel extends JPanel
{
    private GameWindow gameWindow;
    private Player player;
    private JLabel nameLabel;
    private JLabel nameValue;
    private JLabel pointsLabel;
    private JLabel pointsValue;
    private JLabel luckyNumberLabel;
    private JSpinner luckyNumberField;
    private JLabel betLabel;
    private JSpinner betField;

    public GamePlayerPanel(GameWindow gameWindow, Player player)
    {
        this.gameWindow = gameWindow;
        this.player = player;

        this.setLayout(new GridLayout(4, 2));
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

        this.add(nameLabel);
        this.add(nameValue);
        this.add(pointsLabel);
        this.add(pointsValue);
        this.add(luckyNumberLabel);
        this.add(luckyNumberField);
        this.add(betLabel);
        this.add(betField);
    }

    public Player getPlayer()
    {
        return this.player;
    }
}
