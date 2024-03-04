package model;

import ui.GamePanel;

public class ObjectPlacer {

    GamePanel gp;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objList[0] = new ObjectKey(gp);
        gp.objList[0].worldX = 23 * gp.tileSize;
        gp.objList[0].worldY = 7 * gp.tileSize;

        gp.objList[1] = new ObjectKey(gp);
        gp.objList[1].worldX = 23 * gp.tileSize;
        gp.objList[1].worldY = 40 * gp.tileSize;

        gp.objList[2] = new ObjectKey(gp);
        gp.objList[2].worldX = 38 * gp.tileSize;
        gp.objList[2].worldY = 8 * gp.tileSize;

        gp.objList[3] = new ObjectDoor(gp);
        gp.objList[3].worldX = 10 * gp.tileSize;
        gp.objList[3].worldY = 11 * gp.tileSize;

        gp.objList[4] = new ObjectDoor(gp);
        gp.objList[4].worldX = 8 * gp.tileSize;
        gp.objList[4].worldY = 28 * gp.tileSize;

        gp.objList[5] = new ObjectDoor(gp);
        gp.objList[5].worldX = 12 * gp.tileSize;
        gp.objList[5].worldY = 22 * gp.tileSize;

        gp.objList[6] = new ObjectChest(gp);
        gp.objList[6].worldX = 10 * gp.tileSize;
        gp.objList[6].worldY = 7 * gp.tileSize;

        gp.objList[7] = new ObjectBoots(gp);
        gp.objList[7].worldX = 37 * gp.tileSize;
        gp.objList[7].worldY = 42 * gp.tileSize;
    }
}
