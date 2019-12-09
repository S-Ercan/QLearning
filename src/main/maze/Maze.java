package main.maze;

import main.maze.tile.EmptyTile;
import main.maze.tile.PunishmentTile;
import main.maze.tile.RewardTile;
import main.maze.tile.Tile;

import java.util.Random;

/**
 * Represents a maze using a 2D array.
 */
public class Maze {
    private double pReward = 0.2;
    private double pPunishment = 0.1;

    private Tile[][] maze;
    private int width;
    private int height;

    Maze(int xSize, int ySize, double pReward, double pPunishment) {
        setWidth(xSize);
        setHeight(ySize);
        setPReward(pReward);
        setPPunishment(pPunishment);

        maze = new Tile[xSize][ySize];
        populateMaze();
    }

    /**
     * Loops through all tiles and assigns a reward with probability pReward or
     * a punishment with probability pPunishment.
     */
    private void populateMaze() {
        Random random = new Random();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double value = random.nextDouble();
                if (value <= pReward) {
                    // Assign reward of 5 or 10
                    maze[i][j] = new RewardTile((random.nextInt(2) + 1) * 5);
                } else if (value >= 1 - pPunishment) {
                    // Assign punishment of -5 or -10
                    maze[i][j] = new PunishmentTile((random.nextInt(2) + 1) * -5);
                } else {
                    maze[i][j] = new EmptyTile();
                }
            }
        }
        // Ensure initial square is free
        maze[0][0] = new EmptyTile();
    }

    public Tile getTile(int x, int y) {
        Tile tile = null;
        if (!(x < 0 || x >= width || y < 0 || y >= height)) {
            tile = maze[x][y];
        }
        return tile;
    }

    private Tile getTile(Position position) {
        Tile tile = null;
        if (position != null && position.isValid(width, height)) {
            tile = getTile(position.getX(), position.getY());
        }
        return tile;
    }

    int getTileValue(Position position) throws InvalidPositionException {
        Tile tile = getTile(position);
        if (tile == null) {
            throw new InvalidPositionException("No tile at position " + position + ".");
        } else {
            return tile.getValue();
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getPReward() {
        return pReward;
    }

    public void setPReward(double pReward) {
        this.pReward = pReward;
    }

    public double getPPunishment() {
        return pPunishment;
    }

    public void setPPunishment(double pPunishment) {
        this.pPunishment = pPunishment;
    }
}
