package com.it00zyq.tank;

import com.it00zyq.tank.entity.Explode;
import com.it00zyq.tank.enums.DirectionEnum;
import com.it00zyq.tank.enums.GroupEnum;
import com.it00zyq.tank.utils.Constant;
import com.it00zyq.tank.entity.Bullet;
import com.it00zyq.tank.entity.Tank;
import com.it00zyq.tank.utils.Images;
import com.it00zyq.tank.utils.RandomUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author IT00ZYQ
 * @date 2021/5/25 22:50
 **/
public class TankFrame extends JFrame {

    private Tank myTank = new Tank(200, 200, DirectionEnum.UP_W, this, GroupEnum.OUR);
    private List<Tank> tanks = new LinkedList<>();
    private List<Bullet> bullets = new LinkedList<>();
    private List<Explode> explodes = new LinkedList<>();

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
        super.getContentPane().setBackground(Color.BLACK);
        super.setAutoRequestFocus(true);

        // 添加键盘监听
        super.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void addBullet(Bullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(Bullet bullet) {
        bullets.remove(bullet);
    }

    public void addTank(Tank tank) {
        tanks.add(tank);
    }

    public void removeTank(Tank tank) { tanks.remove(tank); }


    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量：" + bullets.size(), 20, 60);
        g.setColor(color);
        super.paint(g);
        myTank.paint(g);

        for (Tank tank : tanks) {
            if (tank.isDeath()) {
                explodes.add(new Explode(tank.getX(), tank.getY()));
            }
        }

        // 去除已经死亡的子弹
        bullets = bullets.stream().filter(e -> !e.isDeath()).collect(Collectors.toList());
        // 去除已经死亡的坦克
        tanks = tanks.stream().filter(e -> !e.isDeath()).collect(Collectors.toList());
        // 去除爆炸完成的效果
        explodes = explodes.stream().filter(e -> e.getStep() < Images.explodes.length - 1).collect(Collectors.toList());

        // 使用foreach循环会报错，因为在遍历的同时可能要将飞出边界的子弹从集合中删除
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        // 画出敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        // 画出爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        // 碰撞检测
        for (Bullet bullet : bullets) {
            for (Tank tank : tanks) {
                bullet.collision(tank);
            }
        }

        if (tanks.size() < Constant.ENEMY_TANK_COUNT) {
            this.addTank(new Tank(RandomUtil.randomX(), RandomUtil.randomY(), DirectionEnum.DOWN_S, this, GroupEnum.ENEMY));
        }

//        for (Bullet bullet : bullets) {
//            bullet.paint(g);
//        }
    }





}
