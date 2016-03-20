package main.maze.tile;

public class PunishmentTile extends Tile
{
	public PunishmentTile(int value)
	{
		setValue(value);
	}

	public void setValue(int value)
	{
		if (value >= 0)
		{
			throw new IllegalArgumentException("Value for PunishmentTile should be negative.");
		}
		this.value = value;
	}
}
