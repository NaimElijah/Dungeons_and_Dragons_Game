package Presentation_Layer;

import Business_Layer.*;
import Business_Layer.factories.DefaultTileFactory;
import Business_Layer.factories.TileFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InputReader {
    private String file_path;
    private final int player;
    private final ArrayList<Monster> Monsters;
    private final ArrayList<Trap> Traps;
    private Player the_player;

    public InputReader(String file_path, int player){
        this.file_path = file_path;
        this.player = player;
        this.Monsters = new ArrayList<>();
        this.Traps = new ArrayList<>();
    }

    /**
     * Allows re-using the same reader instance to load a different level.
     */
    public void setFile_path(String file_path) { this.file_path = file_path; }

    public Player getThe_player() {
        return this.the_player;
    }
    public ArrayList<Monster> getMonsters() {
        return this.Monsters;
    }
    public ArrayList<Trap> getTraps() {
        return this.Traps;
    }

    /**
     * Reads a level file and populates the given {@link Game_Board}.
     *
     * The parsing is delegated to a {@link TileFactory} to avoid a giant if/else chain.
     */
    public void Read(Game_Board game_board){
        final ArrayList<ArrayList<Tile>> res = new ArrayList<>();
        List<String> lines = Collections.emptyList();

        try {
            lines = Files.readAllLines(Paths.get(this.file_path));
        } catch (IOException e) {
            // Keep behavior simple for a CLI project: print an error and load an empty board.
            System.out.println(e.getMessage());
            game_board.setArrays_Board(res);
            return;
        }

        if (lines.isEmpty()) {
            game_board.setArrays_Board(res);
            return;
        }

        // Clear lists in case the reader is re-used for a different level.
        this.Monsters.clear();
        this.Traps.clear();
        this.the_player = null;

        final int rows = lines.size();
        final int cols = lines.get(0).length();

        final TileFactory tileFactory = new DefaultTileFactory(this.player, this.Monsters, this.Traps, System.out::println, p -> this.the_player = p);

        for (int y = 0; y < rows; y++) {
            final String line = lines.get(y);
            final ArrayList<Tile> currLine = new ArrayList<>(cols);
            for (int x = 0; x < cols; x++) {
                currLine.add(tileFactory.createTile(line.charAt(x), x, y));
            }
            res.add(currLine);
        }

        game_board.setArrays_Board(res);
    }
}
