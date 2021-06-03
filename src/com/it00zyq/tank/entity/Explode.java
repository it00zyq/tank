package com.it00zyq.tank.entity;

import com.it00zyq.tank.utils.Images;
import java.awt.*;

/**
 * @author IT00ZYQ
 * @date 2021/6/3 8:20
 **/
public class Explode {
    private int x;
    private int y;
    private int step;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if (step < Images.explodes.length) {
            g.drawImage(Images.explodes[step++], this.x, this.y, Images.explodes[step].getWidth(), Images.explodes[step].getHeight(), null);
        }
    }

    public int getStep() {
        return step;
    }
}
