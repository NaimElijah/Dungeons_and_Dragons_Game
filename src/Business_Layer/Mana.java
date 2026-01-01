package Business_Layer;

public class Mana {
    protected int mana_pool;
    protected int current_mana;

    public Mana(int mana_pool, int current_mana){
        this.mana_pool = mana_pool;
        this.current_mana = current_mana;
    }


    @Override
    public String toString() {
        return this.current_mana + "/" + this.mana_pool;
    }

    public int getMana_pool() {
        return this.mana_pool;
    }

    public int getCurrent_mana() {
        return this.current_mana;
    }

    public void setMana_pool(int mana_pool) {
        this.mana_pool = mana_pool;
    }

    public void setCurrent_mana(int current_mana) {
        if (current_mana > this.mana_pool){
            this.current_mana = this.mana_pool;
        }else if (current_mana < 0){
            this.current_mana = 0;
        }else{   /////  anyway it won't go below 0 because we won't let it when casting the ability
             this.current_mana = current_mana;
        }
    }
}
