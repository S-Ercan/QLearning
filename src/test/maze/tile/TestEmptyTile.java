package test.maze.tile;

import main.maze.tile.EmptyTile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestEmptyTile {
    @Test
    public void testCreate_HasZeroValue() {
        EmptyTile tile = new EmptyTile();
        assertEquals(0, tile.getValue());
    }
}
