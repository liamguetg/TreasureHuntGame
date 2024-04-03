package model;

import ui.GamePanel;

public class NpcOldMan extends Entities {

    public NpcOldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
    }

    //EFFECTS: gets the NPC sprite to display on screen
    public void getNPCImage() {
        up1 = setUpEntityTile("/npcSprites/oldman_up1");
        up2 = setUpEntityTile("/npcSprites/oldman_up2");
        down1 = setUpEntityTile("/npcSprites/oldman_down1");
        down2 = setUpEntityTile("/npcSprites/oldman_down2");
        right1 = setUpEntityTile("/npcSprites/oldman_right1");
        right2 = setUpEntityTile("/npcSprites/oldman_right2");
        left1 = setUpEntityTile("/npcSprites/oldman_left1");
        left2 = setUpEntityTile("/npcSprites/oldman_left2");
    }
}
