package test.maze.tile;

import main.maze.tile.PunishmentTile;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPunishmentTile {
    @Test
    public void testCreate_SetsValue() {
        int value = -1;
        PunishmentTile tile = new PunishmentTile(value);
        assertEquals(value, tile.getValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithZeroValue_ThrowsException() {
        new PunishmentTile(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithPositiveValue_ThrowsException() {
        new PunishmentTile(1);
    }
}
