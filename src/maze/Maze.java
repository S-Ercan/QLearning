package maze;

import java.util.Random;

/**
 * Represents a maze using a 2D array.
 */
public class Maze
{
	private final int minSize = 2;
	private final int maxSize = 10;

	private final double pReward = 0.2;
	private final double pPunishment = 0.1;

	private final int rewardValue = 10;
	private final int punishmentValue = -10;

	private Random random;
	private int[][] maze;

	private int xSize;
	private int ySize;

	public Maze()
	{
		// Randomly determine dimensions
		random = new Random();
		xSize = random.nextInt(maxSize - 1) + minSize;
		ySize = random.nextInt(maxSize - 1) + minSize;
		maze = new int[xSize][ySize];
		System.out.println("Creating " + xSize + " x " + ySize + " maze");

		// Fill maze with rewards and punishments
		populateMaze();
	}

	/**
	 * Loops through all tiles and assigns a reward with probability pReward
	 * or a punishment with probability pPunishment.
	 */
	public void populateMaze()
	{
		for (int i = 0; i < xSize; i++)
		{
			for (int j = 0; j < ySize; j++)
			{
				double value = random.nextDouble();
				if (value <= pReward)
				{
					maze[i][j] = rewardValue;
				}
				else if (value >= 1 - pPunishment)
				{
					maze[i][j] = punishmentValue;
				}
			}
		}
		// Ensure initial square is free
		maze[0][0] = 0;
	}

	public int getTileValue(int x, int y)
	{
		int tileValue = -1;
		if (!(x < 0 || x >= xSize || y < 0 || y >= ySize))
		{
			tileValue = maze[x][y];
		}
		return tileValue;
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}
}
