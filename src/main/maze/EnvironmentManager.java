package main.maze;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.agent.Agent;
import main.agent.Direction;
import main.gui.GameWindow;

/**
 * Creates the game window which displays the maze graphically. Instantiates and
 * runs the maze and the agents active in it. Passes moves from agents to the
 * maze, and returns move results to agents.
 */
public class EnvironmentManager
{
	private static GameWindow gameWindow;
	private static Maze maze;
	private static Agent agent;

	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}

		maze = new Maze();
		gameWindow = new GameWindow(maze);
		agent = new Agent(maze);

		run();
	}

	private static void run()
	{
		gameWindow.showMoveAnimation(agent.getPosition());
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
	 * Executes move given by agent and passes move details to GameWindow to
	 * display the move graphically.
	 * 
	 * @param position
	 *            destination position in maze
	 * @throws InvalidPositionException
	 */
	public static void executeMove(Position oldPosition, Direction direction, Position position)
			throws InvalidPositionException
	{
		int result;
		try
		{
			result = maze.getTileValue(position);
		}
		catch (InvalidPositionException e)
		{
			throw e;
		}

		agent.update(position, result);
		gameWindow.processMove(oldPosition, agent.getPosition(), direction, agent.getScore(),
				agent.getUtility(oldPosition, direction));
	}
}
