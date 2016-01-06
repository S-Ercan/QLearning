package maze;

public class EnvironmentManager
{
	public static void main(String[] args)
	{
		Maze maze = new Maze();
		new Agent(maze).run();
	}

	public static int executeMove(Maze maze, int x, int y)
	{
		return maze.getTileValue(x, y);
	}
}
