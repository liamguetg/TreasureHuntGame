package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectSword extends ObjectSuper {
    GamePanel gp;

    public ObjectSword(GamePanel gp) {
        this.gp = gp;
        name = "Sword";
        valuePerItem = 3;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/sword_normal.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}