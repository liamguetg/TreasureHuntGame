package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectCoin extends ObjectSuper {
    GamePanel gp;

    public ObjectCoin(GamePanel gp) {
        this.gp = gp;
        name = "Coin";
        valuePerItem = 1;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/coin.png"));
            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
