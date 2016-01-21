package strategy;

import maze.Direction;

public class StrategyProfile
{
	private Q[][] profile;

	public StrategyProfile(int x, int y)
	{
		profile = new Q[x][y];
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < y; j++)
			{
				profile[i][j] = new Q(i, j);
			}
		}
	}

	public void updateStrategy(int xOld, int yOld, Direction direction, int x, int y, double reward)
	{
		profile[xOld][yOld].update(direction, profile[x][y], reward);
	}

	public Direction chooseDirectionFromTile(int x, int y)
	{
		return profile[x][y].chooseDirection();
	}

	public void excludeDirectionFromTile(int x, int y, Direction direction)
	{
		profile[x][y].excludeDirection(direction);
	}
}
