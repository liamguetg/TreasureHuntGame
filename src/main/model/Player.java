package model;


import ui.GamePanel;
import ui.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entities {

    KeyHandler keyH;

    public final int screenX;
    public final int screenY;


    //EFFECTS: constructor for player
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        //PLACE CHARACTER IN CENTER OF SCREEN
        screenX = gp.screenWidth/2 - (gp.tileSize/2); // 360 pixels
        screenY = gp.screenHeight/2 - (gp.tileSize/2); // 264 pixels

        //HIT-BOX
        solidArea = new Rectangle(8, 16, gp.tileSize - 16, gp.tileSize - 16);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefaultPlayer();
        getPlayerImage();
    }

    //MODIFIES: this
    //EFFECTS: sets the default (start settings) for the player.
    public void setDefaultPlayer() {
        entityWorldX = gp.tileSize * 23; //column 23 of the worldMap (pixel 1104) (~the middle of the map)
        entityWorldY = gp.tileSize * 21; //row 21 of the worldMap (pixel 1008) (~the middle of the map)
        speed = 4;
        direction = "down";
    }

    //MODIFIES: playerWorldX and playerWorldY
    //EFFECTS: updates the player and y positions
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            //COLLISION CHECKS: (collision with object and entity produces an int (999 if collision occurs)
            // this can then be used in subsequent methods that do something as a result of collision)
            collisionOn = false;
            gp.collisionCheck.checkTile(this);

            int objectIndex = gp.collisionCheck.checkObject(this, true, gp.objList);
            pickUpObject(objectIndex, gp.objList);
            int randObjectIndex = gp.collisionCheck.checkObject(this, true, gp.randItemList);
            pickUpObject(randObjectIndex, gp.randItemList);

            int npcIndex = gp.collisionCheck.colPlayerEntCheck(this, gp.npc);
            interactNPC(npcIndex);

            gp.collisionCheck.checkWinningTile(this);

            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (collisionOn == false) {
                switch (direction){
                    case "up": entityWorldY -= speed; break;
                    case "down": entityWorldY += speed; break;
                    case "left": entityWorldX -= speed; break;
                    case "right": entityWorldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

//    public void wonGame(Entities entity){
//        int playerPos[][];
//        int player
//        playerPos = [entity.entityWorldX][entity.entityWorldY];
//        int winningTile = gp.tileM.mapTileNum[gp.tileSize * 10][gp.tileSize * 12];
//        int if (entity.collisionOn && (tileNum2 == winningTile || tileNum1 == winningTile)) {
//        if
//    }

    //MODIFIES: this (hasKey, gp.objList)
    //EFFECTS: Updates status of items on screen if character interacts (collides) with them. Removes, if
    // the conditions are met. Increments hasKey if item is a key, subtracts hasKey if item is a door.
    public void pickUpObject (int i, ObjectSuper theList[]) {
        if (i != 999) {
            ObjectSuper newItem = theList[i];
            String objectName = theList[i].name;

            switch (objectName) {
                case "Key":
                    gp.inventory.addToInventory(newItem);
                    theList[i] = null;
//                    gp.ui.showMessage("You got a key!");
                    break;
//                case "Door":
//                    if(hasKey > 0) {
//                        gp.objList[i] = null;
//                        hasKey--;
//                        gp.ui.showMessage("You opened the door!");
//                    } else {
//                        gp.ui.showMessage("You need a key to open this door.");
//                    }
//                    break;
                case "Door":
                    if (gp.inventory.getItemInInvWithName("Master Key") != null) {
                        theList[i] = null;
                        gp.inventory.removeFromInventoryWithName("Master Key", 1);
                    }
                    break;
                case "Boots":
                    gp.inventory.addToInventory(newItem);
                    speed += 2;
                    theList[i] = null;
//                    gp.ui.showMessage("You got new boots! Speed+2");
                    break;
                case "Chest":
                    gp.inventory.addToInventory(newItem);
                    theList[i] = null;
//                    gp.ui.gameDone = true;
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("you hit the bitch");
            gp.gameState = gp.dialogueState;
            gp.npc[i].speak();
        }
    }

    //EFFECTS: Draws the image of the player on the game screen
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    //EFFECTS: gets the player sprite to display on screen
    public void getPlayerImage() {
            up1 = setUpEntityTile("/BbSprite/boy_up1");
            up2 = setUpEntityTile("/BbSprite/boy_up2");
            down1 = setUpEntityTile("/BbSprite/boy_down1");
            down2 = setUpEntityTile("/BbSprite/boy_down2");
            right1 = setUpEntityTile("/BbSprite/boy_right1");
            right2 = setUpEntityTile("/BbSprite/boy_right2");
            left1 = setUpEntityTile("/BbSprite/boy_left1");
            left2 = setUpEntityTile("/BbSprite/boy_left2");
    }
}
