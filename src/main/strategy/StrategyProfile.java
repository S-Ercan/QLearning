package main.strategy;

import main.maze.Direction;
import main.maze.Position;

/**
 * A 2D array containing action-utility mappings for all maze tiles.
 */
public class StrategyProfile
{
	private int width;
	private int height;
	private Q[][] profile;

	/**
	 * @param x
	 *            maze width
	 * @param y
	 *            maze height
	 */
	public StrategyProfile(int x, int y)
	{
		width = x;
		height = y;
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
	 * @param oldPosition
	 *            position of previous tile
	 * @param newPosition
	 *            position of current tile
	 * @param direction
	 *            direction agent traveled from previous to current tile
	 * @param reward
	 *            reward received by moving from previous to current tile
	 */
	public void updateStrategyForTile(Position oldPosition, Position newPosition,
			Direction direction, double reward)
	{
		getQ(oldPosition).update(direction, getQ(newPosition), reward);
	}

	/**
	 * @param position
	 *            position of tile to update Q-value for
	 * @param direction
	 *            direction to return Q-value for
	 * @return Q-value corresponding to choosing 'direction' from tile (x, y).
	 */
	public double getQValueForTile(Position position, Direction direction)
	{
		return getQ(position).getQValueForDirection(direction);
	}

	/**
	 * @param position
	 *            position of tile for which to return the best direction
	 * @return direction with highest Q-value from tile (x, y)
	 */
	public Direction getBestDirectionFromTile(Position position)
	{
		return getQ(position).getBestDirection();
	}

	/**
	 * Excludes 'direction' as a possible action from tile at 'position'. Used
	 * after discovering that moving in direction 'direction' from tile at
	 * 'position' is an invalid move.
	 * 
	 * @param position
	 *            position of tile to exclude direction for
	 * @param direction
	 *            direction to exclude from tile(x, y)
	 */
	public void excludeDirectionFromTile(Position position, Direction direction)
	{
		getQ(position).excludeDirection(direction);
	}

	/**
	 * @param position
	 *            tile to get strategy for
	 * @return strategy for tile at 'position'
	 */
	public Q getQ(Position position)
	{
		Q q = null;
		int x = position.getX();
		int y = position.getY();
		if (x >= 0 && x < width && y >= 0 && y < height)
		{
			q = profile[x][y];
		}
		return q;
	}
}
