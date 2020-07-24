package Day10;

import java.awt.image.BufferedImage;

public class Airplane  extends  FlyingObject implements InterfaceEnemy{
      private int  AirplaneLife;
  private int  speed;
  private  int score;
     public Airplane(){
         super((int)(Math.random()*Main.WIDTH),0,Main.airplane,1);
         speed=2;
         score=5;
     }
    @Override
    public void move() {
    setY(getY()+speed);
    }
    @Override
    public int getScore() {
        return 0;
    }
}
