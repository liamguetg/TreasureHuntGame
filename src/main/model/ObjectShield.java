package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectShield extends ObjectSuper {
    GamePanel gp;

    public ObjectShield(GamePanel gp) {
        this.gp = gp;
        name = "Shield";
        valuePerItem = 2;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/shield_wood.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
