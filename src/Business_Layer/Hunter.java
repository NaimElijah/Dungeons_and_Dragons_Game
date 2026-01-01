package Business_Layer;

import java.util.ArrayList;

public class Hunter extends Player{

    protected int range;
    protected int arrows_count;
    protected int ticks_count;

    public Hunter(int x, int y, String name, int h_p, int attack_points, int defense_points, int range) {
        super(new Coordinate(x, y), name, new Health(h_p, h_p), attack_points, defense_points);
        this.range = range;
        this.arrows_count = this.getPlayer_Level()*10;
        this.ticks_count = 0;
    }


    public void Level_Up(){
        super.Level_Up();
        ////////////////////////////  now addons  ////////////////////////////
        this.arrows_count = this.arrows_count + (10*this.getPlayer_Level());
        this.setAttack_points(this.getAttack_points() + (2*this.getPlayer_Level()));
        this.setDefense_points(this.getDefense_points() + (this.getPlayer_Level()));

        int hea_add = (10*this.getPlayer_Level()), att_add = (6*this.getPlayer_Level()), def_add = (this.getPlayer_Level()*2);  ///////  now change them accordingly
        String message = "\n" + this.getName() + " reached level " + this.getPlayer_Level() + ": +" + hea_add + " Health, +" + att_add + " Attack, +" + def_add + " Defense." + "\n";
        this.getMessageCallback().send(message);  /////////////////  the message with the regular upgrades + the class upgrades !!!
    }


    @Override
    public void Ability_Cast(Game_Board game_board) {
        ArrayList<Enemy> in_range_enemies = this.get_enemies_in_n_range(this.range, game_board, "sp");
        if(in_range_enemies.size() == 0) {
            this.getMessageCallback().send(this.getName() + " tried to shoot an arrow but there were no enemies in range.");
        }else if (this.arrows_count <= 0){
            this.getMessageCallback().send(this.getName() +" tried to shoot an arrow but there were no arrows available.");
        }else{
            this.arrows_count = this.arrows_count - 1;
            Enemy closest = in_range_enemies.get(0);
            if (in_range_enemies.size() > 1){
                for (Enemy enemy:in_range_enemies){  ////  getting the closest enemy
                    if (enemy.getRange(this) < closest.getRange(this)){
                        closest = enemy;
                    }
                }
            }
            this.getMessageCallback().send(this.getName() + " fired an arrow at " + closest.getName() + ".");
            this.attack(closest, this.getAttack_points(), game_board, "sp");
        }
    }


    public void On_Tick_Do() {
        if (this.ticks_count == 10){
            this.arrows_count = this.arrows_count + this.getPlayer_Level();
            this.ticks_count = 0;
        }else {
            this.ticks_count = this.ticks_count + 1;
        }
    }


    @Override
    public String description() {      /////////   returns full information of the current unit, maybe just .send(what we return here), maybe...
        return super.description() + "\n" + "        Arrows: " + this.arrows_count + "        Range: " + this.range;
    }


}
