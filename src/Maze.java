
public class Maze
{
	private int[][] maze;
	
	public Maze()
	{
		maze = new int[2][2];
		maze[1][0] = -10;
		maze[1][1] = 10;
	}
	
	public int getTileValue(int x, int y)
	{
		return maze[x][y];
	}
}
