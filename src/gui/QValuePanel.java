package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

import maze.Direction;

public class QValuePanel extends JPanel
{
	private static final long serialVersionUID = -6075907538876446276L;

	private final int tileWidth = 60;
	private final int tileHeight = 60;
	private final int xSpacing = 70;
	private final int ySpacing = 70;

	private int xSize;
	private int ySize;

	private JLabel[][][] qValueLabels;

	private DecimalFormat decimalFormat;

	public QValuePanel(int xSize, int ySize)
	{
		setLayout(null);

		this.xSize = xSize;
		this.ySize = ySize;

		qValueLabels = new JLabel[xSize][ySize][4];
		createQValueLabels();

		decimalFormat = new DecimalFormat("#0.0");
	}

	private void createQValueLabels()
	{
		JLabel label;
		int xPosition, yPosition;
		for (int x = 0; x < xSize; x++)
		{
			for (int y = 0; y < ySize; y++)
			{
				for (int z = 0; z < 4; z++)
				{
					xPosition = (x + 1) * xSpacing + 5;
					yPosition = (y + 1) * ySpacing;
					if(z == 0 || z == 2)
					{
						xPosition += 15;
						if(z == 0)
						{
							yPosition -= 18;
						}
						else
						{
							yPosition += 15;	
						}
					}
					if(z == 1)
					{
						xPosition += 30;
					}

					label = new JLabel("0.0");
					label.setFont(new Font("", Font.PLAIN, 13));
					label.setForeground(Color.white);
					label.setSize(10, 10);
					label.setBounds(xPosition, yPosition, tileWidth, tileHeight);
					qValueLabels[x][y][z] = label;
					add(qValueLabels[x][y][z]);
				}
			}
		}
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{		
		int z = 0;
		switch(direction)
		{
			case UP:
				z = 0;
				break;
			case RIGHT:
				z = 1;
				break;
			case DOWN:
				z = 2;
				break;
			case LEFT:
				z = 3;
				break;
		}

		qValueLabels[x][y][z].setText(decimalFormat.format(q));
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
