package test.maze.agent;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import main.agent.Agent;
import main.agent.Direction;
import main.maze.Maze;
import main.maze.Position;

public class TestQAgent {
	private Maze maze;
	private Agent agent;

	@Before
	public void setUp() {
		maze = mock(Maze.class);
		when(maze.getXSize()).thenReturn(5);
		when(maze.getYSize()).thenReturn(5);
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
		assertTrue(agent.getDirection() instanceof Direction);
	}

	@Test
	public void testUpdate_SetsPosition() {
		Position position = new Position(1, 1);
		agent.update(position, 1);

		assertTrue(position.equals(agent.getPosition()));
	}

	@Test
	public void testUpdate_SetsScore() {
		int score = agent.getScore();
		int scoreChange = 10;
		agent.update(new Position(1, 1), scoreChange);

		assertEquals(score += scoreChange, agent.getScore());
	}

	@Test
	public void testGetQValueWithoutMoves_ReturnsZero() {
		assertEquals(0, agent.getUtility(agent.getPosition(), Direction.RIGHT), 0.0);
	}
}
