package Business_Layer.factories;

import Business_Layer.*;

/**
 * Centralizes the mapping between a player's selection index (1..7) and the concrete {@link Player} type.
 */
public final class DefaultPlayerFactory {

    private DefaultPlayerFactory() {}

    /**
     * @return a new player instance for the given choice, or {@code null} if the choice is invalid.
     */
    public static Player create(int choice, int x, int y) {
        switch (choice) {
            case 1:
                return new Warrior(x, y, "Jon Snow", 300, 30, 4, 3);
            case 2:
                return new Warrior(x, y, "The Hound", 400, 20, 6, 5);
            case 3:
                return new Mage(x, y, "Melisandre", 100, 5, 1, 300, 30, 15, 5, 6);
            case 4:
                return new Mage(x, y, "Thoros of Myr", 250, 25, 4, 150, 20, 20, 3, 4);
            case 5:
                return new Rogue(20, x, y, "Arya Stark", 150, 40, 2);
            case 6:
                return new Rogue(50, x, y, "Bronn", 250, 35, 3);
            case 7:
                return new Hunter(x, y, "Ygritte", 220, 30, 2, 6);
            default:
                return null;
        }
    }
}
