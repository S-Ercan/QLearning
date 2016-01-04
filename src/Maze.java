
public class Maze
{
	private int[][] maze;
	private int xSize;
	private int ySize;

	public Maze()
	{
		xSize = 2;
		ySize = 2;
		maze = new int[xSize][ySize];
		maze[1][0] = -10;
		maze[1][1] = 10;
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
}
