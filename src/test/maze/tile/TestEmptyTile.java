package test.maze.tile;

import static org.junit.Assert.*;

import org.junit.Test;

import main.maze.tile.EmptyTile;

public class TestEmptyTile {
	@Test
	public void testCreate_HasZeroValue() {
		EmptyTile tile = new EmptyTile();
		assertEquals(0, tile.getValue());
	}
}
