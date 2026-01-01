package Business_Layer;

import Presentation_Layer.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Game_Board {
    /**
     * 2D board representation. This class keeps the original "ArrayList of ArrayList" structure
     * to avoid breaking existing code/tests, but internally we treat it as a grid.
     */
    protected ArrayList<ArrayList<Tile>> arrays_Board;
    protected final InputReader reader;
    protected Player the_player;
    protected final ArrayList<Monster> Monsters;
    protected final ArrayList<Trap> Traps;

    public Game_Board(String file_path, int player){
        this.reader = new InputReader(file_path, player);
        this.reader.Read(this);
        this.the_player = this.reader.getThe_player();
        this.Monsters = this.reader.getMonsters();
        this.Traps = this.reader.getTraps();
    }

    public void setThe_player(Player the_player) {
        if (the_player == null) {
            throw new IllegalArgumentException("player cannot be null");
        }

        // Replace the tile at the current player coordinates.
        this.getArrays_Board()
                .get(this.getThe_player().getCoordinate().getY_coor())
                .set(this.getThe_player().getCoordinate().getX_coor(), the_player);

        this.the_player = the_player;
    }

    public void remove(Enemy enemy){
        if (enemy == null) {
            return;
        }

        // If the enemy is adjacent to the player, the player takes the enemy's position.
        if (this.getThe_player().getRange(enemy) <= 1) {
            this.getThe_player().replace_positions(enemy);
            this.getArrays_Board()
                    .get(this.getThe_player().getCoordinate().getY_coor())
                    .set(this.getThe_player().getCoordinate().getX_coor(),
                            new Empty(enemy.getCoordinate().getX_coor(), enemy.getCoordinate().getY_coor()));
        } else {
            this.getArrays_Board()
                    .get(enemy.getCoordinate().getY_coor())
                    .set(enemy.getCoordinate().getX_coor(), new Empty(enemy.getCoordinate().getX_coor(), enemy.getCoordinate().getY_coor()));
        }

        // Remove from the correct collection.
        if (enemy instanceof Monster) {
            this.getMonsters().remove(enemy);
        } else if (enemy instanceof Trap) {
            this.getTraps().remove(enemy);
        }
    }

    public void remove_player(){
        this.getThe_player().setCharacter("X");
    }


    public void game_tick (){
        this.getThe_player().On_Tick_Do();

        for (Monster monster:this.getMonsters()) {
            monster.On_Tick_Do(this.getThe_player(), this);
        }

        for (Trap trap:this.getTraps()) {
            trap.On_Tick_Do(this.getThe_player(), this);
        }

    }


    public void arrange_board(){
        // Rebuild a normalized board (every coordinate contains exactly one tile).
        if (this.arrays_Board == null || this.arrays_Board.isEmpty()) {
            return;
        }

        final int rows = this.arrays_Board.size();
        final int cols = this.arrays_Board.get(0).size();

        final ArrayList<ArrayList<Tile>> newBoard = new ArrayList<>(rows);
        for (int y = 0; y < rows; y++) {
            final ArrayList<Tile> row = new ArrayList<>(cols);
            for (int x = 0; x < cols; x++) {
                row.add(new Empty(x, y));
            }
            newBoard.add(row);
        }

        for (List<Tile> row : this.arrays_Board) {
            for (Tile tile : row) {
                newBoard.get(tile.getCoordinate().getY_coor())
                        .set(tile.getCoordinate().getX_coor(), tile);
            }
        }

        this.arrays_Board = newBoard;
    }


    @Override
    public String toString() {
        this.arrange_board();

        final StringBuilder sb = new StringBuilder();
        for (ArrayList<Tile> line : this.arrays_Board) {
            for (Tile tile : line) {
                sb.append(tile.getCharacter());
            }
            sb.append('\n');
        }
        return sb.toString();
    }










    public ArrayList<ArrayList<Tile>> getArrays_Board() {
        return this.arrays_Board;
    }
    public void setArrays_Board(ArrayList<ArrayList<Tile>> arrays_Board) { this.arrays_Board = arrays_Board; }

    public Player getThe_player() {
        return this.the_player;
    }
    public ArrayList<Monster> getMonsters() {
        return this.Monsters;
    }
    public ArrayList<Trap> getTraps() {
        return this.Traps;
    }

}
