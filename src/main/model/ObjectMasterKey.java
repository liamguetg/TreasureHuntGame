package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectMasterKey extends ObjectSuper {
    GamePanel gp;

    public ObjectMasterKey(GamePanel gp) {
        this.gp = gp;
        name = "Master Key";
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/Items/MasterKey.png"));
//            scaleObject.scaleImage(img, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

