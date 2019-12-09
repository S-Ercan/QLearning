package main.gui;

import main.agent.Direction;
import main.maze.Position;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * JPanel for displaying the updated Q-values as the agent learns them while
 * moving through the maze.
 */
public class QValuesPanel extends JPanel {
    private static final long serialVersionUID = -6075907538876446276L;

    private static final int tilePanelPadding = 5;

    private int xSize;
    private int ySize;

    private JLabel[][][] qValueLabels;

    private DecimalFormat decimalFormat;

    /**
     * Creates a new Q-values panel with the given dimensions. Creates and adds
     * Q-value labels.
     *
     * @param xSize maze width
     * @param ySize maze height
     */
    public QValuesPanel(int xSize, int ySize) {
        setLayout(null);

        this.xSize = xSize;
        this.ySize = ySize;

        qValueLabels = new JLabel[xSize][ySize][Direction.values().length];
        createQValueLabels();

        decimalFormat = new DecimalFormat("#0.0");
    }

    /**
     * Creates a JPanel for each maze tile and adds a JLabel for each direction
     * to all created JPanels.
     */
    private void createQValueLabels() {
        JPanel tilePanel;
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                // Create panel
                tilePanel = createPanel(x, y);
                // Create labels for each direction
                createLabels(x, y, tilePanel);
            }
        }
    }

    /**
     * Creates a JPanel for tile (x, y) which will contain Q-value labels.
     *
     * @param x x coordinate of tile
     * @param y y coordinate of tile
     * @return JPanel created for tile (x, y)
     */
    private JPanel createPanel(int x, int y) {
        // Determine positioning of panel
        int xPosition = MazePanel.xMargin + x * MazePanel.xSpacing;
        int yPosition = MazePanel.yMargin + y * MazePanel.ySpacing;

        // Create and add panel
        JPanel tilePanel = new JPanel();
        tilePanel.setBackground(MazePanel.neutralColor);
        tilePanel.setLayout(new BorderLayout());
        tilePanel.setBounds(xPosition, yPosition, MazePanel.tileWidth, MazePanel.tileHeight);
        tilePanel.setBorder(new EmptyBorder(tilePanelPadding, tilePanelPadding, tilePanelPadding,
                tilePanelPadding));
        add(tilePanel);
        return tilePanel;
    }

    /**
     * Creates a JLabel for each direction and adds them to tilePanel.
     *
     * @param x         x coordinate of tile
     * @param y         y coordinate of tile
     * @param tilePanel JPanel created for tile (x, y)
     */
    private void createLabels(int x, int y, JPanel tilePanel) {
        for (int z = 0; z < Direction.values().length; z++) {
            JLabel label = new JLabel("0.0", SwingConstants.CENTER);
            label.setFont(new Font("", MazePanel.fontStyle, MazePanel.fontSize));
            label.setForeground(MazePanel.textColor);
            qValueLabels[x][y][z] = label;

            String index = BorderLayout.CENTER;
            switch (z) {
                case 0:
                    index = BorderLayout.NORTH;
                    break;
                case 1:
                    index = BorderLayout.EAST;
                    break;
                case 2:
                    index = BorderLayout.SOUTH;
                    break;
                case 3:
                    index = BorderLayout.WEST;
                    break;
            }
            tilePanel.add(label, index);
        }
    }

    /**
     * Updates Q-value and font color of Q-value label specified by (x, y) and
     * direction.
     *
     * @param position  position of tile to update Q-value for
     * @param direction direction for which to update Q-value
     * @param q         new Q-value
     */
    public void updateQValue(Position position, Direction direction, double q) {
        JLabel qValueLabel = qValueLabels[position.getX()][position.getY()][direction.ordinal()];
        qValueLabel.setText(decimalFormat.format(q));
        if (q > 0) {
            qValueLabel.setForeground(MazePanel.rewardColor);
        } else if (q < 0) {
            qValueLabel.setForeground(MazePanel.punishmentColor);
        } else {
            qValueLabel.setForeground(MazePanel.textColor);
        }
    }
}
