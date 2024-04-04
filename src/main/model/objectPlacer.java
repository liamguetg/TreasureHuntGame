package model;

import ui.GamePanel;

public class objectPlacer {

    GamePanel gp;

    public objectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.objList[0] = new ObjectKey(gp);
//        gp.objList[0].position(21, 19);
        gp.objList[0].worldX = gp.tileSize * 21;
        gp.objList[0].worldY = gp.tileSize * 19;
    }

    public void setNPC(){
        //Old Man NPC
        gp.npc[0] = new NpcOldMan(gp);
        gp.npc[0].entityWorldX = gp.tileSize*21;
        gp.npc[0].entityWorldY = gp.tileSize*21;
    }
}
