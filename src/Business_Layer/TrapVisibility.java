package Business_Layer;

public class TrapVisibility {
    protected int visibility_time;
    protected int invisibility_time;
    protected boolean visible;
    protected int ticks_count;

    public TrapVisibility(int visibility_time, int invisibility_time){
        this.visibility_time = visibility_time;
        this.invisibility_time = invisibility_time;
        this.visible = true;
        this.ticks_count = 0;
    }

    public int getVisibility_time() {
        return this.visibility_time;
    }
    public void setVisibility_time(int visibility_time) {
        this.visibility_time = visibility_time;
    }

    public int getInvisibility_time() {
        return this.invisibility_time;
    }
    public void setInvisibility_time(int invisibility_time) {
        this.invisibility_time = invisibility_time;
    }

    public boolean getVisible(){
        return this.visible;
    }
    public void setVisible(boolean newvisible){
        this.visible = newvisible;
    }

    public int getTicks_count() {
        return this.ticks_count;
    }
    public void setTicks_count(int ticks_count) {   ///// may not be needed
        this.ticks_count = ticks_count;
    }

}
