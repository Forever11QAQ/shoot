package Day10;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

public class JFrameDem {
    public static void main(String[] args) {
        //新建一个JFrame对象
        JFrame window = new JFrame();
        //创建一个画板对象
        MyPanael myPanel = new MyPanael();
        //把画板add到窗口对象里边去
        window.add(myPanel);
        //引用window调用方法setTitle设置标题
        window.setTitle("飞机大战");
        //设置窗口可显示化
        window.setVisible(true);
        //设置窗口的规格
        window.setSize(400, 700);
        //设置默认关闭选项
        window.setDefaultCloseOperation(1);
        //设置窗口居中
        window.setLocationRelativeTo(null);
    }

}

//自定义一个类继承JPanel
class MyPanael extends JPanel {
    @Override
    //建立一个paint有参构造方法
    public void paint(Graphics g) {
        //调用JDK当中paint方法
        super.paint(g);
        //创建一个Font对象
        Font font = new Font("微软雅黑", Font.BOLD, 20);
        //参数g调用setFont方法
        g.setFont(font);
        //参数g调用setColor方法
        g.setColor(Color.PINK);
        //调用drawString方法在画板中输入字符串
        g.drawString("4AM加油给爷冲", 20, 30);
        paintAirPlane(g);
    }
    //
    private void paintAirPlane(Graphics g) {
        //background,png和MyPanel同包

        BufferedImage img = null;
        //try类似于else if
        try {
            img = ImageIO.read(MyPanael.class.getResourceAsStream("QQ图片20200716125409.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, this);
    }
}
