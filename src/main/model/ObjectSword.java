package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;


//Represents a Sword Object that can be found on the map.
public class ObjectSword extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectSword(GamePanel gp) {
        this.gp = gp;
        name = "Sword";
        valuePerItem = 3;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/sword_normal.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}