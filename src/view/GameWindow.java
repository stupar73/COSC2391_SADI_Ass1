package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
    private GameWheelPanel wheelPanel;
    private Collection<GamePlayerPanel> playerPanels;
    private Collection<Player> visiblePlayers;

    public GameWindow(GameEngine gameEngine)
    {
        super("Spin the Wheel Game");

        // Disable default bold font
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        this.gameEngine = gameEngine;

        this.menuBar = new GameMenuBar(this, gameEngine);
        this.setJMenuBar(menuBar);

        this.toolbar = new GameToolbar(this, gameEngine);
        this.add(this.toolbar, BorderLayout.PAGE_START);

        this.playerPanelsContainer = new JPanel();
        this.add(this.playerPanelsContainer, BorderLayout.CENTER);
        this.playerPanels = new ArrayList<GamePlayerPanel>();
        this.visiblePlayers = new ArrayList<Player>();

        this.wheelPanel = new GameWheelPanel(this, gameEngine);
        this.add(wheelPanel, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationByPlatform(true);
        this.setMinimumSize(new Dimension(480, 360));
        this.pack();
        this.setVisible(true);
    }

    /**
     * Update the GUI to display any added player and hide any removed players
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
     * Create a {@code GamePlayerPanel} for {@code player} and add it to
     * {@code playerPanels} and {@code playerPanelsContainer} as well as adding
     * {@code player} to {@code visiblePlayers}
     *
     * @param player
     *            the {@code Player} whose {@code GamePlayerPanel} we want to
     *            add to the GUI
     */
    private void addPlayerPanel(Player player)
    {
        GamePlayerPanel playerPanel = new GamePlayerPanel(this, this.gameEngine,
                player);

        // Player should not already be present
        assert (!playerPanels.contains(player));

        this.playerPanels.add(playerPanel);
        this.playerPanelsContainer.add(playerPanel);
        this.visiblePlayers.add(player);
    }

    /**
     * Remove player's {@code GamePlayerPanel} from {@code playerPanels} and
     * {@code playerPanelsContainer} as well remove the associated
     * {@code Player} from {@code visiblePlayers}
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
     * Get the {@code GamePlayerPanel} for {@code Player} player
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
     * @return TODO
     */
    public Collection<GamePlayerPanel> getPlayerPanels()
    {
        return playerPanels;
    }

    /**
     * @return all {@code Player}s that are displayed on the screen
     */
    private Collection<Player> getAllVisiblePlayers()
    {
        return Collections.unmodifiableCollection(
                new ArrayList<Player>(this.visiblePlayers));
    }

    /**
     * @return TODO
     */
    public GameWheelPanel getWheelPanel()
    {
        return wheelPanel;
    }
}
