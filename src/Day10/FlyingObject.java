package Day10;

import java.awt.image.BufferedImage;
  //创建一个抽象类（父类）
public   abstract class  FlyingObject {
      //定义protected成员变量x，y，img，width，height
      private  int x;
      private  int y;
      private  int width;
      private int height;
      private int life;
      private BufferedImage img;
      protected BufferedImage[] ember;
       protected FlyingObject(int x, int y, BufferedImage img, int life){
           this.img=img;
           this.width=img.getWidth();
           this.height=img.getHeight();
           this.x=x;
           this.y=y;
           this.life=life;
       }

      public int getLife() {
          return life;
      }

      public void setLife(int life) {
          this.life = life;
      }
      public abstract void move();
          public  void minusLife() {
              this.life--;
          }

      //对变量get set方法
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public BufferedImage getImg() {
        return img;
    }
    public void setImg(BufferedImage img) {
        this.img = img;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

  }

