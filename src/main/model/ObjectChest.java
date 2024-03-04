package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectChest extends ObjectSuper {

    GamePanel gp;

    public ObjectChest(GamePanel gp) {

        this.gp = gp;

        name = "Chest";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/chest.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
