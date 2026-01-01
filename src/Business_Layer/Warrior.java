package Business_Layer;

import java.util.ArrayList;

public class Warrior extends Player{

    protected int ability_cooldown;
    protected int remaining_cooldown;

    public Warrior(int x, int y, String name, int h_p, int attack_points, int defense_points, int ability_cooldown) {
        super(new Coordinate(x, y), name, new Health(h_p, h_p), attack_points, defense_points);
        this.ability_cooldown = ability_cooldown;
        this.remaining_cooldown = 0;
    }


    public void Level_Up(){
        super.Level_Up();
        ////////////////////////////  now addons  ////////////////////////////
        this.remaining_cooldown = 0;
        this.getHealth().setHealth_pool(this.getHealth().getHealth_pool() + (5*this.getPlayer_Level()));
        this.getHealth().setHealth_amount(this.getHealth().getHealth_pool());

        this.setAttack_points(this.getAttack_points() + (2*this.getPlayer_Level()));
        this.setDefense_points(this.getDefense_points() + this.getPlayer_Level());

        int hea_add = (15*this.getPlayer_Level()), att_add = (6*this.getPlayer_Level()), def_add = (this.getPlayer_Level()*2);  ///////  now change them accordingly
        String message = "\n" + this.getName() + " reached level " + this.getPlayer_Level() + ": +" + hea_add + " Health, +" + att_add + " Attack, +" + def_add + " Defense." + "\n";
        this.getMessageCallback().send(message);  /////////////////  the message with the regular upgrades + the class upgrades !!!
    }


    @Override
    public void Ability_Cast(Game_Board game_board) {
        if(this.remaining_cooldown > 0){
            this.getMessageCallback().send(this.getName() + " tried to cast Avenger's Shield, but there is a cooldown: " + this.remaining_cooldown + ".");
        }else{
            this.remaining_cooldown = this.ability_cooldown + 1;
            this.getHealth().setHealth_amount(Math.min(this.getHealth().getHealth_amount() + (10*this.getDefense_points()), this.getHealth().getHealth_pool()));
            this.getMessageCallback().send(this.getName() + " used Avenger's Shield, healing for " + (10*this.getDefense_points()));

            ArrayList<Enemy> enemies_in_3_range = this.get_enemies_in_n_range(3, game_board, "re");
            if (enemies_in_3_range.size() > 0) {
                int index_attacked = (int)(Math.floor(Math.random() * (enemies_in_3_range.size())));  /// the random enemy
                this.attack(enemies_in_3_range.get(index_attacked), this.getHealth().getHealth_pool() / 10, game_board, "sp");  /// attacking the random enemy
            }
        }
    }


    public void On_Tick_Do() {
        if(this.remaining_cooldown > 0){
            this.remaining_cooldown = this.remaining_cooldown - 1;
        }
    }


    @Override
    public String description() {      /////////   returns full information of the current unit, maybe just .send(what we return here), maybe...
        return super.description() + "        Cooldown: " + this.remaining_cooldown + "/" + this.ability_cooldown;
    }


}
