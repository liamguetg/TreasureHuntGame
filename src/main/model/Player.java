package model;

// import org.json.JSONObject;
import ui.GamePanel;
import ui.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

//=======================
//Maybe better in other file
//=======================

// Represents a player:
// - Player is centered in the screen unless they are near the boarders of the world map.
// - Player Sprite (image) changes with direction of movement.
public class Player extends Entities {

    KeyHandler keyH;
    private final int screenX;
    private final int screenY;

    // EFFECTS: constructor for player
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        // PLACE CHARACTER IN CENTER OF SCREEN
        screenX = gp.getScreenWidth() / 2 - (gp.getTileSize() / 2); // 360 pixels
        screenY = gp.getScreenHeight() / 2 - (gp.getTileSize() / 2); // 264 pixels

        // HIT-BOX
        solidArea = new Rectangle(8, 16, gp.getTileSize() - 16, gp.getTileSize() - 16);
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        setDefaultPlayer();
        getPlayerImage();
    }

    // MODIFIES: this
    // EFFECTS: sets the default (starting) position and speed for the player.
    // (~middle of the map)
    public void setDefaultPlayer() {
        entityWorldX = gp.getTileSize() * 23; // column 23 (pixel 1104)
        entityWorldY = gp.getTileSize() * 21; // row 21 (pixel 1008)
        speed = 4;
        direction = "down";
    }

    // MODIFIES: this
    // EFFECTS: updates the player and y positions
    public void update() {
        if (keyH.getUpPressed() || keyH.getDownPressed() || keyH.getLeftPressed() || keyH.getRightPressed()) {
            direction = playerDirection();
            anyCollision();

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        entityWorldY -= speed;
                        break;
                    case "down":
                        entityWorldY += speed;
                        break;
                    case "left":
                        entityWorldX -= speed;
                        break;
                    case "right":
                        entityWorldX += speed;
                        break;
                }
            }
            spriteNum = spriteCounters();
        }
    }

    // MODIFIES: direction
    // EFFECTS: Gets the direction of the player
    public String playerDirection() {
        if (keyH.getUpPressed()) {
            direction = "up";
        } else if (keyH.getDownPressed()) {
            direction = "down";
        } else if (keyH.getLeftPressed()) {
            direction = "left";
        } else if (keyH.getRightPressed()) {
            direction = "right";
        }
        return direction;
    }

    // MODIFIES: collisionOn
    // EFFECTS: Checks if player movement results in any collision (with tiles,
    // objects or entities)
    // and calls methods to handle corresponding interactions.
    public void anyCollision() {
        collisionOn = false;

        // TILES
        gp.getColCheck().checkTile(this);

        // OBJECTS AND ITEMS
        int objectIndex = gp.getColCheck().checkObject(this, true, gp.getObjList());
        pickUpObject(objectIndex, gp.getObjList());
        int randObjectIndex = gp.getColCheck().checkObject(this, true, gp.getRandItemList());
        pickUpObject(randObjectIndex, gp.getRandItemList());

        // ENTITIES
        int npcIndex = gp.getColCheck().colPlayerEntCheck(this, gp.getNpcList());
        interactNPC(npcIndex);

        // WIN?
        gp.getColCheck().checkWon(this);
    }

    // MODIFIES: SpriteNum
    // EFFECTS: Alternates the spriteNum between 1 and 2.
    public int spriteCounters() {
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        return spriteNum;
    }

    // MODIFIES: this, gp.objList, gp.randItemList, gp.inventory
    // EFFECTS: Updates status of items on screen if character interacts (collides)
    // with object. Removes, them from
    // screen and may add them to inventory if the conditions are met.
    @SuppressWarnings("methodlength")
    public void pickUpObject(int i, ObjectSuper[] theList) {
        if (i != 999) {

            ObjectSuper newItem = theList[i];
            String objectName = theList[i].name;

            switch (objectName) {
                case "Key":
                    gp.getInv().addToInv(newItem, 1);
                    theList[i] = null;
                    // gp.ui.showMessage("You got a key!");
                    break;
                case "Door":
                    if (gp.getInv().getItemInInv("Master Key") != null) {
                        theList[i] = null;
                        gp.getInv().removeItemFromInv("Master Key", 1);
                    }
                    break;
                case "Boots":
                    gp.getInv().addToInv(newItem, 1);
                    speed += 2;
                    theList[i] = null;
                    // gp.ui.showMessage("You got new boots! Speed+2");
                    break;
                case "Chest":
                    gp.getInv().addToInv(newItem, 1);
                    theList[i] = null;
                    break;
                case "Sword":
                    gp.getInv().addToInv(newItem, 1);
                    theList[i] = null;
                    break;
                case "Shield":
                    gp.getInv().addToInv(newItem, 1);
                    theList[i] = null;
                    break;
            }
        }
    }

    // MODIFIES: gp.gameState
    // EFFECTS: Handles players interaction with NPC if they collide. Changing
    // gameState to allow for trade.
    public void interactNPC(int i) {
        if (i != 999) {
            // Message?
            gp.setGameState(gp.dialogueState);
        }
    }

    // MODIFIES: this
    // EFFECTS: Draws the correct sprite image of the player on the screen.
    @SuppressWarnings("methodlength")
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
    }

    // //EFFECTS: gets the player redboy sprite to display on screen
    // public void getPlayerImage() {
    // up1 = setUpEntityTile("/BbSprite/redboyUp1");
    // up2 = setUpEntityTile("/BbSprite/redboyUp2");
    // down1 = setUpEntityTile("/BbSprite/redboyDown1");
    // down2 = setUpEntityTile("/BbSprite/redboyDown2");
    // right1 = setUpEntityTile("/BbSprite/redboyRight1");
    // right2 = setUpEntityTile("/BbSprite/redboyRight2");
    // left1 = setUpEntityTile("/BbSprite/redboyLeft1");
    // left2 = setUpEntityTile("/BbSprite/redboyLeft2");
    // }

    // EFFECTS: gets the player sprite to display on screen
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

    // PERSISTENCE:

    // MODIFIES: this
    // EFFECTS: loads in the saved Y-position
    public void setPlayerY(int savedYPos) {
        entityWorldY = savedYPos;
    }

    // MODIFIES: this
    // EFFECTS: loads in the saved X-position
    public void setPlayerX(int savedXPos) {
        entityWorldX = savedXPos;
    }

    // public JSONObject playerToJson() {
    // JSONObject jsonPlayer = new JSONObject();
    // jsonPlayer.put("X-position:", entityWorldX);
    // jsonPlayer.put("Y-position", entityWorldY);
    // return jsonPlayer;
    // }

    // GETTERS:

    public int getPlayerX() {
        return entityWorldX;
    }

    public int getPlayerY() {
        return entityWorldY;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }
}
