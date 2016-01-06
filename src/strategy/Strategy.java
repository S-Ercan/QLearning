package strategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import maze.Direction;

import java.util.Random;

public class Strategy
{
	private Map<Direction, ProbabilityInterval> strategy;
	private Random random;

	public Strategy()
	{
		random = new Random();
		strategy = new HashMap<Direction, ProbabilityInterval>();
		strategy.put(Direction.UP, new ProbabilityInterval(0, 0.25));
		strategy.put(Direction.DOWN, new ProbabilityInterval(0.25, 0.5));
		strategy.put(Direction.LEFT, new ProbabilityInterval(0.5, 0.75));
		strategy.put(Direction.RIGHT, new ProbabilityInterval(0.75, 1));
	}

	public Direction chooseDirection()
	{
		Direction direction = null;
		double value = random.nextDouble();
		ProbabilityInterval interval;
		for (Entry<Direction, ProbabilityInterval> entry : strategy.entrySet())
		{
			interval = entry.getValue();
			if (value >= interval.getLowerBound() && value <= interval.getUpperBound())
			{
				direction = entry.getKey();
				break;
			}
		}

		return direction;
	}

	public void excludeDirection(Direction direction)
	{
		ProbabilityInterval interval = strategy.get(direction);
		double difference = interval.getUpperBound() - interval.getLowerBound();
		double correction = difference / strategy.size();

		strategy.remove(direction);
		double lowerBound = 0;
		double upperBound;
		for (Entry<Direction, ProbabilityInterval> entry : strategy.entrySet())
		{
			ProbabilityInterval oldInterval = entry.getValue();
			double oldIntervalSize = oldInterval.getUpperBound() - oldInterval.getLowerBound();

			strategy.get(entry.getKey()).setLowerBound(lowerBound);
			upperBound = lowerBound + oldIntervalSize + correction;
			strategy.get(entry.getKey()).setUpperBound(upperBound);

			lowerBound = upperBound;
		}
	}
}
