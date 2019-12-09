package main.maze;

import main.agent.Agent;
import main.agent.Direction;
import main.gui.ConfigurationDialog;
import main.gui.GameWindow;

import javax.swing.*;

/**
 * Creates the game window which displays the maze graphically. Instantiates and
 * runs the maze and the agents active in it. Passes moves from agents to the
 * maze, and returns move results to agents.
 */
public class EnvironmentManager {
    private static GameWindow gameWindow;
    private static Maze maze;
    private static Agent agent;
    private static int timeFactor;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ConfigurationDialog();
            }
        });
        timeFactor = 1;
    }

    public static void start(int xSize, int ySize, double pReward, double pPunishment) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() {
                maze = new Maze(xSize, ySize, pReward, pPunishment);
                gameWindow = new GameWindow(maze);
                agent = new Agent(maze);
                EnvironmentManager.run();
                return null;
            }
        }.execute();
    }

    private static void run() {
        gameWindow.showMoveAnimation(agent.getPosition());
        while (true) {
            if (gameWindow.simulationIsRunning()) {
                agent.move();
            }
            try {
                Thread.sleep((long) (1000 / Math.pow(2, timeFactor)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Executes move given by agent and passes move details to GameWindow to
     * display the move graphically. Updates agent with move result (the value
     * associated with the tile moved to).
     *
     * @param position destination position in maze
     * @throws InvalidPositionException if position is not a valid position within maze
     */
    public static void executeMove(Position oldPosition, Direction direction, Position position)
            throws InvalidPositionException {
        int result;
        try {
            result = maze.getTileValue(position);
        } catch (InvalidPositionException e) {
            throw e;
        }

        agent.update(position, result);
        gameWindow.processMove(oldPosition, agent.getPosition(), direction, agent.getScore(),
                agent.getUtility(oldPosition, direction));
    }

    public static void setTimeFactor(int timeFactor) {
        EnvironmentManager.timeFactor = timeFactor;
    }
}
