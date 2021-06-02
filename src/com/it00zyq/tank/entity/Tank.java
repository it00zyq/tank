package com.it00zyq.tank.entity;

import com.it00zyq.tank.enums.DirectionEnum;
import com.it00zyq.tank.TankFrame;
import com.it00zyq.tank.enums.GroupEnum;
import com.it00zyq.tank.utils.Constant;
import com.it00zyq.tank.utils.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * @author IT00ZYQ
 * @date 2021/5/26 0:01
 **/
public class Tank {
    private int x;
    private int y;
    private DirectionEnum direction;
    private TankFrame tankFrame;
    private boolean death;
    private GroupEnum group;
    private Rectangle r;

    private static Random random = new Random();

    public Tank(int x, int y, DirectionEnum direction, TankFrame tankFrame, GroupEnum group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.death = false;
        this.group = group;
        this.r = new Rectangle(x, y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT);
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
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
        r.setLocation(this.x, this.y);
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.PINK);
        g.drawRect(x, y, Constant.TANK_WIDTH, Constant.TANK_HEIGHT);
        g.setColor(color);

        switch(direction) {
            case LEFT_A:
                g.drawImage(Images.myTankL, x, y, null);
                break;
            case UP_W:
                g.drawImage(Images.myTankU, x, y, null);
                break;
            case DOWN_S:
                g.drawImage(Images.myTankD, x, y, null);
                break;
            case RIGHT_D:
                g.drawImage(Images.myTankR, x, y, null);
                break;
            default:
                break;
        }

        // 敌方坦克，发射子弹
        if (GroupEnum.ENEMY.equals(this.getGroup())) {
            // 随机发射子弹
            if (random.nextInt(Constant.TOTAL_NUM) < Constant.FIRE_NUM) {
                this.fire();
            }
        }
    }

    /**
     * 发射子弹
     */
    public void fire() {
        int bulletX = this.x + Constant.TANK_WIDTH/2 - Constant.BULLET_WIDTH/2;
        int bulletY = this.y + Constant.TANK_HEIGHT/2 - Constant.BULLET_HEIGHT/2;
        // 添加子弹
        tankFrame.addBullet(new Bullet(bulletX, bulletY, this.direction, this.tankFrame, this.group));
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

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public GroupEnum getGroup() {
        return group;
    }

    public void setGroup(GroupEnum group) {
        this.group = group;
    }

    public Rectangle getR() {
        return r;
    }
}
