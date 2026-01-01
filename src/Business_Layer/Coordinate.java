package Business_Layer;

public class Coordinate {
    protected int x_coor;
    protected int y_coor;

    public Coordinate(int x, int y){
        this.x_coor = x;
        this.y_coor = y;
    }

    public int getX_coor(){
        return this.x_coor;
    }
    public int getY_coor(){
        return this.y_coor;
    }

    public void setX_coor(int x_coor) {
        this.x_coor = x_coor;
    }
    public void setY_coor(int y_coor) {
        this.y_coor = y_coor;
    }
}
