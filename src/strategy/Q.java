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

	public Direction chooseDirection()
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

	public void update(Direction direction, Q nextStrategy, double reward)
	{
		double oldValue = strategy.get(direction);
		double newValue = oldValue + alpha * (reward + gamma * (nextStrategy.getMaxQValue()) - oldValue);
		strategy.put(direction, newValue);
		System.out.println("Updating Q((" + x + ", " + y + "), " + direction + ") from "
				+ decimalFormat.format(oldValue) + " to " + decimalFormat.format(newValue) + ".");
	}

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

	public void excludeDirection(Direction direction)
	{
		strategy.remove(direction);
	}
}
