package Business_Layer;

public abstract class Enemy extends Unit{
    protected int Experience_value;

    protected Enemy(String character, Coordinate coordinate, String name, Health health, int attack_points, int defense_points, int experience_value) {
        super(character, coordinate, name, health, attack_points, defense_points);
        this.Experience_value = experience_value;
    }

    public String description(){
        return super.description() + "\n" + "        Experience Value: " + this.Experience_value;
    }

    public int getExperience_value(){
        return this.Experience_value;
    }

    public void setExperience_value(int experience_value) {
        this.Experience_value = experience_value;
    }



    public void on_death(Game_Board game_board, String killer){
        this.getMessageCallback().send(this.getName() + " died. "  + killer + " gained " + this.getExperience_value() + " experience points.");
        game_board.getThe_player().Experience_Addition(this.getExperience_value());
        game_board.remove(this);
    }




    @Override
    public void move(Tile tile, Game_Board game) {
        tile.move(this, game);
    }


    @Override
    public void move(Player player, Game_Board game) {  /////////  confrontation
        player.attack(this, (int)(Math.floor(Math.random()*(player.getAttack_points() + 1))), game, "re");
    }


    @Override
    public void move(Enemy enemy, Game_Board game) {
        /////////   do nothing, a monster cannot move into another monster or another trap
    }




    public abstract void On_Tick_Do(Player player, Game_Board game_board);

}
