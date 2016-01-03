import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Strategy
{
	private Map<Direction, Double> strategy;

	public Strategy()
	{
		strategy = new HashMap<Direction, Double>();
		strategy.put(Direction.UP, 0.25);
		strategy.put(Direction.DOWN, 0.25);
		strategy.put(Direction.LEFT, 0.25);
		strategy.put(Direction.RIGHT, 0.25);
	}

	public Direction getBestDirection()
	{
		// TODO: randomize in case all non-excluded directions have equal probability
		Direction maxKey = null;
		double maxValue = 0;
		double currentValue;
		for(Entry<Direction, Double> entry : strategy.entrySet())
		{
			currentValue = entry.getValue();
			if(currentValue > maxValue)
			{
				maxKey = entry.getKey();
				maxValue = currentValue;
			}
		}
		return maxKey;
	}

	public void excludeDirection(Direction direction)
	{
		double difference = strategy.get(direction);
		int numExcludedDirections = getNumberOfExcludedDirections();
		double correction = difference / numExcludedDirections;

		Direction key;
		for(Entry<Direction, Double> entry : strategy.entrySet())
		{
			key = entry.getKey();
			if(key == direction)
			{
				strategy.put(key, 0.0);
			}
			else if(entry.getValue() != 0)
			{
				strategy.put(key, entry.getValue() + correction);
			}
		}
	}

	public int getNumberOfExcludedDirections()
	{
		int number = 0;
		for(double value : strategy.values())
		{
			if(value == 0)
			{
				number++;
			}
		}
		return number;
	}

	public double getProbUp()
	{
		return strategy.get(Direction.UP);
	}

	public void setProbUp(double probUp)
	{
		strategy.put(Direction.UP, probUp);
	}

	public double getProbDown()
	{
		return strategy.get(Direction.DOWN);
	}

	public void setProbDown(double probDown)
	{
		strategy.put(Direction.DOWN, probDown);
	}

	public double getProbLeft()
	{
		return strategy.get(Direction.LEFT);
	}

	public void setProbLeft(double probLeft)
	{
		strategy.put(Direction.LEFT, probLeft);
	}

	public double getProbRight()
	{
		return strategy.get(Direction.RIGHT);
	}

	public void setProbRight(double probRight)
	{
		strategy.put(Direction.RIGHT, probRight);
	}
}
