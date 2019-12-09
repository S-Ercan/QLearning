package main.gui;

import main.maze.Maze;
import main.maze.Position;
import main.maze.tile.EmptyTile;
import main.maze.tile.PunishmentTile;
import main.maze.tile.RewardTile;
import main.maze.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * JPanel for displaying maze and animating agent movement through it.
 */
public class MazePanel extends JPanel {
    // Sizes, margins and paddings
    public static final int xMargin = 10;
    public static final int yMargin = 10;
    public static final int tileWidth = 75;
    public static final int tileHeight = 75;
    public static final int xSpacing = tileWidth + xMargin;
    public static final int ySpacing = tileHeight + yMargin;
    // Tile colors
    public static final Color neutralColor = new Color(160, 160, 160);
    public static final Color rewardColor = new Color(15, 80, 15);
    public static final Color punishmentColor = new Color(180, 0, 0);
    // Font attributes
    public static final int fontStyle = Font.TRUETYPE_FONT;
    public static final int fontSize = 14;
    public static final Color textColor = Color.white;
    private static final long serialVersionUID = 5364142617462688939L;
    // Mapping from tile types to colors
    private Map<Class<?>, Color> tileTypeToColorMap;
    // Mapping from x and y coordinates to color
    private Color[][] colorMap;
    // Mapping from x and y coordinates to tile panel
    private JPanel[][] tilePanelMap;

    private Maze maze;
    private int xSize;
    private int ySize;

    private int xOld;
    private int yOld;

    private DecimalFormat decimalFormat;

    /**
     * Creates a new panel for the given maze.
     *
     * @param maze maze to create panel for
     */
    public MazePanel(Maze maze) {
        setLayout(null);

        this.maze = maze;
        xSize = maze.getWidth();
        ySize = maze.getHeight();

        // Map tile types to colors
        tileTypeToColorMap = new HashMap<Class<?>, Color>();
        tileTypeToColorMap.put(RewardTile.class, rewardColor);
        tileTypeToColorMap.put(PunishmentTile.class, punishmentColor);
        tileTypeToColorMap.put(EmptyTile.class, neutralColor);

        colorMap = new Color[xSize][ySize];
        tilePanelMap = new JPanel[xSize][ySize];

        decimalFormat = new DecimalFormat("#0.0");

        createTilePanels();
    }

    /**
     * Creates a panel for each maze tile and colors it according to the tile
     * type.
     */
    private void createTilePanels() {
        Tile tile;
        Color tileColor;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                // Determine positioning of panel
                int xPosition = MazePanel.xMargin + x * MazePanel.xSpacing;
                int yPosition = MazePanel.yMargin + y * MazePanel.ySpacing;

                // Create panel
                JPanel tilePanel = new JPanel();
                tilePanel.setLayout(new BorderLayout());

                // Color panel according to the type of its tile
                tile = maze.getTile(x, y);
                tileColor = tileTypeToColorMap.get(tile.getClass());
                colorMap[x][y] = tileColor;
                tilePanel.setBackground(tileColor);

                // Set bounds and add panel
                tilePanel.setBounds(xPosition, yPosition, MazePanel.tileWidth,
                        MazePanel.tileHeight);
                tilePanelMap[x][y] = tilePanel;
                add(tilePanel);

                // Add a label with tile value if value is non-zero
                if (tile instanceof RewardTile || tile instanceof PunishmentTile) {
                    createTileValueLabel(tilePanel, tile);
                }
            }
        }
    }

    /**
     * Creates a label containing the value of tile and adds it to tilePanel.
     *
     * @param tilePanel panel to add label to
     * @param tile      tile to display value for
     */
    private void createTileValueLabel(JPanel tilePanel, Tile tile) {
        JLabel label = new JLabel(decimalFormat.format(tile.getValue()), SwingConstants.CENTER);
        label.setFont(new Font("", fontStyle, fontSize));
        label.setForeground(textColor);
        tilePanel.add(label);
    }

    /**
     * Animates an agent's move to tile (x, y) by first restoring the agent's
     * previous tile to its original color and then painting its current tile
     * white.
     *
     * @param x x coordinate of destination tile
     * @param y y coordinate of destination tile
     */
    public void showMoveAnimation(Position position) {
        int x = position.getX();
        int y = position.getY();
        // Restore color of previous tile
        tilePanelMap[xOld][yOld].setBackground(colorMap[xOld][yOld]);
        // Paint current tile white
        tilePanelMap[x][y].setBackground(Color.white);

        // Save current coordinates
        this.xOld = x;
        this.yOld = y;
    }
}
