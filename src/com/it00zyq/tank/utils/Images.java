package com.it00zyq.tank.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * @author IT00ZYQ
 * @date 2021/5/3 20:24
 **/
public class Images {
    public static BufferedImage myTankU, myTankD, myTankL, myTankR;
    public static BufferedImage[] explodes = new BufferedImage[16];


    static {
        try {
            myTankU = ImageIO.read(Objects.requireNonNull(Images.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            myTankR = ImageUtil.rotateImage(myTankU, 90);
            myTankD = ImageUtil.rotateImage(myTankU, 180);
            myTankL = ImageUtil.rotateImage(myTankU, -90);

            for (int i = 1; i <= 16; i++) {
                explodes[i-1] = ImageIO.read(Objects.requireNonNull(Images.class.getClassLoader().getResourceAsStream("images/e" + i + ".git")));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
