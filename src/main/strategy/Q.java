package main.strategy;

import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import main.agent.Direction;

import java.util.Random;

/**
 * Implements Q-learning by storing Q-values for all directions from a given
 * tile and enabling their update according to the Q-learning technique.
 */
public class Q extends Strategy
{
	private static final double alpha = 1;
	private static final double gamma = 0.5;

	private Random random;
	private double pRandExploration;

	private DecimalFormat decimalFormat;

	/**
	 * Creates an action-utility mapping for tile (x, y).
	 * 
	 * @param x
	 *            x coordinate of tile
	 * @param y
	 *            y coordinate of tile
	 */
	public Q(int x, int y)
	{
		super(x, y);

		random = new Random();
		pRandExploration = 0.5;

		decimalFormat = new DecimalFormat("#0.00");
	}

	/**
	 * Returns the direction with the highest Q-value. If multiple dimensions
	 * share this maximum, one is selected among these randomly.
	 * 
	 * @return direction with highest value
	 */
	public Direction getBestDirection()
	{
		/*
		 * With a small and decreasing probability, choose random direction.
		 * This is to encourage exploration and avoid getting stuck in locally
		 * optimal but globally sub-optimal behavior.
		 */
		if (random.nextDouble() < getPRandExploration())
		{
			pRandExploration -= 0.025;
			System.out.println("Updated pRandExploration for (" + getX() + ", " + getY() + ") to "
					+ decimalFormat.format(pRandExploration));
			List<Direction> directions = new ArrayList<Direction>(getStrategy().keySet());
			return directions.get(random.nextInt(directions.size()));
		}

		List<Direction> candidateDirections = new ArrayList<Direction>();
		double maxQ = Double.NEGATIVE_INFINITY;
		double value;
		for (Entry<Direction, Double> entry : getStrategy().entrySet())
		{
			value = entry.getValue();
			if (value >= maxQ)
			{
				if (value > maxQ)
				{
					candidateDirections.clear();
				}
				candidateDirections.add(entry.getKey());
				maxQ = value;
			}
		}

		return candidateDirections.get(random.nextInt(candidateDirections.size()));
	}

	/**
	 * Updates Q((x, y), direction) according to the Q-values in the next state
	 * and the reward received.
	 * 
	 * @param direction
	 *            direction to update Q-value for
	 * @param nextStrategy
	 *            Q-values belonging to state reached by last move
	 * @param reward
	 *            reward received by last move (positive or negative)
	 */
	public void update(Direction direction, Q nextStrategy, double reward)
	{
		double oldValue = getStrategy().get(direction);
		double newValue = oldValue
				+ alpha * (reward + gamma * (nextStrategy.getMaxQValue()) - oldValue);
		getStrategy().put(direction, newValue);
		System.out.println("Updating Q((" + getX() + ", " + getY() + "), " + direction + ") from "
				+ decimalFormat.format(oldValue) + " to " + decimalFormat.format(newValue) + ".");
	}

	/**
	 * @return maximum utility value that can be achieved from this tile
	 */
	public double getMaxQValue()
	{
		double maxQ = 0;
		double value;
		for (Entry<Direction, Double> entry : getStrategy().entrySet())
		{
			value = entry.getValue();
			if (value >= maxQ)
			{
				maxQ = value;
			}
		}
		return maxQ;
	}

	public double getQValueForDirection(Direction direction)
	{
		return getStrategy().getOrDefault(direction, 0.0);
	}

	public double getPRandExploration()
	{
		return pRandExploration;
	}

	public void setPRandExploration(double pRandExploration)
	{
		this.pRandExploration = pRandExploration;
	}
}
