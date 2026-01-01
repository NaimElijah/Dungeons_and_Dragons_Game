package Business_Layer;

import java.util.ArrayList;

public class Rogue extends Player{

    protected int cost;
    protected int current_energy;

    public Rogue(int cost, int x, int y, String name, int h_p, int attack_points, int defense_points) {
        super(new Coordinate(x, y), name, new Health(h_p, h_p), attack_points, defense_points);
        this.cost = cost;
        this.current_energy = 100;
    }

    public void Level_Up(){
        super.Level_Up();
        ////////////////////////////  now addons  ////////////////////////////
        this.current_energy = 100;
        this.setAttack_points(this.getAttack_points() + (3*this.getPlayer_Level()));

        int hea_add = (10*this.getPlayer_Level()), att_add = (4*this.getPlayer_Level()), def_add = (this.getPlayer_Level());  ///////  now change them accordingly
        String message = "\n" + this.getName() + " reached level " + this.getPlayer_Level() + ": +" + hea_add + " Health, +" + att_add + " Attack, +" + def_add + " Defense." + "\n";
        this.getMessageCallback().send(message);  /////////////////  the message with the regular upgrades + the class upgrades !!!
    }

    @Override
    public void Ability_Cast(Game_Board game_board) {
        if(this.current_energy < this.cost){
            this.getMessageCallback().send(this.getName() + " tried to cast Fan of Knives, but there was not enough energy: " + this.current_energy + "/" + this.cost + ".");
        }else{
            this.getMessageCallback().send(this.getName() + " cast Fan of Knives.");
            this.current_energy = this.current_energy - this.cost;
            ArrayList<Enemy> surrounding_enemies_in_range_2 = this.get_enemies_in_n_range(2, game_board, "re");
            if(surrounding_enemies_in_range_2.size() > 0){
                for (Enemy enemy: surrounding_enemies_in_range_2){
                    this.attack(enemy, this.getAttack_points(), game_board, "sp");
                }
            }
        }
    }


    public void On_Tick_Do() {
        this.current_energy = Math.min(this.current_energy + 10, 100);
    }

    @Override
    public String description() {      /////////   returns full information of the current unit, maybe just .send(what we return here), maybe...
        return super.description() + "        Energy: " + this.current_energy + "/100";
    }


}
