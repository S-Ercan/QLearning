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
					label.setSize(10, 10);
					label.setBounds(xPosition, yPosition, tileWidth, tileHeight);
					qValueLabels[x][y][z] = label;
					add(qValueLabels[x][y][z]);
				}
			}
		}

		decimalFormat = new DecimalFormat("#0.00");
	}

	public void updateQValue(int x, int y, Direction direction, double q)
	{
		Graphics g = getGraphics();
		g.setFont(new Font("QValueFont", Font.PLAIN, 14));
		g.setColor(Color.white);
		g.drawString(decimalFormat.format(q), (x + 1) * xSpacing + 12, (y + 1) * ySpacing + 15);
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
