package maze;

import gui.GameWindow;

/**
 * Creates the game window which displays the maze graphically. Instantiates and
 * runs the maze and the agents active in it. Passes moves from agents to the
 * maze, and returns move results to agents.
 */
public class EnvironmentManager
{
	private static GameWindow gameWindow;
	private static Agent agent;

	public static void main(String[] args)
	{
		Maze maze = new Maze();
		gameWindow = new GameWindow(maze);

		agent = new Agent(maze);
		agent.run();
	}

	/**
	 * Execute move and return result.
	 * 
	 * @param maze
	 *            maze to execute move in
	 * @param x
	 *            x coordinate of destination tile
	 * @param y
	 *            y coordinate of destination tile
	 * @return change in score (can be positive and negative)
	 */
	public static int executeMove(Maze maze, int x, int y, Direction direction)
	{
		int result = maze.getTileValue(x, y);
		// Trigger move animation if move was valid
		if (result != -1)
		{
			gameWindow.showMoveAnimation(x, y);
			gameWindow.updateQValue(agent.getXPosition(), agent.getYPosition(), direction,
					agent.getQValue(agent.getXPosition(), agent.getYPosition(), direction));
		}
		return result;
	}
}
