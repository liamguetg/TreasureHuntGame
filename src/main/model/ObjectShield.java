package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Shield Object that can be found on the map.
public class ObjectShield extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectShield(GamePanel gp) {
        this.gp = gp;
        name = "Shield";
        valuePerItem = 2;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/shield_wood.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
