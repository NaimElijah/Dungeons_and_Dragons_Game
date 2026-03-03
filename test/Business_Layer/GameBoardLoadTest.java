package Business_Layer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardLoadTest {

    @Test
    void testLevelLoadsSuccessfully() {
        Game_Board board = new Game_Board("data/levels_dir/level1.txt", 1);

        assertNotNull(board.getThe_player());
        assertFalse(board.getMonsters().isEmpty());
        assertFalse(board.getTraps().isEmpty());
    }
}
