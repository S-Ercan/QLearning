package main.maze.tile;

public class RewardTile extends Tile
{
	public RewardTile(int value)
	{
		if (value <= 0)
		{
			throw new IllegalArgumentException("Value for RewardTile should be positive.");
		}
		this.value = value;
	}
}
