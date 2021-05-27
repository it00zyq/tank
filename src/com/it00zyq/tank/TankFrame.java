package com.it00zyq.tank;

import com.it00zyq.tank.constant.Constant;
import com.it00zyq.tank.entity.Bullet;
import com.it00zyq.tank.entity.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author IT00ZYQ
 * @date 2021/5/25 22:50
 **/
public class TankFrame extends JFrame {

    private Tank myTank = new Tank(200, 200, DirectionEnum.UP_W, this);
    private List<Bullet> bullets = new LinkedList<>();

    public TankFrame() {
        // 设置窗口标题
        super.setTitle("IT00ZYQ - 坦克大战");
        // 获取屏幕宽高，设置弹出位置以及窗体大小，使游戏窗口居中
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        super.setBounds((width - 800) / 2, (height - 800) / 2, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        // 设置窗体大小不可调
        super.setResizable(false);
        // 设置关闭窗口时结束程序
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置背景颜色
        super.setBackground(Color.BLACK);

        // 添加键盘监听
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // 坦克移动
                myTank.move(e.getKeyCode());

                // 改变坦克方向
                myTank.changeDirection(e.getKeyCode());
                // 刷新页面，就是调用paint()方法
                // repaint();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_J:
                        myTank.fire();
                        break;
                    default:
                        break;
                }
            }
        });

        // 设置显示窗口
        super.setVisible(true);
    }


    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        myTank.paint(g);
        for (Bullet bullet : bullets) {
            bullet.paint(g);
        }
    }





}
