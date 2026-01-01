package Business_Layer;

public class Boss extends Monster implements HeroicUnit{
    protected int ability_frequency;
    protected int combat_ticks;

    public Boss(int vision_range, String character, int x, int y, String name, int h_p, int attack_points, int defense_points, int experience_value, int ability_frequency) {
        super(vision_range, character, x, y, name, h_p, attack_points, defense_points, experience_value);
        this.ability_frequency = ability_frequency;
        this.combat_ticks = 0;
    }

    @Override
    public void On_Tick_Do(Player player, Game_Board game_board) {
        if(this.getRange(player) < this.vision_range){
            this.combat_ticks = this.combat_ticks + 1;
            if (this.combat_ticks == this.ability_frequency){
                this.Ability_Cast(game_board);
            } else if(this.getRange(player) <= 1){   /////  next to each other
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
    public void Ability_Cast(Game_Board game_board) {
        this.combat_ticks = 0;
        this.getMessageCallback().send(this.getName() + " shoots " + game_board.getThe_player().getName() + " for " + this.getAttack_points() + " damage.");
        this.attack(game_board.getThe_player(), this.getAttack_points(), game_board, "sp");
    }
}
