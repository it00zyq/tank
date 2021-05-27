package com.it00zyq.tank.entity;

import com.it00zyq.tank.DirectionEnum;
import com.it00zyq.tank.TankFrame;
import com.it00zyq.tank.constant.Constant;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author IT00ZYQ
 * @date 2021/5/26 0:01
 **/
public class Tank {
    private int x;
    private int y;
    private DirectionEnum direction;
    private TankFrame tankFrame;

    public Tank(int x, int y, DirectionEnum direction, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
    }

    public void move(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                y -= Constant.TANK_SPEED;
                break;
            case KeyEvent.VK_A:
                x -= Constant.TANK_SPEED;
                break;
            case KeyEvent.VK_S:
                y += Constant.TANK_SPEED;
                break;
            case KeyEvent.VK_D:
                x += Constant.TANK_SPEED;
                break;
            default:
                break;
        }
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x, y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT);
        g.setColor(color);
    }

    /**
     * 发射子弹
     */
    public void fire() {
        int bulletX = this.x + Constant.TANK_WIDTH/2 - Constant.BULLET_WIDTH/2;
        int bulletY = this.y + Constant.TANK_HEIGHT/2 - Constant.BULLET_HEIGHT/2;
        // 添加子弹
        tankFrame.addBullet(new Bullet(bulletX, bulletY, this.direction, this.tankFrame));
    }

    /**
     * 改变坦克方向
     */
    public void changeDirection(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_W:
                this.direction = DirectionEnum.UP_W;
                break;
            case KeyEvent.VK_S:
                this.direction = DirectionEnum.DOWN_S;
                break;
            case KeyEvent.VK_A:
                this.direction = DirectionEnum.LEFT_A;
                break;
            case KeyEvent.VK_D:
                this.direction = DirectionEnum.RIGHT_D;
                break;
            default:
                break;
        }
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

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
}
