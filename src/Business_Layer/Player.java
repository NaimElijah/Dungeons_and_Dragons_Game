package Business_Layer;

public abstract class Player extends Unit implements HeroicUnit{
    protected int Experience;
    protected int Player_Level;

    protected Player(Coordinate coordinate, String name, Health health, int attack_points, int defense_points) {
        super("@", coordinate, name, health, attack_points, defense_points);
        this.Experience = 0;
        this.Player_Level = 1;
    }

    public String description(){
        return super.description() + "        Level: " + this.Player_Level + "\n" + "        Experience: " + this.Experience + "/" + (50*this.Player_Level);
    }


    public void Level_Up(){
        this.Experience = this.Experience - (50*this.Player_Level);
        this.Player_Level = this.Player_Level + 1;

        int tempHealth_pool = this.getHealth().getHealth_pool();
        int new_health_pool = tempHealth_pool + (10*this.Player_Level);
        this.setHealth(new Health(new_health_pool, new_health_pool));

        this.setAttack_points(this.getAttack_points() + (4*this.Player_Level));
        this.setDefense_points(this.getDefense_points() + this.Player_Level);
    }


    public void Experience_Addition(int Addition){   ////////  only needed in this class, might need to be in every player class, think it's ok here
        this.Experience = this.Experience + Addition;
        while(this.Experience >= 50*this.Player_Level){
            this.Level_Up();  ////  I want this to activate the Level_Up() of Warrior, Mage or Rogue
        }
    }


    public abstract void On_Tick_Do();


    public void on_death(Game_Board game_board, String killer){
        this.getMessageCallback().send(this.getName() + " was killed by " + killer + ".");
        this.getMessageCallback().send("You lost.");
        game_board.remove_player();
    }




    @Override
    public void move(Tile tile, Game_Board game) {
        tile.move(this, game);
    }

    @Override
    public void move(Player player, Game_Board game) {   /////////  a player runs into a player, possible only in multiplayer, not needed in single player.
        //////  do nothing or heal if the class is healer(make this class)
    }

    @Override
    public void move(Enemy enemy, Game_Board game) {       //  only when a monster is moving to a player(attacks), in this case not needed
        // the monster attack() the player    // because it already attacks when close to player in its on_tick_do(), so this method is never reached.
    }







    public int getExperience(){
        return this.Experience;
    }
    public void setExperience(int experience) {
        this.Experience = experience;
    }

    public int getPlayer_Level() {
        return this.Player_Level;
    }
    public void setPlayer_Level(int player_Level) {
        this.Player_Level = player_Level;
    }


}
