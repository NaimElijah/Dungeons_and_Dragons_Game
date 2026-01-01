package Business_Layer;

public class Empty extends Tile {
    public Empty(int x, int y){
        super(".", new Coordinate(x, y));
    }

    @Override
    public void move(Tile tile, Game_Board game) {
        this.replace_positions(tile);
    }

    @Override
    public void move(Enemy enemy, Game_Board game) {
        this.replace_positions(enemy);
    }

    @Override
    public void move(Player player, Game_Board game) {
        this.replace_positions(player);
    }


}
