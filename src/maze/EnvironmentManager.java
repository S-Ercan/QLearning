package maze;

/**
 * Instantiates and runs the maze and the agents active in it.
 * Passes moves from agents to the maze, and returns move results to agents.
 */
public class EnvironmentManager
{
	public static void main(String[] args)
	{
		Maze maze = new Maze();
		new Agent(maze).run();
	}

	/**
	 * Execute move and return result.
	 * 
	 * @param maze maze to execute move in
	 * @param x x coordinate of destination tile
	 * @param y y coordinate of destination tile
	 * @return change in score (can be positive and negative)
	 */
	public static int executeMove(Maze maze, int x, int y)
	{
		return maze.getTileValue(x, y);
	}
}
