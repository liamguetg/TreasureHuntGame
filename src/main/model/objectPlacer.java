package model;

import ui.GamePanel;

import java.util.Random;

//Places objects, items and entities (NPC) on the map
public class ObjectPlacer {

    GamePanel gp;
    private Random randTile = new Random();
    private Random randItem = new Random();

    //EFFECTS: Constructor
    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    //MODIFIES: this
    //EFFECTS: Randomly assigns and places Items on the map.
    @SuppressWarnings("methodlength")
    public void setRandItems() {
        ObjectSuper[] randItemList = gp.getRandItemList();
        int numItemsOnMap = 0;

        while (numItemsOnMap < 10) {
            int xcord = randTile.nextInt(gp.getMaxWorldCol());
            int ycord = randTile.nextInt(gp.getMaxWorldRow());
            int tileNum1 = gp.getTileManaged().getMapTileNum()[xcord][ycord];
            int item = randItem.nextInt(100);
            if (!gp.getTileManaged().getTile()[tileNum1].collision) {
                if (item <= 50) {
                    randItemList[numItemsOnMap] = new ObjectKey(gp);
                }
                if (item > 50 && item <= 65) {
                    randItemList[numItemsOnMap] = new ObjectSword(gp);
                }
                if (item > 65 && item <= 80) {
                    randItemList[numItemsOnMap] = new ObjectShield(gp);
                }
                if (item > 80 && item <= 95) {
                    randItemList[numItemsOnMap] = new ObjectBoots(gp);
                }
                if (item > 95) {
                    randItemList[numItemsOnMap] = new ObjectChest(gp);
                }
                randItemList[numItemsOnMap].worldX = gp.getTileSize() * xcord;
                randItemList[numItemsOnMap].worldY = gp.getTileSize() * ycord;
                numItemsOnMap++;
            }
        }
    }

    //EFFECTS: Places the given list of objects on the map.
    public void setObjects() {
        ObjectSuper[] objList = gp.getObjList();
        objList[0] = new ObjectDoor(gp);
        objList[0].worldX = gp.getTileSize() * 10;
        objList[0].worldY = gp.getTileSize() * 11;
    }

    //EFFECTS: Places the given list of entities on the map.
    public void setNPC() {
        Entities[] npc = gp.getNpcList();
        npc[0] = new NpcSalesMan(gp);
        npc[0].entityWorldX = gp.getTileSize() * 21;
        npc[0].entityWorldY = gp.getTileSize() * 21;
    }
}
