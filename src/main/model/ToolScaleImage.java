package model;

// A utility tool to scale images. Used when getting an image before it enters the game loop.
// Otherwise, the image will be scaled everytime the game "loops" (60 times per second).

import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolScaleImage {

    public BufferedImage scaleImage(BufferedImage original, int width, int height) {

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0,0, width, height, null);

        return scaledImage;
    }
}
