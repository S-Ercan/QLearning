package test.maze;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import main.maze.Agent;
import main.maze.Maze;

public class TestAgent
{
	private Maze maze;
	private Agent agent;

	@Before
	public void setUp()
	{
		maze = mock(Maze.class);
		agent = new Agent(maze);
	}

	@Test
	public void testCreateAgent_XPositionIsZero()
	{
		assertEquals(0, agent.getXPosition());
	}

	@Test
	public void testCreateAgent_YPositionIsZero()
	{
		assertEquals(0, agent.getYPosition());
	}

	@Test
	public void testCreateAgent_ScoreIsZero()
	{
		assertEquals(0, agent.getScore());
	}

	@Test
	public void testChooseMove()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testExecuteMove()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetQValue()
	{
		fail("Not yet implemented");
	}
}
