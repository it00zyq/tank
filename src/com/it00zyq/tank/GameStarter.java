package com.it00zyq.tank;


import com.it00zyq.tank.entity.Tank;
import com.it00zyq.tank.enums.DirectionEnum;
import com.it00zyq.tank.enums.GroupEnum;
import com.it00zyq.tank.utils.Constant;
import com.it00zyq.tank.utils.RandomUtil;

/**
 * @author IT00ZYQ
 * @date 2021/5/25 22:16
 **/
public class GameStarter {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

        // 初始化地方坦克
        for (int i = 0; i < Constant.ENEMY_TANK_COUNT; i++) {
            tankFrame.addTank(new Tank(RandomUtil.randomX(), RandomUtil.randomY(), DirectionEnum.DOWN_S, tankFrame, GroupEnum.ENEMY));
        }

        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }
    }
}
