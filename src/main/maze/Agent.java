package main.maze;

import main.strategy.StrategyProfile;

/**
 * Represents an agent moving through a maze. Stores position and score
 * information. Learns which actions to choose by updating its StrategyProfile,
 * and then uses this to choose and execute moves.
 */
public class Agent
{
	private Maze maze;
	private StrategyProfile profile;

	// Current position in maze
	private Position position;
	// Direction agent is facing currently
	private Direction direction;
	private int score;

	/**
	 * Creates a new agent for the given maze.
	 * 
	 * @param maze
	 *            the maze the agent is active in
	 */
	public Agent(Maze maze)
	{
		this.maze = maze;
		profile = new StrategyProfile(maze.getXSize(), maze.getYSize());
		position = new Position(0, 0);
		score = 0;
		direction = null;
	}

	/**
	 * Uses 'profile' to get the best direction to head from the current tile.
	 * Calculates destination coordinates accordingly and executes the move.
	 */
	public void move()
	{
		Position currentPosition = getPosition();
		Direction direction = profile.getBestDirectionFromTile(currentPosition);
		setDirection(direction);

		int xCurrent = position.getX();
		int yCurrent = position.getY();
		int xNew = direction == Direction.LEFT ? xCurrent - 1
				: direction == Direction.RIGHT ? xCurrent + 1 : xCurrent;
		int yNew = direction == Direction.DOWN ? yCurrent + 1
				: direction == Direction.UP ? yCurrent - 1 : yCurrent;

		try
		{
			EnvironmentManager.executeMove(maze, new Position(xNew, yNew));
		}
		catch (InvalidTileCoordinatesException e)
		{
			profile.excludeDirectionFromTile(currentPosition, direction);
		}
	}

	/**
	 * Updates position, score and profile and outputs status messages.
	 * 
	 * @param newPosition
	 *            current position in maze
	 * @param scoreChange
	 *            change in score caused by move
	 */
	public void update(Position newPosition, int scoreChange)
	{
		profile.updateStrategyForTile(getPosition(), newPosition, getDirection(), scoreChange);
		setScore(score += scoreChange);
		setPosition(newPosition);
	}

	/**
	 * @param position
	 *            position of tile to get Q-value for
	 * @param direction
	 *            direction to get Q-value for
	 * @return Q-value corresponding to choosing 'direction' from tile at
	 *         'position'
	 */
	public double getQValue(Position position, Direction direction)
	{
		return profile.getQValueForTile(position, direction);
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
		System.out.println("Moved to " + position);
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public int getScore()
	{
		return score;
	}

	public void setScore(int score)
	{
		this.score = score;
		System.out.println("Score: " + score);
	}
}
