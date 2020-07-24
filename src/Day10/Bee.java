package Day10;

import java.awt.image.BufferedImage;

public class Bee extends  FlyingObject implements InterfaceAward{
    //定义一个私有变量为蜜蜂的生命值
    private  int BeeLife;
    //定义蜜蜂在x轴y轴方向上的速度
   private   int  x_speed=1;
   private int y_speed=2;
   private  int awardType;
   private BufferedImage img;
   private int width;
   private  int height;
   public Bee(){
       super((int)(Math.random()*Main.WIDTH),0,Main.bee,1);
       img=Main.bee;
       width=img.getWidth();
       height=img.getHeight();
       awardType = (int) (Math.random() * 2);
   }
    @Override
    public void move() {
       //Bee所在的x，y上的值加上在x，y方向上的速度
  setX(getX()+x_speed);
  setY(getY()+y_speed);
  //
       if(getX()>=Main.WIDTH-getWidth()){
           x_speed=-1;
       }
       else if(getX()==0){
           x_speed=1;
       }
    }


    @Override
    public int getAwardType() {
        return awardType;
    }
}
