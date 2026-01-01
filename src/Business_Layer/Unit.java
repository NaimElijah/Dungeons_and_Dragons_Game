package Business_Layer;

import java.util.ArrayList;

public abstract class Unit extends Tile {
    protected String Name;
    protected Health Health;
    protected int Attack_points;
    protected int Defense_points;

    protected MessageCallback messageCallback;    ////////  initialized later with a method when a Unit is created  !!!!!

    protected Unit(String character, Coordinate coordinate, String name, Health health, int attack_points, int defense_points){
        super(character, coordinate);
        this.Name = name;
        this.Health = health;
        this.Attack_points = attack_points;
        this.Defense_points = defense_points;
    }


    ////////////////////   different on tick do()'s in the different unit's classes

    public String description(){
        return "** " + this.Name + "'s Status ** :        Health: " + this.Health.toString() + "        Attack: " + this.Attack_points + "        Defense: " + this.Defense_points;
    }

    public void initialize_messages(MessageCallback m){  /////  when we create the units, we initialize this for each of them
        this.messageCallback = m;
    }


    public ArrayList<Enemy> get_enemies_in_n_range (int range, Game_Board game_board, String type){  //////  maybe give the player the board so we can access the Enemies
        if (type.equals("re")) {
            ArrayList<Enemy> res = new ArrayList<Enemy>();
            for (Monster m : game_board.getMonsters()) {
                if (m.getRange(game_board.getThe_player()) < range) {
                    res.add(m);
                }
            }
            for (Trap t : game_board.getTraps()) {
                if (t.getRange(game_board.getThe_player()) < range) {
                    res.add(t);
                }
            }
            return res;
        }else {
            ArrayList<Enemy> res = new ArrayList<Enemy>();   //////  maybe make a <= version for Boss  !!!!!!!!!!!!!!!
            for (Monster m : game_board.getMonsters()) {
                if (m.getRange(game_board.getThe_player()) <= range) {
                    res.add(m);
                }
            }
            for (Trap t : game_board.getTraps()) {
                if (t.getRange(game_board.getThe_player()) <= range) {
                    res.add(t);
                }
            }
            return res;
        }
    }



    public String getName() {
        return this.Name;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public Health getHealth() {
        return this.Health;
    }
    public void setHealth(Health health) {
        this.Health = health;
    }

    public int getAttack_points() {
        return this.Attack_points;
    }
    public void setAttack_points(int attack_points) {
        this.Attack_points = attack_points;
    }

    public int getDefense_points() { return this.Defense_points; }
    public void setDefense_points(int defense_points) {
        this.Defense_points = defense_points;
    }

    public MessageCallback getMessageCallback() { return this.messageCallback; }



    public abstract void on_death(Game_Board game_board, String killer);





    public void attack(Unit unit, int attack_amount, Game_Board game, String type){  /////////  later see where to do the .send(messages), not complicated
        if (type.equals("re")) {
            this.getMessageCallback().send(this.getName() + " engaged in combat with " + unit.getName()); ///////////   message
            this.getMessageCallback().send(this.description()); ///////////   attacker description
            this.getMessageCallback().send(unit.description()); ///////////   defender description
            this.getMessageCallback().send(this.getName() + " rolled " + attack_amount + " attack points."); ///////////   message
        }
        unit.defense(this, attack_amount, game, type);

    }


    public void defense(Unit unit, int attack_amount, Game_Board game, String type){  /////////  later see where to do the .send(messages), not complicated
        int defense_amount = (int)(Math.floor(Math.random()*(this.getDefense_points() + 1)));
        this.getMessageCallback().send(this.getName() + " rolled " + defense_amount + " defense points."); ///////////   message
        int damage = attack_amount - defense_amount;
        if (damage < 0) {
            damage = 0;
        }
        if (type.equals("re")) {
            this.getMessageCallback().send(unit.getName() + " dealt " + damage + " damage to " + this.getName() + "."); ///////////   message
        }else {
            this.getMessageCallback().send(unit.getName() + " hit " + this.getName() + " for "+ damage + " ability damage.");
        }
        this.getHealth().setHealth_amount(this.getHealth().getHealth_amount() - damage);
        if (this.getHealth().getHealth_amount() <= 0){
            this.on_death(game, unit.getName());
        }

    }


}
