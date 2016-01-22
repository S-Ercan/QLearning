package test.strategy;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
	public void testUpdate()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetMaxQValue()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testExcludeDirection()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testGetQValueForDirection()
	{
		fail("Not yet implemented");
	}

}
