package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import maze.Direction;

public class QValuePanel extends JPanel
{
	private static final long serialVersionUID = -6075907538876446276L;

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
				xPosition = (x + 1) * MazePanel.xSpacing;
				yPosition = (y + 1) * MazePanel.ySpacing;
				JPanel tilePanel = new JPanel();
				tilePanel.setBackground(MazePanel.neutralColor);
				tilePanel.setLayout(new BorderLayout());
				tilePanel.setBounds(xPosition, yPosition, MazePanel.tileWidth,
						MazePanel.tileHeight);
				add(tilePanel);

				for (int z = 0; z < 4; z++)
				{
					label = new JLabel("0.0", SwingConstants.CENTER);
					label.setFont(new Font("", Font.PLAIN, 13));
					label.setForeground(Color.white);
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
		}
	}

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
			qValueLabel.setForeground(MazePanel.neutralColor);
		}
	}

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
