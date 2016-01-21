package maze;

import java.util.Random;

/**
 * Represents a maze using a 2D array.
 */
public class Maze
{
	private final int minSize = 2;
	private final int maxSize = 10;

	private Random random;
	private int[][] maze;

	private int xSize;
	private int ySize;

	public Maze()
	{
		random = new Random();

		// Randomly determine dimensions
		xSize = random.nextInt(maxSize - 1) + minSize;
		ySize = random.nextInt(maxSize - 1) + minSize;
		maze = new int[xSize][ySize];
		System.out.println("Creating " + xSize + " x " + ySize + " maze");

		populateMaze();
	}

	/**
	 * Loops through all tiles and assigns a reward with 0.1 probability or a
	 * punishment with 0.1 probability.
	 */
	public void populateMaze()
	{
		for (int i = 0; i < xSize; i++)
		{
			for (int j = 0; j < ySize; j++)
			{
				double value = random.nextDouble();
				if (value <= 0.1)
				{
					maze[i][j] = -10;
				}
				else if (value >= 0.9)
				{
					maze[i][j] = 10;
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

	public void setXSize(int xSize)
	{
		this.xSize = xSize;
	}

	public int getYSize()
	{
		return ySize;
	}

	public void setYSize(int ySize)
	{
		this.ySize = ySize;
	}
}
