package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Key Object that can be found on the map.
public class ObjectKey extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectKey(GamePanel gp) {
        this.gp = gp;
        name = "Key";
        valuePerItem = 1;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/key.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
