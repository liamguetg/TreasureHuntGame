package model;


import ui.GamePanel;
import ui.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entities {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;


    //EFFECTS: constructor for player
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2); // 360 pixels (~the center of the screen)
        screenY = gp.screenHeight/2 - (gp.tileSize/2); // 264 pixels (~the center of the screen)

        // The solid area is the sprites body (not the whole tile), which works out to be these coordinates
        solidArea = new Rectangle(8, 16, gp.tileSize - 16, gp.tileSize - 16);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefaultValues();
        getPlayerImage();
    }

    //EFFECTS: sets the default (start settings) for the player.
    public void setDefaultValues() {
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

            // CHECK IF PLAYER IS COLLIDING WITH A SOLID
            collisionOn = false;
            gp.cCheck.checkTile(this);

            // CHECK IF PLAYER IS COLLIDING WITH AN OBJECT
            int objectIndex = gp.cCheck.checkObject(this, true);
            pickUpObject(objectIndex);

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

    //MODIFIES: this (hasKey, gp.objList)
    //EFFECTS: Updates status of items on screen if character interacts (collides) with them. Removes, if
    // the conditions are met. Increments hasKey if item is a key, subtracts hasKey if item is a door.
    public void pickUpObject (int i) {
        if (i != 999) {
            String objectName = gp.objList[i].name;

            switch (objectName) {
                case "Key":
//                    gp.playSoundEffect(1);
                    hasKey++;
                    gp.objList[i] = null;
                    gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if(hasKey > 0) {
//                        gp.playSoundEffect(3);
                        gp.objList[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    } else {
                        gp.ui.showMessage("You need a key to open this door.");
                    }
                    break;
                case "boots":
//                    gp.playSoundEffect(2);
                    speed += 2;
                    gp.objList[i] = null;
                    gp.ui.showMessage("You got new boots! Speed+2");
                    break;
                case "Chest":
                    gp.ui.gameDone = true;
//                    gp.stopMusic();
//                    gp.PlaySE(4);
                    break;
            }
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
            up1 = setUpPlayerTile("up1");
            up2 = setUpPlayerTile("up2");
            down1 = setUpPlayerTile("down1");
            down2 = setUpPlayerTile("down2");
            right1 = setUpPlayerTile("right1");
            right2 = setUpPlayerTile("right2");
            left1 = setUpPlayerTile("left1");
            left2 = setUpPlayerTile("left2");
    }

    public BufferedImage setUpPlayerTile(String playerTileName) {
        ToolScaleImage scalePlayerTile = new ToolScaleImage();
        BufferedImage playerImage = null;

        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/BbSprite/boy_" + playerTileName + ".png"));
            playerImage = scalePlayerTile.scaleImage(playerImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerImage;
    }
}
