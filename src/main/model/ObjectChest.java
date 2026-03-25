package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Chest Object that can be found on the map.
public class ObjectChest extends ObjectSuper {


    //EFFECTS: Constructor
    public ObjectChest(GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        valuePerItem = 15;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/chest.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
