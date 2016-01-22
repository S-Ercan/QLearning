package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import maze.Maze;

/**
 * JPanel for displaying maze and animating agent movement through it.
 */
public class MazePanel extends JPanel
{
	private static final long serialVersionUID = 5364142617462688939L;

	// Mapping from x and y coordinates to color
	private Color[][] colorMap;

	private static final int xMargin = 10;
	private static final int yMargin = 10;
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

	public MazePanel(Maze maze)
	{
		this.maze = maze;
		this.xSize = maze.getXSize();
		this.ySize = maze.getYSize();

		this.colorMap = new Color[xSize][ySize];

		setLayout(new GridLayout(xSize, ySize));
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
		Graphics g = getGraphics();
		// Restore color of previous tile
		g.setColor(colorMap[xOld][yOld]);
		g.fillRect((xOld + 1) * xSpacing, (yOld + 1) * ySpacing, tileWidth, tileHeight);
		// Paint current tile white
		g.setColor(Color.white);
		g.fillRect((x + 1) * xSpacing, (y + 1) * ySpacing, tileWidth, tileHeight);

		// Save current coordinates
		this.xOld = x;
		this.yOld = y;
	}

	/**
	 * Paints each maze tile according to its content.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Color color;

		for (int x = 1; x <= xSize; x++)
		{
			for (int y = 1; y <= ySize; y++)
			{
				if (maze.getTileValue(x - 1, y - 1) == 10)
				{
					color = rewardColor;
				}
				else if (maze.getTileValue(x - 1, y - 1) == -10)
				{
					color = punishmentColor;
				}
				else
				{
					color = neutralColor;
				}

				colorMap[x - 1][y - 1] = color;
				g.setColor(color);
				g.fillRect(x * xSpacing, y * ySpacing, tileWidth, tileHeight);
			}
		}
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
