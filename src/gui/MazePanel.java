package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

import maze.Maze;

public class MazePanel extends JPanel
{
	private static final long serialVersionUID = 5364142617462688939L;

	private Color[][] colorMap;
	
	private final int tileWidth = 25;
	private final int tileHeight = 25;
	private final int xSpacing = 30;
	private final int ySpacing = 30;

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

	public void moveToSquare(int x, int y)
	{
		Graphics g = getGraphics();
		g.setColor(colorMap[xOld][yOld]);
		g.fillRect((xOld + 1) * xSpacing, (yOld + 1) * ySpacing, tileWidth, tileHeight);
		g.setColor(Color.white);
		g.fillRect((x + 1) * xSpacing, (y + 1) * ySpacing, tileWidth, tileHeight);

		this.xOld = x;
		this.yOld = y;
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Color color;

		for(int x = 1; x <= xSize; x++)
		{
			for(int y = 1; y <= ySize; y++)
			{
				if(maze.getTileValue(x - 1, y - 1) == 10)
				{
					color = Color.green;
				}
				else if(maze.getTileValue(x - 1, y - 1) == -10)
				{
					color = Color.red;
				}
				else
				{
					color = Color.gray;
				}

				colorMap[x - 1][y - 1] = color;
				g.setColor(color);
				g.fillRect(x * xSpacing, y * ySpacing, tileWidth, tileHeight);
			}
		}
	}
}
