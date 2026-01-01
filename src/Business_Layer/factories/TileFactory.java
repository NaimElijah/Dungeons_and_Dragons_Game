package Business_Layer.factories;

import Business_Layer.Tile;

/**
 * Factory for creating tiles while loading a level.
 *
 * Keeping parsing logic out of {@code InputReader} makes the code easier to extend
 * (e.g., adding a new monster type is a single registration line).
 */
public interface TileFactory {

    /**
     * Creates the appropriate {@link Tile} for the given symbol.
     *
     * @param symbol the character found in the level file
     * @param x      column index
     * @param y      row index
     */
    Tile createTile(char symbol, int x, int y);
}
