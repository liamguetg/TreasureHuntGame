package model;

import ui.GamePanel;

import java.util.Random;

public class NpcOldMan extends Entities {

    public NpcOldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNPCImage();
        setDialogue();
        line = 0;

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

    public void movement() {

        newMoveCounter++;
        if (newMoveCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(4) + 1;

            if (i == 1) {
                direction = "up";
            }
            if (i == 2) {
                direction = "left";
            }
            if (i == 3) {
                direction = "right";
            }
            if (i == 4) {
                direction = "down";
            }
            newMoveCounter = 0;
        }
    }

    public void setDialogue () {
        dialogue[0] = "Would ya like to trade? (Y/N)";
        dialogue[1] = "Buy a master Key for 3 normal keys? (Y/N)";
        dialogue[2] = "You a broke boy now";
        dialogue[3] = "You a broke bitch";
    }

    public void setLine(int i) {
     line = 1;
    }

    public void speak() {
        gp.ui.currentDialogue = dialogue[line];
    }
}
