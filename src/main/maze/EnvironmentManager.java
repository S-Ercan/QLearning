package main.maze;

import main.gui.GameWindow;

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

		run();
	}

	private static void run()
	{
		gameWindow.showMoveAnimation(agent.getXPosition(), agent.getYPosition());
		while (true)
		{
			if (gameWindow.simulationIsRunning())
			{
				agent.move();
			}
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
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
	 */
	public static void executeMove(Maze maze, int x, int y, Direction direction)
	{
		int result = maze.getTileValue(x, y);
		int agentXOld = agent.getXPosition();
		int agentYOld = agent.getYPosition();
		agent.update(x, y, direction, result);
		// Trigger move animation if move was valid
		if (result != -1)
		{
			gameWindow.showMoveAnimation(x, y);
			gameWindow.updateScore(agent.getScore());
			gameWindow.updateQValue(agentXOld, agentYOld, direction,
					agent.getQValue(agentXOld, agentYOld, direction));
		}
	}
}
