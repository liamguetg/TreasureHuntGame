package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectKey extends ObjectSuper {

    GamePanel gp;

    public ObjectKey(GamePanel gp) {
        this.gp = gp;
        name = "Key";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/key.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
