package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectBoots extends ObjectSuper {

    GamePanel gp;

    public ObjectBoots(GamePanel gp) {

        this.gp = gp;
        name = "boots";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/boots.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
