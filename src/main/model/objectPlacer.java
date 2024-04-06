package model;

import ui.GamePanel;

import java.util.Random;

public class objectPlacer {

    GamePanel gp;
    Random randTile = new Random();
    Random randItem = new Random();

    public objectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setRandItems() {
        int numItemsOnMap = 0;
        while (numItemsOnMap < 10) {
            int xCord = randTile.nextInt(gp.maxWorldCol);
            int yCord = randTile.nextInt(gp.maxWorldRow);
            int tileNum1 = gp.tileM.mapTileNum[xCord][yCord];
            int item = randItem.nextInt(100);
            if (gp.tileM.tile[tileNum1].collision == false) {
                if (item <= 50) {
                    gp.randItemList[numItemsOnMap] = new ObjectKey(gp);
                }
                if (item > 50 && item <= 65) {
                    gp.randItemList[numItemsOnMap] = new ObjectSword(gp);
                }
                if (item > 65 && item <= 80) {
                    gp.randItemList[numItemsOnMap] = new ObjectShield(gp);
                }
                if (item > 80 && item <= 95) {
                    gp.randItemList[numItemsOnMap] = new ObjectBoots(gp);
                }
                if (item > 95) {
                    gp.randItemList[numItemsOnMap] = new ObjectChest(gp);
                }
                gp.randItemList[numItemsOnMap].worldX = gp.tileSize * xCord;
                gp.randItemList[numItemsOnMap].worldY = gp.tileSize * yCord;
                numItemsOnMap++;
            }
        }
    }

    public void setObjects() {
        gp.objList[0] = new ObjectDoor(gp);
        gp.objList[0].worldX = gp.tileSize * 10;
        gp.objList[0].worldY = gp.tileSize * 11;
    }

    public void setNPC(){
        //Old Man NPC
        gp.npc[0] = new NpcOldMan(gp);
        gp.npc[0].entityWorldX = gp.tileSize*21;
        gp.npc[0].entityWorldY = gp.tileSize*21;
    }
}
