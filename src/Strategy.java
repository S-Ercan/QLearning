import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

public class Strategy
{
	private Map<Direction, Double> strategy;
	private Random random;

	public Strategy()
	{
		random = new Random();
		strategy = new HashMap<Direction, Double>();
		strategy.put(Direction.UP, 0.25);
		strategy.put(Direction.DOWN, 0.25);
		strategy.put(Direction.LEFT, 0.25);
		strategy.put(Direction.RIGHT, 0.25);
	}

	public Direction getBestDirection()
	{
		// TODO: randomize in case all non-excluded directions have equal probability
		List<Direction> list = new ArrayList<Direction>();
		double maxValue = 0;
		double currentValue;
		for(Entry<Direction, Double> entry : strategy.entrySet())
		{
			currentValue = entry.getValue();
			if(currentValue > maxValue)
			{
				list.clear();
				list.add(entry.getKey());
				maxValue = currentValue;
			}
		}

		Direction maxKey = null;
		if(list.size() > 1)
		{
			maxKey = list.get(random.nextInt(list.size()));	
		}
		else
		{
			maxKey = list.get(0);
		}
		return maxKey;
	}

	public void excludeDirection(Direction direction)
	{
		double difference = strategy.get(direction);
		double correction = difference / strategy.size();

		strategy.remove(direction);		
		for(Entry<Direction, Double> entry : strategy.entrySet())
		{
			strategy.put(entry.getKey(), entry.getValue() + correction);
		}
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
