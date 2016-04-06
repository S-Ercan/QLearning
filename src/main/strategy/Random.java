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
}
