
public class EnvironmentManager
{
	private static Maze maze;
	private static Agent agent;

	public static void main(String[] args)
	{
		maze = new Maze();
		agent = new Agent();
	}

	public static int executeMove(int x, int y)
	{
		return maze.getTileValue(x, y);
	}
}
