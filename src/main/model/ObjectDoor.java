package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Door Object that can be found on the map.
public class ObjectDoor extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectDoor(GamePanel gp) {
        this.gp = gp;
        name = "Door";
        collision = true;

        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/door.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
