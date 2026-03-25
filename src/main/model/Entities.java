package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

// Represents a Super class for entities (NPC or player) with a position (X and Y coordinates),
// movement speed, direction, a solid area (Hit-box) and associated images.

public class Entities {
    GamePanel gp;

    //POSITION ON MAP
    protected int entityWorldX;
    protected int entityWorldY;

    //COLLISION PARAMETERS (SA)
    protected Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    protected int solidAreaDefaultX;
    protected int solidAreaDefaultY;
    protected Boolean collisionOn = false;

    //MOVEMENT TRACKERS
    protected int speed;
    protected String direction;
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    protected int newMoveCounter;

    //SPRITE IMAGES
    protected BufferedImage up1;
    protected BufferedImage up2;
    protected BufferedImage down1;
    protected BufferedImage down2;
    protected BufferedImage left1;
    protected BufferedImage left2;
    protected BufferedImage right1;
    protected BufferedImage right2;

    //EFFECTS: Constructor
    public Entities(GamePanel gp) {
        this.gp = gp;
    }

    //EFFECTS: Characterizes the movement of the specific entity
    public void movement() {
    }

    //EFFECTS: Updates the position and image of the entity on the map.
    @SuppressWarnings("methodlength")
    public void update() {
        movement();
        //COLLISION CHECKS
        collisionOn = false;
        gp.getColCheck().checkTile(this);
        gp.getColCheck().checkObject(this, false, gp.getObjList());
        gp.getColCheck().checkObject(this, false, gp.getRandItemList());
        gp.getColCheck().colEntPlayerCheck(this);

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

        //MOVEMENT COUNTER
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

    //MODIFIES: this
    //EFFECTS: Draws the entity on the screen (if not onscreen it is not drawn).
    @SuppressWarnings("methodlength")
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = entityWorldX - gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX();
        int screenY = entityWorldY - gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY();

        //ENSURES ONLY VISIBLE (ON SCREEN) SPRITES DRAWN:
        if (onScreen()) {
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
    }

    //EFFECTS: Checks if the entity is on screen.
    public boolean onScreen() {
        return entityWorldX + gp.getTileSize() > gp.getPlayer().entityWorldX - gp.getPlayer().getScreenX()
                && entityWorldX - gp.getTileSize() < gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX()
                && entityWorldY + gp.getTileSize() > gp.getPlayer().entityWorldY - gp.getPlayer().getScreenY()
                && entityWorldY - gp.getTileSize() < gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY();
    }

    //MODIFIES: this.
    //EFFECTS: Retrieves and scales the Entities image.
    public BufferedImage setUpEntityTile(String imagePath) {
        ToolScaleImage scalePlayerTile = new ToolScaleImage();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = scalePlayerTile.scaleImage(image, gp.getTileSize(), gp.getTileSize());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
