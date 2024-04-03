package model;

import ui.GamePanel;

public class ObjectPlacer {

    GamePanel gp;

    public ObjectPlacer(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
    }

    public void setNPC(){
        gp.npc[0] = new NpcOldMan(gp);
        gp.npc[0].entityWorldX = gp.tileSize*21;
        gp.npc[0].entityWorldY = gp.tileSize*21;
    }
}
