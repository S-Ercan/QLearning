package maze;

import strategy.StrategyProfile;

public class Agent
{
	private Maze maze;
	private StrategyProfile profile;
	private int xPosition;
	private int yPosition;
	private int score;

	private double learningFactor = 0.1;

	public Agent(Maze maze)
	{
		this.maze = maze;
		profile = new StrategyProfile(maze.getXSize(), maze.getYSize());
		xPosition = 0;
		yPosition = 0;
		score = 0;
	}

	public void run()
	{
		int stepCounter = 0;
		while (stepCounter < 50)
		{
			try
			{
				Thread.sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			chooseMove();
			stepCounter++;
		}
	}

	public void chooseMove()
	{
		Direction direction = profile.chooseDirectionFromTile(xPosition, yPosition);
		int x = direction == Direction.LEFT ? xPosition - 1
				: direction == Direction.RIGHT ? xPosition + 1 : xPosition;
		int y = direction == Direction.DOWN ? yPosition + 1
				: direction == Direction.UP ? yPosition - 1 : yPosition;
		executeMove(x, y, direction);
	}

	public void executeMove(int x, int y, Direction direction)
	{
		int scoreChange = EnvironmentManager.executeMove(maze, x, y);
		if (scoreChange != -1)
		{
			update(x, y, direction, scoreChange);
		}
		else
		{
			excludeDirectionFromTile(x, y, direction);
		}
	}

	public void update(int x, int y, Direction direction, int scoreChange)
	{
		if(scoreChange > 0)
		{
			profile.adjustProbabilitiesForTile(xPosition, yPosition, direction, learningFactor);
		}
		else if(scoreChange < 0)
		{
			profile.adjustProbabilitiesForTile(xPosition, yPosition, direction, -learningFactor);	
		}

		xPosition = x;
		yPosition = y;
		System.out.println("Moved to (" + x + ", " + y + ")");

		score += scoreChange;
		System.out.println("Score: " + score);
	}

	public void excludeDirectionFromTile(int x, int y, Direction direction)
	{
		profile.excludeDirectionFromTile(xPosition, yPosition, direction);
	}
}
