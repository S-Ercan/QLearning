package test.maze.tile;

import static org.junit.Assert.*;

import org.junit.Test;

import main.maze.tile.RewardTile;

public class TestRewardTile {
	@Test
	public void testCreate_SetsValue() {
		int value = 1;
		RewardTile tile = new RewardTile(value);
		assertEquals(value, tile.getValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWithZeroValue_ThrowsException() {
		new RewardTile(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCreateWithNegativeValue_ThrowsException() {
		new RewardTile(-1);
	}
}
