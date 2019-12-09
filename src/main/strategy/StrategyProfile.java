package main.strategy;

import main.agent.Direction;
import main.maze.InvalidPositionException;
import main.maze.Maze;
import main.maze.Position;

/**
 * A 2D array containing action-utility mappings for all maze tiles.
 */
public class StrategyProfile {
    private int width;
    private int height;
    private Strategy[][] profile;

    /**
     * @param x maze width
     * @param y maze height
     */
    public StrategyProfile(Maze maze) {
        width = maze.getWidth();
        height = maze.getHeight();
        profile = new Strategy[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                profile[i][j] = new Q(i, j);
            }
        }
    }

    /**
     * Updates the Q-value after having chosen 'direction' from tile (xOld,
     * yOld), having arrived at tile (x, y) and having received a reward with
     * value 'reward.
     *
     * @param oldPosition previous position of agent
     * @param newPosition current position of agent
     * @param direction   direction agent traveled from previous to current tile
     * @param reward      reward received by moving from previous to current tile
     * @throws InvalidPositionException if oldPosition or newPosition are not valid positions within
     *                                  the maze associated with this profile
     */
    public void updateStrategyForTile(Position oldPosition, Position newPosition,
                                      Direction direction, double reward) throws InvalidPositionException {
        Strategy currentStrategy = getStrategy(oldPosition);
        if (currentStrategy == null) {
            throw new InvalidPositionException(
                    "'oldPosition' does not represent a valid position.");
        }
        Strategy nextStrategy = getStrategy(newPosition);
        if (nextStrategy == null) {
            throw new InvalidPositionException(
                    "'newPosition' does not represent a valid position.");
        }
        currentStrategy.update(direction, nextStrategy, reward);
    }

    /**
     * @param position  position of tile to return utility of
     * @param direction direction to return utility of
     * @return utility corresponding to choosing 'direction' from tile (x, y).
     */
    public double getUtilityForTile(Position position, Direction direction) {
        return getStrategy(position).getUtilityForDirection(direction);
    }

    /**
     * @param position position of tile for which to return the best direction
     * @return direction with highest utility tile (x, y)
     */
    public Direction getBestDirectionFromTile(Position position) {
        return getStrategy(position).getBestDirection();
    }

    /**
     * Excludes 'direction' as a possible action from tile at 'position'. Used
     * after discovering that moving in direction 'direction' from tile at
     * 'position' is an invalid move.
     *
     * @param position  position of tile to exclude direction for
     * @param direction direction to exclude from tile(x, y)
     */
    public void excludeDirectionFromTile(Position position, Direction direction) {
        getStrategy(position).excludeDirection(direction);
    }

    /**
     * @param position tile to get strategy for
     * @return strategy for tile at 'position'
     */
    private Strategy getStrategy(Position position) {
        Strategy q = null;
        int x = position.getX();
        int y = position.getY();
        if (x >= 0 && x < width && y >= 0 && y < height) {
            q = profile[x][y];
        }
        return q;
    }
}
