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
