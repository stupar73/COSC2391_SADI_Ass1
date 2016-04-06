package view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class GameWindow extends JFrame
{
    private GameEngine gameEngine;
    private GameMenuBar menuBar;
    private GameToolbar toolbar;
    private JPanel playerPanelsContainer;
    private Collection<GamePlayerPanel> playerPanels;
    private Collection<Player> visiblePlayers;

    public GameWindow(GameEngine gameEngine)
    {
        super("Spin the Wheel Game");

        // Disable bold
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        this.gameEngine = gameEngine;

        this.menuBar = new GameMenuBar(this, gameEngine);
        this.setJMenuBar(menuBar);

        this.toolbar = new GameToolbar(this, gameEngine);
        this.add(this.toolbar, BorderLayout.NORTH);

        this.playerPanelsContainer = new JPanel();
        this.add(this.playerPanelsContainer, BorderLayout.CENTER);
        this.playerPanels = new ArrayList<GamePlayerPanel>();
        this.visiblePlayers = new ArrayList<Player>();

        // TODO Wheel display + spin button

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // this.setPreferredSize(new Dimension(800, 600));
        this.pack();
        this.setVisible(true);
    }

    /**
     * TODO
     */
    public void updateVisiblePlayers()
    {
        // Add players
        for (Player player : this.gameEngine.getAllPlayers())
        {
            if (!this.visiblePlayers.contains(player))
            {
                addPlayerPanel(player);
            }
        }
        // Remove players
        for (Player player : this.getAllVisiblePlayers())
        {
            if (!this.gameEngine.getAllPlayers().contains(player))
            {
                removePlayerPanel(player);
            }
        }

        // Update GUI to add/remove players
        this.revalidate();
        this.repaint();
        this.pack();
    }

    /**
     * TODO
     *
     * @param player
     *            the {@code Player} whose {@code GamePlayerPanel} we want to
     *            add to the GUI
     */
    private void addPlayerPanel(Player player)
    {
        GamePlayerPanel playerPanel = new GamePlayerPanel(this, player);

        // Player should not already be present
        assert (!playerPanels.contains(player));

        this.playerPanels.add(playerPanel);
        this.playerPanelsContainer.add(playerPanel);
        this.visiblePlayers.add(player);
    }

    /**
     * TODO
     *
     * @param player
     *            the {@code Player} whose {@code GamePlayerPanel} we want to
     *            remove from the GUI
     */
    private void removePlayerPanel(Player player)
    {
        GamePlayerPanel playerPanel = getPlayersPanel(player);

        // Players panel should be present in playerPanels
        assert (playerPanel != null);

        this.playerPanels.remove(playerPanel);
        this.playerPanelsContainer.remove(playerPanel);
        this.visiblePlayers.remove(player);
    }

    /**
     * TODO
     *
     * @param player
     *            the {@code Player} whose {@code GamePlayerPanel} we're looking
     *            for
     * @return the {@code GamePlayerPanel} for {@code player}, or null if it
     *         does not exist
     */
    private GamePlayerPanel getPlayersPanel(Player player)
    {
        for (GamePlayerPanel panel : this.playerPanels)
        {
            if (panel.getPlayer().equals(player))
            {
                return panel;
            }
        }
        return null;
    }

    /**
     * @return
     */
    private Collection<Player> getAllVisiblePlayers()
    {
        return Collections.unmodifiableCollection(
                new ArrayList<Player>(this.visiblePlayers));
    }
}
