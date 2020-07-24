package Day10;

public class Bullet  extends FlyingObject{
    private  int speed;
     public Bullet(int x,int y){
         super(x,y,Main.bullet,1);
        speed=3;
     }
    @Override
    public void move() {
        this.setY(this.getY()-speed);
    }
}
