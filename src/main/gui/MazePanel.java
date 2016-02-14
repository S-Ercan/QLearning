package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.maze.Maze;
import main.maze.tile.PunishmentTile;
import main.maze.tile.RewardTile;
import main.maze.tile.Tile;

/**
 * JPanel for displaying maze and animating agent movement through it.
 */
public class MazePanel extends JPanel
{
	private static final long serialVersionUID = 5364142617462688939L;

	// Mapping from x and y coordinates to color
	private Color[][] colorMap;
	// Mapping from x and y coordinates to tile panel
	private JPanel[][] tilePanelMap;
	// Mapping from x and y coordinates to tile value label
	private JLabel[][] tileValueLabelMap;

	public static final int xMargin = 10;
	public static final int yMargin = 10;
	public static final int tileWidth = 70;
	public static final int tileHeight = 70;
	public static final int xSpacing = tileWidth + xMargin;
	public static final int ySpacing = tileHeight + yMargin;

	public static final Color neutralColor = Color.gray;
	public static final Color rewardColor = Color.green;
	public static final Color punishmentColor = Color.red;

	private Maze maze;
	private int xSize;
	private int ySize;

	private int xOld;
	private int yOld;

	private DecimalFormat decimalFormat;

	public MazePanel(Maze maze)
	{
		setLayout(null);

		this.maze = maze;
		this.xSize = maze.getXSize();
		this.ySize = maze.getYSize();

		this.colorMap = new Color[xSize][ySize];
		this.tilePanelMap = new JPanel[xSize][ySize];
		this.tileValueLabelMap = new JLabel[xSize][ySize];

		decimalFormat = new DecimalFormat("#0.0");

		createTileValueLabels();
	}

	private void createTileValueLabels()
	{
		Tile tile;
		Color tileColor;
		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++)
			{
				// Determine positioning of panel
				int xPosition = MazePanel.xMargin + x * MazePanel.xSpacing;
				int yPosition = MazePanel.yMargin + y * MazePanel.ySpacing;

				// Create and add panel
				JPanel tilePanel = new JPanel();
				tilePanel.setLayout(new BorderLayout());

				tile = maze.getTile(x, y);
				if (tile instanceof RewardTile)
				{
					tileColor = rewardColor;
				}
				else if (tile instanceof PunishmentTile)
				{
					tileColor = punishmentColor;
				}
				else
				{
					tileColor = neutralColor;
				}
				colorMap[x][y] = tileColor;
				tilePanel.setBackground(tileColor);

				tilePanel.setBounds(xPosition, yPosition, MazePanel.tileWidth,
						MazePanel.tileHeight);
				tilePanelMap[x][y] = tilePanel;
				add(tilePanel);
				String labelText = (tile instanceof RewardTile || tile instanceof PunishmentTile)
						? decimalFormat.format(tile.getValue()) : "";
				JLabel label = new JLabel(labelText, SwingConstants.CENTER);
				label.setFont(new Font("", Font.TRUETYPE_FONT, 14));
				label.setForeground(Color.white);
				tileValueLabelMap[x][y] = label;
				tilePanel.add(label);

			}
		}
	}

	/**
	 * Animates an agent's move to tile (x, y) by first restoring the agent's
	 * previous tile to its original color and then painting its current tile
	 * white.
	 * 
	 * @param x
	 *            x coordinate of destination tile
	 * @param y
	 *            y coordinate of destination tile
	 */
	public void showMoveAnimation(int x, int y)
	{
		// Restore color of previous tile
		tilePanelMap[xOld][yOld].setBackground(colorMap[xOld][yOld]);
		// Paint current tile white
		tilePanelMap[x][y].setBackground(Color.white);

		// Save current coordinates
		this.xOld = x;
		this.yOld = y;
	}

	public int getTileWidth()
	{
		return tileWidth;
	}

	public int getTileHeight()
	{
		return tileHeight;
	}
}
