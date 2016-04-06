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
}
