import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
		List<Direction> candidateDirections = getCandidateDirections();
		Direction maxKey = null;
		if (candidateDirections.size() > 1)
		{
			maxKey = candidateDirections.get(random.nextInt(candidateDirections.size()));
		}
		else
		{
			maxKey = candidateDirections.get(0);
		}
		return maxKey;
	}

	public List<Direction> getCandidateDirections()
	{
		List<Direction> candidateDirections = new ArrayList<Direction>();
		double maxValue = 0;
		double currentValue;
		for (Entry<Direction, Double> entry : strategy.entrySet())
		{
			currentValue = entry.getValue();
			if (currentValue >= maxValue)
			{
				if (currentValue > maxValue)
				{
					candidateDirections.clear();
				}
				candidateDirections.add(entry.getKey());
				maxValue = currentValue;
			}
		}
		return candidateDirections;
	}

	public void excludeDirection(Direction direction)
	{
		double difference = strategy.get(direction);
		double correction = difference / strategy.size();

		strategy.remove(direction);
		for (Entry<Direction, Double> entry : strategy.entrySet())
		{
			strategy.put(entry.getKey(), entry.getValue() + correction);
		}
	}
}
