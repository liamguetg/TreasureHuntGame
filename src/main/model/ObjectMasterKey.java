package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a MasterKey Object that can be found on the map.
public class ObjectMasterKey extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectMasterKey(GamePanel gp) {
        this.gp = gp;
        name = "Master Key";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/MasterKey.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

