package Day10;

import java.awt.image.BufferedImage;

public  class BigPlane extends FlyingObject implements InterfaceAward,InterfaceEnemy {

      private int speed=2;
      private  int score;
      private  int awardType;
      private  int width;
      private int height;
      private BufferedImage img;
      public BigPlane(){
          super((int)(Math.random()*Main.WIDTH),0,Main.BigPlane,3);
          img=Main.BigPlane;
          width=img.getWidth();
          height=img.getHeight();
          awardType = (int) (Math.random() * 2);
      }
      @Override
      public void move() {
    setY(getY()+speed);
      }


      @Override
      public int getScore() {
          return score;
      }


      @Override
      public int getAwardType() {
          return awardType;
      }
  }
