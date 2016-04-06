package main.strategy;

import main.agent.AgentType;
import main.agent.Direction;
import main.maze.Maze;
import main.maze.Position;

/**
 * A 2D array containing action-utility mappings for all maze tiles.
 */
public class StrategyProfile
{
	private int width;
	private int height;
	private Strategy[][] profile;
	
	/**
	 * @param x
	 *            maze width
	 * @param y
	 *            maze height
	 */
	public StrategyProfile(Maze maze, AgentType agentType)
	{
		width = maze.getXSize();
		height = maze.getYSize();
		profile = new Strategy[width][height];
		for (int i = 0; i < width; i++)
		{
			for (int j = 0; j < height; j++)
			{
				profile[i][j] = Strategy.createStrategy(i, j, agentType);
			}
		}
	}

	/**
	 * Updates the Q-value after having chosen 'direction' from tile (xOld,
	 * yOld), having arrived at tile (x, y) and having received a reward with
	 * value 'reward.
	 * 
	 * @param oldPosition
	 *            previous position of agent
	 * @param newPosition
	 *            current position of agent
	 * @param direction
	 *            direction agent traveled from previous to current tile
	 * @param reward
	 *            reward received by moving from previous to current tile
	 */
	public void updateStrategyForTile(Position oldPosition, Position newPosition,
			Direction direction, double reward)
	{
		Strategy currentStrategy = getStrategy(oldPosition);
		Strategy nextStrategy = getStrategy(newPosition);
		// TODO: put update() in Strategy instead of in Q
		if(currentStrategy instanceof Q && nextStrategy instanceof Q)
		{
			Q currentQ = (Q) currentStrategy;
			Q nextQ = (Q) nextStrategy;
			currentQ.update(direction, nextQ, reward);
		}
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
		return getStrategy(position).getQValueForDirection(direction);
	}

	/**
	 * @param position
	 *            position of tile for which to return the best direction
	 * @return direction with highest Q-value from tile (x, y)
	 */
	public Direction getBestDirectionFromTile(Position position)
	{
		return getStrategy(position).getBestDirection();
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
		getStrategy(position).excludeDirection(direction);
	}

	/**
	 * @param position
	 *            tile to get strategy for
	 * @return strategy for tile at 'position'
	 */
	public Strategy getStrategy(Position position)
	{
		Strategy q = null;
		int x = position.getX();
		int y = position.getY();
		if (x >= 0 && x < width && y >= 0 && y < height)
		{
			q = profile[x][y];
		}
		return q;
	}
}
