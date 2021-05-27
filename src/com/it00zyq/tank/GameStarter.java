package com.it00zyq.tank;


/**
 * @author IT00ZYQ
 * @date 2021/5/25 22:16
 **/
public class GameStarter {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();

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
