package main.agent;

import main.maze.EnvironmentManager;
import main.maze.InvalidPositionException;
import main.maze.Maze;
import main.maze.Position;
import main.strategy.StrategyProfile;

/**
 * Represents an agent moving through a maze. Stores position and score
 * information. Learns which actions to choose by updating its StrategyProfile,
 * and then uses this to choose and execute moves.
 */
public class Agent {
    private StrategyProfile profile;

    // Current position in maze
    private Position position;
    // Direction agent is facing currently
    private Direction direction;
    private int score;

    /**
     * Creates a new agent for the given maze.
     *
     * @param maze the maze the agent is active in
     */
    public Agent(Maze maze) {
        setProfile(new StrategyProfile(maze));
        setPosition(new Position(0, 0));
        setDirection(Direction.RIGHT);
        setScore(0);
    }

    /**
     * Uses 'profile' to get the best direction to head from the current tile.
     * Calculates destination coordinates accordingly and executes the move.
     */
    public void move() {
        // Determine next direction to take
        Position currentPosition = getPosition();
        Direction direction = profile.getBestDirectionFromTile(currentPosition);
        setDirection(direction);
        // Calculate next position
        Position nextPosition = getNextPosition();
        // Execute move and exclude it as a possibility if it's invalid
        try {
            EnvironmentManager.executeMove(currentPosition, direction, nextPosition);
        } catch (InvalidPositionException e) {
            profile.excludeDirectionFromTile(currentPosition, direction);
        }
    }

    private Position getNextPosition() {
        int xCurrent = position.getX();
        int yCurrent = position.getY();
        int xNew = direction == Direction.LEFT ? xCurrent - 1
                : direction == Direction.RIGHT ? xCurrent + 1 : xCurrent;
        int yNew = direction == Direction.DOWN ? yCurrent + 1
                : direction == Direction.UP ? yCurrent - 1 : yCurrent;
        return new Position(xNew, yNew);
    }

    /**
     * Updates strategy profile, score and position.
     *
     * @param newPosition current position in maze
     * @param scoreChange change in score caused by move
     * @throws InvalidPositionException if newPosition is an invalid tile within the strategy profile
     */
    public void update(Position newPosition, int scoreChange) throws InvalidPositionException {
        getProfile().updateStrategyForTile(getPosition(), newPosition, getDirection(), scoreChange);
        setScore(score += scoreChange);
        setPosition(newPosition);
    }

    /**
     * @param position  position of tile to get Q-value for
     * @param direction direction to get Q-value for
     * @return Q-value corresponding to choosing 'direction' from tile at
     * 'position'
     */
    public double getUtility(Position position, Direction direction) {
        return profile.getUtilityForTile(position, direction);
    }

    public StrategyProfile getProfile() {
        return profile;
    }

    public void setProfile(StrategyProfile profile) {
        this.profile = profile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
