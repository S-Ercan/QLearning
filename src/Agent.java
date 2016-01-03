
public class Agent
{
	private StrategyProfile profile;
	private int xPosition;
	private int yPosition;
	private int score;

	public Agent()
	{
		profile = new StrategyProfile(5, 5);
		xPosition = 0;
		yPosition = 0;
		score = 0;
		run();
	}

	public void run()
	{
		int stepCounter = 0;
		while(stepCounter < 50)
		{
			chooseMove();
			stepCounter++;
		}
	}

	public void chooseMove()
	{
		Direction direction = profile.getBestDirectionFromTile(xPosition, yPosition);
		int x = 
				direction == Direction.LEFT ? xPosition - 1 : 
					direction == Direction.RIGHT ? xPosition + 1 : xPosition;
		int y = 
				direction == Direction.DOWN ? yPosition + 1 : 
					direction == Direction.UP ? yPosition - 1 : yPosition;
		executeMove(x, y, direction);
	}

	public void executeMove(int x, int y, Direction direction)
	{
		int scoreChange = EnvironmentManager.executeMove(x, y);
		if(scoreChange != -1)
		{
			updateScore(scoreChange);
			updatePosition(x, y);
		}
		else
		{
			excludeDirectionFromTile(x, y, direction);
		}
	}

	public void updatePosition(int x, int y)
	{
		xPosition = x;
		yPosition = y;
		System.out.println("Moved to (" + x + ", " + y + ")");
	}

	public void updateScore(int scoreChange)
	{
		score += scoreChange;
		System.out.println("Score: " + score);
	}

	public void excludeDirectionFromTile(int x, int y, Direction direction)
	{
		profile.excludeDirectionFromTile(xPosition, yPosition, direction);
	}
}
