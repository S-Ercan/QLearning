package test.maze.agent;

import main.agent.Agent;
import main.agent.Direction;
import main.maze.InvalidPositionException;
import main.maze.Maze;
import main.maze.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestQAgent {
    private Maze maze;
    private Agent agent;

    private int mazeWidth = 5;
    private int mazeHeight = 5;

    @Before
    public void setUp() {
        maze = mock(Maze.class);
        when(maze.getWidth()).thenReturn(mazeWidth);
        when(maze.getHeight()).thenReturn(mazeHeight);
        agent = new Agent(maze);
    }

    @Test
    public void testCreateAgent_XPositionIsZero() {
        assertEquals(0, agent.getPosition().getX());
    }

    @Test
    public void testCreateAgent_YPositionIsZero() {
        assertEquals(0, agent.getPosition().getY());
    }

    @Test
    public void testCreateAgent_ScoreIsZero() {
        assertEquals(0, agent.getScore());
    }

    @Test
    public void testCreateAgent_HasValidDirection() {
        assertNotNull(agent.getDirection());
    }

    @Test
    public void testUpdate_SetsPosition() throws InvalidPositionException {
        Position position = new Position(1, 1);
        agent.update(position, 1);

        assertEquals(position, agent.getPosition());
    }

    @Test(expected = InvalidPositionException.class)
    public void testUpdateWithIllegalPosition_ThrowsException() throws InvalidPositionException {
        Position position = new Position(mazeWidth + 1, mazeWidth + 1);
        agent.update(position, 0);
    }

    @Test
    public void testUpdate_SetsScore() throws InvalidPositionException {
        int score = agent.getScore();
        int scoreChange = 10;
        agent.update(new Position(1, 1), scoreChange);

        assertEquals(score + scoreChange, agent.getScore());
    }

    @Test
    public void testGetQValueWithoutMoves_ReturnsZero() {
        assertEquals(0, agent.getUtility(agent.getPosition(), Direction.RIGHT), 0.0);
    }
}
