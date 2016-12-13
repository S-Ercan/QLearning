package test.strategy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import main.agent.Direction;
import main.strategy.Q;

public class TestQ {
	private Q q;
	private Q qNext;
	private int x;
	private int y;

	@Before
	public void setUp() {
		x = 1;
		y = 2;

		q = new Q(x, y);
		qNext = mock(Q.class);
		when(qNext.getMaxUtility()).thenReturn(0.0);
	}

	@Test
	public void testCreateQ_HasCorrectX() {
		assertEquals(x, q.getX());
	}

	@Test
	public void testCreateQ_HasCorrectY() {
		assertEquals(y, q.getY());
	}

	@Test
	public void testUpdateQ_QUpdated() {
		q.update(Direction.LEFT, qNext, 10);
		assertEquals(10, q.getUtilityForDirection(Direction.LEFT), 0);
	}

	@Test
	public void testNoUpdates_MaxValueIsZero() {
		assertEquals(0, q.getMaxUtility(), 0);
	}

	@Test
	public void testExcludeDirection_ReturnsZeroForExcludedDirection() {
		q.update(Direction.RIGHT, qNext, 10);
		q.excludeDirection(Direction.RIGHT);

		assertEquals(0, q.getUtilityForDirection(Direction.RIGHT), 0);
	}

	@Test
	public void testGetBestDirectionWithoutRandomness_ReturnsBestDirection() {
		q.update(Direction.RIGHT, qNext, 10);
		q.setPRandExploration(0);

		assertEquals(Direction.RIGHT, q.getBestDirection());
	}

	@Test
	public void testGetBestDirectionWithRandomness_ReturnsSuboptimalDirection() {
		// Set RIGHT as the best direction
		Direction bestDirection = Direction.RIGHT;
		Direction returnedDirection = bestDirection;
		q.update(bestDirection, qNext, 10);

		// With pRandExploration = 1, eventually a direction other than the best
		// one should be returned
		while (returnedDirection == bestDirection) {
			q.setPRandExploration(1);
			returnedDirection = q.getBestDirection();
		}

		assertNotEquals(bestDirection, returnedDirection);
	}
}
