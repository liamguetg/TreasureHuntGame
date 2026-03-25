package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

//Represents a Coin Object that can be found on the map.
public class ObjectCoin extends ObjectSuper {
    GamePanel gp;

    //EFFECTS: Constructor
    public ObjectCoin(GamePanel gp) {
        this.gp = gp;
        name = "Coin";
        valuePerItem = 1;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/coin.png"));
            scaleObject.scaleImage(img, gp.getTileSize(), gp.getTileSize());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
