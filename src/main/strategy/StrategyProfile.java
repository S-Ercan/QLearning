package main.strategy;

import main.maze.Direction;

/**
 * A 2D array containing action-utility mappings for all maze tiles.
 */
public class StrategyProfile
{
	private Q[][] profile;

	/**
	 * @param x
	 *            maze width
	 * @param y
	 *            maze height
	 */
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

	/**
	 * Updates the Q-value after having chosen 'direction' from tile (xOld,
	 * yOld), having arrived at tile (x, y) and having received a reward with
	 * value 'reward.
	 * 
	 * @param xOld
	 *            x coordinate of previous tile
	 * @param yOld
	 *            y coordinate of previous tile
	 * @param direction
	 *            direction agent traveled from previous to current tile
	 * @param x
	 *            x coordinate of current tile
	 * @param y
	 *            y coordinate of current tile
	 * @param reward
	 *            reward received by moving from previous to current tile
	 */
	public void updateStrategyForTile(int xOld, int yOld, Direction direction, int x, int y,
			double reward)
	{
		profile[xOld][yOld].update(direction, profile[x][y], reward);
	}

	/**
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @param direction
	 *            direction to return Q-value for
	 * @return Q-value corresponding to choosing 'direction' from tile (x, y).
	 */
	public double getQValueForTile(int x, int y, Direction direction)
	{
		return profile[x][y].getQValueForDirection(direction);
	}

	/**
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @return direction with highest Q-value from tile (x, y)
	 */
	public Direction getBestDirectionFromTile(int x, int y)
	{
		return profile[x][y].getBestDirection();
	}

	/**
	 * Excludes 'direction' as a possible action from tile (x, y). Used after
	 * discovering that moving in direction 'direction' from tile (x, y) is an
	 * invalid move.
	 * 
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 * @param direction
	 *            direction to exclude from tile(x, y)
	 */
	public void excludeDirectionFromTile(int x, int y, Direction direction)
	{
		profile[x][y].excludeDirection(direction);
	}
}
