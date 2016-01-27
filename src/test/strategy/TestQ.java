package test.strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import main.maze.Direction;
import main.strategy.Q;

public class TestQ
{
	private Q q;
	private int x;
	private int y;

	@Before
	public void setUp()
	{
		x = 1;
		y = 2;
		q = new Q(x, y);
	}

	@Test
	public void testCreateQ_HasCorrectX()
	{
		assertEquals(x, q.getX());
	}

	@Test
	public void testCreateQ_HasCorrectY()
	{
		assertEquals(y, q.getY());
	}

	@Test
	public void testUpdateQ_QUpdated()
	{
		Q qNext = mock(Q.class);
		when(qNext.getMaxQValue()).thenReturn(0.0);
		q.update(Direction.LEFT, qNext, 10);

		assertEquals(10, q.getQValueForDirection(Direction.LEFT), 0);
	}

	@Test
	public void testNoUpdates_MaxValueIsZero()
	{
		assertEquals(0, q.getMaxQValue(), 0);
	}
}
