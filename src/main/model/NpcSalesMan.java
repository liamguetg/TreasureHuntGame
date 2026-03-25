package model;

import ui.GamePanel;

import java.util.Random;

//Represents the NPC that runs the store. Has a movement speed, random direction setter and
// associated images.

public class NpcSalesMan extends Entities {

    public NpcSalesMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;
        getNpcImage();
    }

    //EFFECTS: gets the NPC sprite to display on screen
    public void getNpcImage() {
        up1 = setUpEntityTile("/npcSprites/oldman_up1");
        up2 = setUpEntityTile("/npcSprites/oldman_up2");
        down1 = setUpEntityTile("/npcSprites/oldman_down1");
        down2 = setUpEntityTile("/npcSprites/oldman_down2");
        right1 = setUpEntityTile("/npcSprites/oldman_right1");
        right2 = setUpEntityTile("/npcSprites/oldman_right2");
        left1 = setUpEntityTile("/npcSprites/oldman_left1");
        left2 = setUpEntityTile("/npcSprites/oldman_left2");
    }

    //MODIFIES: this
    //EFFECTS: Randomly assigns a direction for the NPCs movement.
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
}