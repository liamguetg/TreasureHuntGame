package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectDoor extends ObjectSuper {

    GamePanel gp;

    public ObjectDoor(GamePanel gp) {

        this.gp = gp;

        name = "Door";
        collision = true;

        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/door.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
