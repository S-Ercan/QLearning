package maze;

import strategy.StrategyProfile;

public class Agent
{
	private final int numSteps = 100;
	private final int moveDelay = 500;

	private Maze maze;
	private StrategyProfile profile;

	private int xPosition;
	private int yPosition;

	private int score;

	public Agent(Maze maze)
	{
		this.maze = maze;
		profile = new StrategyProfile(maze.getXSize(), maze.getYSize());
		xPosition = 0;
		yPosition = 0;
		score = 0;
	}

	/**
	 * Contains main agent loop: chooses and executes a move 'numSteps' times.
	 */
	public void run()
	{
		int stepCounter = 0;
		while (stepCounter < numSteps)
		{
			try
			{
				Thread.sleep(moveDelay);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			chooseMove();
			stepCounter++;
		}
	}

	/**
	 * Uses 'profile' to get the best direction to head from the current tile.
	 * Calculates destination coordinates accordingly and executes the move.
	 */
	public void chooseMove()
	{
		Direction direction = profile.getBestDirectionFromTile(xPosition, yPosition);
		int x = direction == Direction.LEFT ? xPosition - 1
				: direction == Direction.RIGHT ? xPosition + 1 : xPosition;
		int y = direction == Direction.DOWN ? yPosition + 1
				: direction == Direction.UP ? yPosition - 1 : yPosition;
		executeMove(x, y, direction);
	}

	/**
	 * Passes the x and y coordinates of the desired destination square to EnvironmentManager.
	 * Updates profile if move was valid, otherwise excludes direction from current tile.
	 * 
	 * @param x x coordinate of target tile
	 * @param y y coordinate of target tile
	 * @param direction direction we're moving in from current tile to get to target tile
	 */
	public void executeMove(int x, int y, Direction direction)
	{
		EnvironmentManager.executeMove(maze, x, y, direction);
	}

	/**
	 * Updates position and score and outputs status messages.
	 * 
	 * @param x x coordinate of new tile
	 * @param y y coordinate of new tile
	 * @param direction direction we've moved in from previous tile to current tile
	 * @param scoreChange change in score caused by move
	 */
	public void update(int x, int y, Direction direction, int scoreChange)
	{
		if (scoreChange != -1)
		{
			System.out.println("Moved to (" + x + ", " + y + ")");

			score += scoreChange;
			System.out.println("Score: " + score);

			profile.updateStrategy(xPosition, yPosition, direction, x, y, scoreChange);

			xPosition = x;
			yPosition = y;
		}
		else
		{
			profile.excludeDirectionFromTile(xPosition, yPosition, direction);
		}
	}

	public double getQValue(int x, int y, Direction direction)
	{
		return profile.getQValue(x, y, direction);
	}

	public int getXPosition()
	{
		return xPosition;
	}

	public void setXPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public int getYPosition()
	{
		return yPosition;
	}

	public void setYPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}
}
