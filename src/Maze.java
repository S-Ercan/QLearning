import java.util.Random;

public class Maze
{
	private Random random;
	private int[][] maze;
	private int xSize;
	private int ySize;

	public Maze()
	{
		random = new Random();

		xSize = random.nextInt(9) + 2;
		ySize = random.nextInt(9) + 2;
		maze = new int[xSize][ySize];
		System.out.println("Creating " + xSize + " x " + ySize + " maze");

		populateMaze();
	}

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
