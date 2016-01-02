
public class EnvironmentManager
{
	private static Maze maze;

	public static void main(String[] args)
	{
		maze = new Maze();
	}

	public static int executeMove(int x, int y)
	{
		return maze.getTileValue(x, y);
	}
}
