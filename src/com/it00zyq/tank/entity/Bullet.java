package com.it00zyq.tank.entity;

import com.it00zyq.tank.enums.DirectionEnum;
import com.it00zyq.tank.TankFrame;
import com.it00zyq.tank.enums.GroupEnum;
import com.it00zyq.tank.utils.Constant;

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
    private GroupEnum group;
    private boolean death;
    private Rectangle r;

    public Bullet(int x, int y, DirectionEnum direction, TankFrame tankFrame, GroupEnum group) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tankFrame = tankFrame;
        this.group = group;
        this.r = new Rectangle(this.x, this.y, Constant.BULLET_WIDTH, Constant.BULLET_HEIGHT);
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
        r.setLocation(this.x, this.y);

        // 子弹飞出边界时，删除子弹，防止内存泄露
        if (this.x > Constant.FRAME_WIDTH || this.x < 0 || this.y > Constant.FRAME_HEIGHT || this.y < 0) {
            this.death = true;
        }
    }

    public void collision(Tank tank) {
        // 己方子弹不对己方坦克造成伤害
        if (tank.getGroup().equals(this.group)) {
            return ;
        }

        // 每次检测都new, GC压力大
        // Rectangle r1 = new Rectangle(tank.getX(), tank.getY(), Constant.TANK_WIDTH, Constant.TANK_HEIGHT);
        // Rectangle r2 = new Rectangle(this.x, this.y, Constant.BULLET_WIDTH, Constant.BULLET_HEIGHT);
        // 判断两个物体是否相交，即碰撞
        if (this.r.intersects(tank.getR())) {
            this.death = true;
            tank.setDeath(true);
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

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }
}
