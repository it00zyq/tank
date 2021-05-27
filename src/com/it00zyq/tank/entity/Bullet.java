package com.it00zyq.tank.entity;

import com.it00zyq.tank.DirectionEnum;
import com.it00zyq.tank.TankFrame;
import com.it00zyq.tank.constant.Constant;

import java.awt.*;

/**
 * @author IT00ZYQ
 * @date 2021/5/27 22:32
 **/
public class Bullet {
    private int x;
    private int y;
    private DirectionEnum direction;
    private TankFrame tankFrame;

    public Bullet(int x, int y, DirectionEnum direction, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, Constant.BULLET_WIDTH, Constant.BULLET_HEIGHT);
        g.setColor(color);

        this.move();
    }


    public void move() {
        switch (this.direction) {
            case DOWN_S:
                y += Constant.BULLET_SPEED;
                break;
            case UP_W:
                y -= Constant.BULLET_SPEED;
                break;
            case LEFT_A:
                x -= Constant.BULLET_SPEED;
                break;
            case RIGHT_D:
                x += Constant.BULLET_SPEED;
                break;
            default:
                break;
        }
    }
}
