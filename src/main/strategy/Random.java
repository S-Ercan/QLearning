package main.strategy;

import main.agent.Direction;

public class Random extends Strategy
{
	public Random(int x, int y)
	{
		super(x, y);
	}

	@Override
	public Direction getBestDirection()
	{
		return null;
	}

	@Override
	public double getQValueForDirection(Direction direction)
	{
		return 0;
	}

	@Override
	public void update(Direction direction, Strategy nextStrategy, double reward)
	{
		// Random strategy doesn't learn
	}
}
