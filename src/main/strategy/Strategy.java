package main.strategy;

import main.agent.Direction;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public abstract class Strategy {
    private int x;
    private int y;

    private Map<Direction, Double> strategy;

    /**
     * Creates an action-utility mapping for tile (x, y).
     *
     * @param x x coordinate of tile
     * @param y y coordinate of tile
     */
    Strategy(int x, int y) {
        this.x = x;
        this.y = y;

        strategy = new HashMap<>();
        strategy.put(Direction.UP, 0.0);
        strategy.put(Direction.DOWN, 0.0);
        strategy.put(Direction.LEFT, 0.0);
        strategy.put(Direction.RIGHT, 0.0);
    }

    /**
     * Returns direction considered best. Strategies subclassing this class
     * should define how this direction should be chosen.
     *
     * @return best direction
     */
    public abstract Direction getBestDirection();

    public abstract void update(Direction direction, Strategy nextStrategy, double reward);

    public double getUtilityForDirection(Direction direction) {
        return getStrategy().getOrDefault(direction, 0.0);
    }

    /**
     * @return maximum utility value that can be achieved from this tile
     */
    public double getMaxUtility() {
        double maxQ = 0;
        double value;
        for (Entry<Direction, Double> entry : getStrategy().entrySet()) {
            value = entry.getValue();
            if (value >= maxQ) {
                maxQ = value;
            }
        }
        return maxQ;
    }

    /**
     * Removes direction from strategy - used when moving in 'direction' from
     * tile (x, y) turns out to be an invalid move.
     *
     * @param direction direction to remove
     */
    public void excludeDirection(Direction direction) {
        strategy.remove(direction);
    }

    public Map<Direction, Double> getStrategy() {
        return strategy;
    }

    public void setStrategy(Map<Direction, Double> strategy) {
        this.strategy = strategy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
