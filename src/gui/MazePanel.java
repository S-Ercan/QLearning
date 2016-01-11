package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MazePanel extends JPanel
{
	private static final long serialVersionUID = 5364142617462688939L;
	private final int tileWidth = 25;
	private final int tileHeight = 25;
	private final int xSpacing = 30;
	private final int ySpacing = 30;

	private int xSize;
	private int ySize;

	public MazePanel(int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		
		setLayout(new GridLayout(xSize, ySize));
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		
		for(int x = 1; x <= xSize; x++)
		{
			for(int y = 1; y <= ySize; y++)
			{
				g.fillRect(x * xSpacing, y * ySpacing, tileWidth, tileHeight);
			}
		}
	}
}
