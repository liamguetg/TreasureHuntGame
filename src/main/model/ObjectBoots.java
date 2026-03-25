package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Boots Object that can be found on the map.
public class ObjectBoots extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectBoots(GamePanel gp) {

        this.gp = gp;
        name = "Boots";
        valuePerItem = 10;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/boots.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
