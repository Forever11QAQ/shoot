package Day10;

import java.awt.image.BufferedImage;

public class Hero extends  FlyingObject {
    private  int score;

    public  Hero(int x,int y) {
        super(x,y,Main.hero0,3);
    }
private int count=0;
    private BufferedImage[] herosImg={Main.hero0,Main.hero1};
    @Override
    public void move() {
        count++;
        setImg(herosImg[count/10%2]);
        if(count%2==0) {
            setImg(Main.hero0);
        }
        else  {
           setImg(Main.hero1);
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void  addScore(int Score){
           this.setScore(this.getScore()+score);
     }
    public void addLife(){
        this.setLife(getLife()+1);
    }
     public int doubleFire=0;
     public void  addDoubleFire(int getDoubleFire){
        doubleFire+=20;
     }
    //发射子弹  生成新的子弹
    public  Bullet[] shoot(){
         Bullet[] bullets;
         if(doubleFire==0){
            bullets=new Bullet[1];
             //根据英雄机的x和y，计算子弹的x和y
             bullets[0]=new Bullet(this.getX()+getWidth()/2,this.getY());
         }
         else{
             bullets=new Bullet[2];
             bullets[0]=new Bullet(this.getX()+getWidth()/4,this.getY());
             bullets[1]=new Bullet(this.getX()+getWidth()/4*3,this.getY());
             doubleFire--;
         }
       //返回子弹
        return bullets;
    }

    public int getScore() {
        return score;
    }
}
