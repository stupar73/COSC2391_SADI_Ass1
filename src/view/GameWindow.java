package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import model.interfaces.GameEngine;
import model.interfaces.Player;

/**
 * Main window that holds all other components of the games GUI.
 *
 * @author Stuart Parker (s3390317)
 *
 */
public class GameWindow extends JFrame
{
    private GameEngine engine;
    private GameMenuBar menuBar;
    private GameToolbar toolbar;
    private JPanel playerPanelsContainer;
    private GameWheelPanel wheelPanel;
    private Map<Player, GamePlayerPanel> playerPanels;
    private Collection<Player> visiblePlayers;

    public GameWindow(GameEngine engine)
    {
        super("Spin the Wheel Game");

        // Disable default bold font
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        this.engine = engine;

        this.menuBar = new GameMenuBar(this, engine);
        this.setJMenuBar(menuBar);

        this.toolbar = new GameToolbar(this, engine);
        this.add(this.toolbar, BorderLayout.PAGE_START);

        this.playerPanelsContainer = new JPanel();
        this.add(this.playerPanelsContainer, BorderLayout.CENTER);
        this.playerPanels = new HashMap<>();
        this.visiblePlayers = new ArrayList<Player>();

        this.wheelPanel = new GameWheelPanel(this, engine);
        this.add(wheelPanel, BorderLayout.PAGE_END);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLocationByPlatform(true);
        this.setMinimumSize(new Dimension(480, 360));
        this.pack();
        this.setVisible(true);
    }

    /**
     * Update the GUI to display any added players and hide any removed players
     */
    public void updateVisiblePlayers()
    {
        // Add players
        for (Player player : this.engine.getAllPlayers())
        {
            if (!this.visiblePlayers.contains(player))
            {
                addPlayerPanel(player);
            }
        }
        // Remove players
        for (Player player : this.getAllVisiblePlayers())
        {
            if (!this.engine.getAllPlayers().contains(player))
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
     * Create a GamePlayerPanel for {@code player} and add it to
     * {@code playerPanels} and {@code playerPanelsContainer} as well as adding
     * {@code player} to {@code visiblePlayers}
     *
     * @param player
     *            the Player whose GamePlayerPanel we want to add to the GUI
     */
    private void addPlayerPanel(Player player)
    {
        GamePlayerPanel playerPanel = new GamePlayerPanel(this, this.engine,
                player);

        // Player should not already be present
        assert (!playerPanels.containsKey(player));

        this.playerPanels.put(player, playerPanel);
        this.playerPanelsContainer.add(playerPanel);
        this.visiblePlayers.add(player);
    }

    /**
     * Remove {@code player}'s GamePlayerPanel from {@code playerPanels} and
     * {@code playerPanelsContainer} as well remove the associated
     * Player from {@code visiblePlayers}
     *
     * @param player
     *            the Player whose GamePlayerPanel we want to remove from the
     *            GUI
     */
    private void removePlayerPanel(Player player)
    {
        GamePlayerPanel playerPanel = playerPanels.get(player);

        // Players panel should be present in playerPanels
        assert (playerPanel != null);

        this.playerPanels.remove(playerPanel);
        this.playerPanelsContainer.remove(playerPanel);
        this.visiblePlayers.remove(player);
    }

    /**
     * @return all GamePlayerPanels present in {@code playerPanels}
     */
    public Collection<GamePlayerPanel> getPlayerPanels()
    {
        return playerPanels.values();
    }

    /**
     * @return all Players that are displayed on the screen
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
