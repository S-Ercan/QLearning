package main.strategy;

import java.util.HashMap;
import java.util.Map;

import main.agent.AgentType;
import main.agent.Direction;
import main.maze.Position;

public abstract class Strategy
{
	private int x;
	private int y;

	private Map<Direction, Double> strategy;

	public static Strategy createStrategy(int x, int y, AgentType agentType)
	{
		if (agentType == AgentType.Q)
		{
			return new Q(x, y);
		}
		else if (agentType == AgentType.RANDOM)
		{
			return new Q(x, y);
		}
		else
		{
			throw new IllegalArgumentException("Unsupported agent type.");
		}
	}

	/**
	 * Creates an action-utility mapping for tile (x, y).
	 * 
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 */
	public Strategy(int x, int y)
	{
		this.x = x;
		this.y = y;

		strategy = new HashMap<Direction, Double>();
		strategy.put(Direction.UP, 0.0);
		strategy.put(Direction.DOWN, 0.0);
		strategy.put(Direction.LEFT, 0.0);
		strategy.put(Direction.RIGHT, 0.0);
	}

	/**
	 * Returns direction considered best. Strategies subclassing this class
	 * should define how this direction should be chosen.
	 * 
	 * @return best direction
	 */
	public abstract Direction getBestDirection();

	public abstract double getQValueForDirection(Direction direction);

	/**
	 * Removes direction from strategy - used when moving in 'direction' from
	 * tile (x, y) turns out to be an invalid move.
	 * 
	 * @param direction
	 *            direction to remove
	 */
	public void excludeDirection(Direction direction)
	{
		strategy.remove(direction);
	}

	public Map<Direction, Double> getStrategy()
	{
		return strategy;
	}

	public void setStrategy(Map<Direction, Double> strategy)
	{
		this.strategy = strategy;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
}
