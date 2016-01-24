package main.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.maze.Direction;

/**
 * JPanel for displaying the updated Q-values as the agent learns them while
 * moving through the maze.
 */
public class QValuePanel extends JPanel
{
	private static final long serialVersionUID = -6075907538876446276L;

	private final int tilePanelPadding = 5;

	private final int fontStyle = Font.TRUETYPE_FONT;
	private final int fontSize = 14;
	private final Color textColor = Color.white;

	private int xSize;
	private int ySize;

	private JLabel[][][] qValueLabels;

	private DecimalFormat decimalFormat;

	/**
	 * Creates a new Q-values panel with the given dimensions. Creates and adds
	 * Q-value labels.
	 * 
	 * @param xSize
	 *            maze width
	 * @param ySize
	 *            maze height
	 */
	public QValuePanel(int xSize, int ySize)
	{
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
	private void createQValueLabels()
	{
		JPanel tilePanel;
		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++)
			{
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
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @return JPanel created for tile (x, y)
	 */
	private JPanel createPanel(int x, int y)
	{
		// Determine positioning of panel
		int xPosition = (x + 1) * MazePanel.xSpacing;
		int yPosition = (y + 1) * MazePanel.ySpacing;

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
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @param tilePanel
	 *            JPanel created for tile (x, y)
	 */
	private void createLabels(int x, int y, JPanel tilePanel)
	{
		for (int z = 0; z < Direction.values().length; z++)
		{
			JLabel label = new JLabel("0.0", SwingConstants.CENTER);
			label.setFont(new Font("", fontStyle, fontSize));
			label.setForeground(textColor);
			qValueLabels[x][y][z] = label;

			String index = BorderLayout.CENTER;
			switch (z)
			{
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
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @param direction
	 *            direction for which to update Q-value
	 * @param q
	 *            new Q-value
	 */
	public void updateQValue(int x, int y, Direction direction, double q)
	{
		JLabel qValueLabel = qValueLabels[x][y][direction.ordinal()];
		qValueLabel.setText(decimalFormat.format(q));
		if (q > 0)
		{
			qValueLabel.setForeground(MazePanel.rewardColor);
		}
		else if (q < 0)
		{
			qValueLabel.setForeground(MazePanel.punishmentColor);
		}
		else
		{
			qValueLabel.setForeground(textColor);
		}
	}

	/**
	 * Draws rectangles for all maze tiles.
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (int x = 1; x <= xSize; x++)
		{
			for (int y = 1; y <= ySize; y++)
			{
				g.setColor(MazePanel.neutralColor);
				g.fillRect(x * MazePanel.xSpacing, y * MazePanel.ySpacing, MazePanel.tileWidth,
						MazePanel.tileHeight);
			}
		}
	}
}