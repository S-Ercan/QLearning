package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import maze.Direction;

public class QValuePanel extends JPanel
{
	private static final long serialVersionUID = -6075907538876446276L;

	private final int tileWidth = 50;
	private final int tileHeight = 50;
	private final int xSpacing = 60;
	private final int ySpacing = 60;

	private int xSize;
	private int ySize;

	public QValuePanel(int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{
		
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		for (int x = 1; x <= xSize; x++)
		{
			for (int y = 1; y <= ySize; y++)
			{
				g.setColor(Color.gray);
				g.fillRect(x * xSpacing, y * ySpacing, tileWidth, tileHeight);
			}
		}
	}
}
