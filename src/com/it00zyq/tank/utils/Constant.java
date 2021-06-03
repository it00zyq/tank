package com.it00zyq.tank.utils;

/**
 * @author IT00ZYQ
 * @date 2021/5/25 22:19
 **/
public interface Constant {
    /**
     * 游戏窗口的宽高
     */
    int FRAME_WIDTH = 780;
    int FRAME_HEIGHT = 810;

    /**
     * 坦克大小
     */
    int TANK_WIDTH = Images.myTankU.getWidth();
    int TANK_HEIGHT = Images.myTankU.getHeight();

    /**
     * 坦克移动速度
     */
    int TANK_SPEED = 10;

    /**
     * 子弹大小
     */
    int BULLET_WIDTH = 10;
    int BULLET_HEIGHT = 10;

    /**
     * 子弹速度
     */
    int BULLET_SPEED = 15;

    /**
     * 初始敌方坦克数量
     */
    int ENEMY_TANK_COUNT = 5;

    /**
     * 敌方坦克发射子弹的概率 FIRE_NUM/TOTAL_NUM
     */
    int TOTAL_NUM = 100;
    int FIRE_NUM = 3;
}
