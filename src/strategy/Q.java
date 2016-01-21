package strategy;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import maze.Direction;

import java.util.Random;

public class Q
{
	private final double alpha = 1;
	private final double gamma = 0.5;

	private int x;
	private int y;

	private Map<Direction, Double> strategy;
	private Random random;

	private DecimalFormat decimalFormat;

	public Q(int x, int y)
	{
		this.x = x;
		this.y = y;

		random = new Random();

		strategy = new HashMap<Direction, Double>();
		strategy.put(Direction.UP, 0.0);
		strategy.put(Direction.DOWN, 0.0);
		strategy.put(Direction.LEFT, 0.0);
		strategy.put(Direction.RIGHT, 0.0);

		decimalFormat = new DecimalFormat("#0.00");
	}

	/**
	 * Returns the direction with the highest Q-value.
	 * If multiple dimensions share this maximum, one is selected among these randomly.
	 * 
	 * @return direction with highest value
	 */
	public Direction getBestDirection()
	{
		List<Direction> candidateDirections = new ArrayList<Direction>();
		double maxQ = 0;
		double value;
		for (Entry<Direction, Double> entry : strategy.entrySet())
		{
			value = entry.getValue();
			if (value >= maxQ)
			{
				maxQ = value;
				if (value > maxQ)
				{
					candidateDirections.clear();
				}
				candidateDirections.add(entry.getKey());
			}
		}

		return candidateDirections.get(random.nextInt(candidateDirections.size()));
	}

	/**
	 * Updates Q((x, y), direction) according to the Q-values in the next state
	 * and the reward received.
	 * 
	 * @param direction direction to update Q-value for
	 * @param nextStrategy Q-values belonging to state reached by last move
	 * @param reward reward received by last move (positive or negative)
	 */
	public void update(Direction direction, Q nextStrategy, double reward)
	{
		double oldValue = strategy.get(direction);
		double newValue = oldValue + alpha * (reward + gamma * (nextStrategy.getMaxQValue()) - oldValue);
		strategy.put(direction, newValue);
		System.out.println("Updating Q((" + x + ", " + y + "), " + direction + ") from "
				+ decimalFormat.format(oldValue) + " to " + decimalFormat.format(newValue) + ".");
	}

	/**
	 * @return maximum value occurring in strategy
	 */
	public double getMaxQValue()
	{
		double maxQ = 0;
		double value;
		for (Entry<Direction, Double> entry : strategy.entrySet())
		{
			value = entry.getValue();
			if (value >= maxQ)
			{
				maxQ = value;
			}
		}
		return maxQ;
	}

	/**
	 * Removes direction from strategy - used when moving in 'direction' from tile (x, y)
	 * turns out to be an invalid move. 
	 * 
	 * @param direction direction to remove
	 */
	public void excludeDirection(Direction direction)
	{
		strategy.remove(direction);
	}
}
