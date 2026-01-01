package Business_Layer;

public class Monster extends Enemy{
    protected int vision_range;

    public Monster(int vision_range, String character, int x, int y, String name, int h_p, int attack_points, int defense_points, int experience_value) {
        super(character, new Coordinate(x, y), name, new Health(h_p, h_p), attack_points, defense_points, experience_value);
        this.vision_range = vision_range;
    }

    public int getVision_range() {
        return this.vision_range;
    }
    public void setVision_range(int vision_range) {
        this.vision_range = vision_range;
    }


    @Override
    public void On_Tick_Do(Player player, Game_Board game_board) {
        if(this.getRange(player) < this.vision_range){
            if(this.getRange(player) <= 1){   /////  next to each other
                this.attack(player, (int)(Math.floor(Math.random()*(this.getAttack_points() + 1))), game_board, "re");
            }else {    //////  chasing after the player
                int dx = this.getCoordinate().getX_coor() - player.getCoordinate().getX_coor();
                int dy = this.getCoordinate().getY_coor() - player.getCoordinate().getY_coor();
                if (Math.abs(dx) > Math.abs(dy)){
                    if(dx > 0){
                        this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()).get(this.getCoordinate().getX_coor()-1), game_board);// left
                    }else {
                        this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()).get(this.getCoordinate().getX_coor()+1), game_board);// right
                    }
                }else {
                    if (dy > 0){
                        this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()-1).get(this.getCoordinate().getX_coor()), game_board);// up
                    }else {
                        this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()+1).get(this.getCoordinate().getX_coor()), game_board);// down
                    }
                }
            }
        }else {    //////////   random movement
            int i = (int)(Math.floor(Math.random()*5));
            if(i == 0){   /////////  right
                this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()).get(this.getCoordinate().getX_coor()+1), game_board);
            }else if (i== 1){   /////////  left
                this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()).get(this.getCoordinate().getX_coor()-1), game_board);
            }else if (i== 2){   /////////  down
                this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()+1).get(this.getCoordinate().getX_coor()), game_board);
            }
            else if (i== 3){   /////////  up
                this.move(game_board.arrays_Board.get(this.getCoordinate().getY_coor()-1).get(this.getCoordinate().getX_coor()), game_board);
            }
        }
    }



    @Override
    public String description() {      /////////   returns full information of the current unit, maybe just .send(what we return here), maybe...
        return super.description() + "        Vision Range: " + this.vision_range + "\n";
    }



}
