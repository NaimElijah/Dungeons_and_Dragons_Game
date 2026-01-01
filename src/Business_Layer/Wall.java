package Business_Layer;

public class Wall extends Tile {
    public Wall(int x, int y){
        super("#", new Coordinate(x, y));
    }

    @Override
    public void move(Tile tile, Game_Board game) {
        ///////   can't move into wall so won't do anything
    }

    @Override
    public void move(Enemy enemy, Game_Board game) {
        ///////   can't move into wall so won't do anything
    }

    @Override
    public void move(Player player, Game_Board game) {
        ///////   can't move into wall so won't do anything
    }


}
