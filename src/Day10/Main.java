package Day10;

import Day09.main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Main extends JPanel {
    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage hero_ember0;
    public static BufferedImage hero_ember1;
    public static BufferedImage hero_ember2;
    public static BufferedImage hero_ember3;
    public static BufferedImage pause;
    public static BufferedImage start;
    public static BufferedImage airplane;
    public static BufferedImage bee;
    public static BufferedImage BigPlane;
    public static BufferedImage bullet;
    public static BufferedImage background;

    static {
        //一次性读取图片
        try {
            hero0 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero0.png"));
            hero1 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero1.png"));
            hero_ember0 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero_ember0.png"));
            hero_ember1 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero_ember1.png"));
            hero_ember2 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero_ember2.png"));
            hero_ember3 = ImageIO.read(Main.class.getResourceAsStream("Photo/hero_ember3.png"));
            pause = ImageIO.read(Main.class.getResourceAsStream("Photo/pause.png"));
            start = ImageIO.read(Main.class.getResourceAsStream("Photo/start.png"));
            airplane = ImageIO.read(Main.class.getResourceAsStream("Photo/airplane.png"));
            bee = ImageIO.read(Main.class.getResourceAsStream("Photo/bee.png"));
            BigPlane = ImageIO.read(Main.class.getResourceAsStream("Photo/BigPlane.png"));
            bullet = ImageIO.read(Main.class.getResourceAsStream("Photo/bullet.png"));
            background = ImageIO.read(Main.class.getResourceAsStream("Photo/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //定义一个成员变量fly
    FlyingObject fly;
    //定义一个数组存放飞行物
    //private ArrayList<FlyingObject> flyings;
    //初始化flyings和buttles
    public ArrayList<FlyingObject> flyings ;
    public ArrayList<Bullet> bullets;
    public Main(){
        flyings = new ArrayList<FlyingObject>();
        bullets=new ArrayList<>();
    }
  public final int START=0;
  public final int PAUSE=1;
  public final int GAME_OVER=3;
  public final int RUNNING=2;

  private int state=START;
    Hero h = new Hero(getX(),getY());
    //Airplane a = new Airplane();
    //BigPlane b1 = new BigPlane();
    //Bullet b2=new Bullet();
    private java.util.Timer timer = new java.util.Timer();
    //重写自定义绘画方法
    public void action() {
        //过3s开始定时器任务,1s执行一次
        timer.schedule(new TimerTask() {//优化一个匿名内部类
            public void run() {
                creatFlyingsObject();
                moveFlyingsObject();
                //判断越界的方法
                outOfBound();
                h.move();
                //发射子弹
                shootAction(h.shoot());
                bulletmove();
                bangaction();
                //shootByAction();
                repaint();
            }
        }, 500, 15);
         //
        MouseAdapter adapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
          if(state==0) {
              state=START;
              state = RUNNING;
          }
          else if(state==GAME_OVER){
              state=START;
          }
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }

            public void mouseMoved(MouseEvent e) {
                int mouse_x = e.getX();
                int mouse_y = e.getY();
                h.setX(mouse_x);
                h.setY(mouse_y);
                repaint();
            }
        };
        this.addMouseMotionListener(adapter);
        this.addMouseListener(adapter);
    }

    //判断是否超出边界的方法
    private void outOfBound() {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            if (fly.getY() > Main.HEIGHT) {
                flyings.remove(i);
                //返回原来的位置
                i--;
            }
        }
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bu = bullets.get(i);
            if (bu.getY() > Main.HEIGHT) {
                bullets.remove(i);
                i--;
            }
        }
    }

    //子弹从英雄机当中射出的方法
    private  int bulletIndex=0;
    private void shootAction(Bullet[] arr) {
        bulletIndex++;
        if(bulletIndex%10==0) {
            Bullet[] bu = h.shoot();
            for (int i = 0; i < bu.length; i++) {
                bullets.add(bu[i]);
            }
        }
    }
    //射出的子弹移动的方法
    private void bulletmove() {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bu = bullets.get(i);
            bu.move();
        }
    }
    //定义flyingIndex控制频率
    private int flyingIndex = 0;
    //生成飞行物的方法
    private void creatFlyingsObject() {
        flyingIndex++;
        if (flyingIndex % 20 == 0) {
            int ran = (int) (Math.random() * 20);
            FlyingObject fly;
            if (ran == 0) {
                fly = new Bee();
            } else if (ran == 1 || ran == 2) {
                fly = new BigPlane();
            } else {
                fly = new Airplane();

            }
            flyings.add(fly);
        }
    }
    //判断飞行物跟英雄机相碰撞
   //private  void shootByAction(){
       //for (int i = 0; i < flyings.size(); i++) {
            //FlyingObject fly = flyings.get(i);
           // if (h.shootByFlying(fly)){
               // h.minusLife();
               // flyings.remove(i);
                //i--;
           // }
        //}
   // }
    //移动所有飞行物
    private void moveFlyingsObject() {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            fly.move();
        }
    }
       //碰撞的判断,把子弹简化地看成一个点
        private  void bangaction(){
        for(int i=0;i<bullets.size();i++){
                    Bullet b2=bullets.get(i);
                    int b2_x=b2.getX();
                    int b2_y=b2.getY();
            for(int j=0;j<flyings.size();j++){
                 FlyingObject fly=flyings.get(j);
                 int fly_x=fly.getX();
                 int fly_y=fly.getY();
                if(b2_x>fly_x&&b2_x<fly_x+fly.getWidth()&&b2_y>fly_y&&b2_y<fly_y+fly.getHeight()){
                   fly.minusLife();
                    if(fly.getLife()==0){
                        if(fly instanceof InterfaceEnemy){
                           InterfaceEnemy enemy=(InterfaceEnemy)fly;
                            h.addScore(enemy.getScore());
                        }
                        if(fly instanceof  InterfaceAward){
                            InterfaceAward award=(InterfaceAward)fly;
                            if(award.getAwardType()==InterfaceAward.ADD_LIFE)
                                h.addLife();
                            else{
                                h.addDoubleFire(5);
                            }
                        }
                        flyings.remove(j);
                    }
                    bullets.remove(i);
                    i--;
                    break;
                }
            }
        }
    }
    private void paintScore(Graphics g){
        g.drawString("生命值"+h.getLife(),5,20);
        g.drawString("分数"+h.getScore(),5,40);
    }
    public void paint(Graphics g) {
        //清除绘画内容
        super.paint(g);
        g.drawImage(background, 0, 0, this);
        g.drawImage(h.getImg(), h.getX(), h.getY(), this);
        paintButtle(g);
        paintFlyingObjects(g);
        paintScore(g);

    }
    //构造画飞行物的方法
    private void paintFlyingObjects(Graphics g) {
        for (int i = 0; i < flyings.size(); i++) {
            FlyingObject fly = flyings.get(i);
            g.drawImage(fly.getImg(), fly.getX(), fly.getY(), this);
        }
    }
    //画子弹
    private void paintButtle(Graphics g) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bu = bullets.get(i);
            g.drawImage(bu.getImg(), bu.getX(), bu.getY(), this);
        }
    }
   //定义静态变量
    public static final int WIDTH = 500;
    public static final int HEIGHT = 700;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        Main main = new Main();
        window.add(main);
        window.setVisible(true);
        window.setSize(500, 700);
        window.setDefaultCloseOperation(3);
        main.action();
    }

}
