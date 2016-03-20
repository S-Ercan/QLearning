package main.maze;

import java.util.Random;

import main.maze.tile.EmptyTile;
import main.maze.tile.PunishmentTile;
import main.maze.tile.RewardTile;
import main.maze.tile.Tile;

/**
 * Represents a maze using a 2D array.
 */
public class Maze
{
	private static final int minSize = 2;
	private static final int maxSize = 10;

	private static final double pReward = 0.2;
	private static final double pPunishment = 0.1;

	private Random random;
	private Tile[][] maze;

	private int xSize;
	private int ySize;

	public Maze()
	{
		// Randomly determine dimensions
		random = new Random();
		xSize = random.nextInt(maxSize - 1) + minSize;
		ySize = random.nextInt(maxSize - 1) + minSize;
		maze = new Tile[xSize][ySize];
		System.out.println("Creating " + xSize + " x " + ySize + " maze");

		// Fill maze with rewards and punishments
		populateMaze();
	}

	/**
	 * Loops through all tiles and assigns a reward with probability pReward or
	 * a punishment with probability pPunishment.
	 */
	public void populateMaze()
	{
		for (int i = 0; i < xSize; i++)
		{
			for (int j = 0; j < ySize; j++)
			{
				double value = random.nextDouble();
				if (value <= pReward)
				{
					// Assign reward of 5 or 10
					maze[i][j] = new RewardTile((random.nextInt(2) + 1) * 5);
				}
				else if (value >= 1 - pPunishment)
				{
					// Assign punishment of -5 or -10
					maze[i][j] = new PunishmentTile((random.nextInt(2) + 1) * -5);
				}
				else
				{
					maze[i][j] = new EmptyTile();
				}
			}
		}
		// Ensure initial square is free
		maze[0][0] = new EmptyTile();
	}

	public Tile getTile(int x, int y)
	{
		Tile tile = null;
		if (!(x < 0 || x >= xSize || y < 0 || y >= ySize))
		{
			tile = maze[x][y];
		}
		return tile;
	}

	public Tile getTile(Position position)
	{
		Tile tile = null;
		if (position != null)
		{
			tile = getTile(position.getX(), position.getY());
		}
		return tile;
	}

	public int getTileValue(Position position) throws InvalidPositionException
	{
		Tile tile = getTile(position);
		if (tile == null)
		{
			throw new InvalidPositionException("No tile at position " + position + ".");
		}
		else
		{
			return tile.getValue();
		}
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}
}
