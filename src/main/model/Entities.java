package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entities {
    GamePanel gp;

    public int entityWorldX;
    public int entityWorldY;
    public int speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;

    /*
     solidArea field is to create the part of the entity which is solid (cannot pass through other solid objects)
     Since making the whole entity (the entire tile it takes up) solid causes collisions to be too
     frequent/annoying, the solidArea is made to be smaller than a tile.
    */
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public int solidAreaDefaultWidth;
    public int solidAreaDefaultHeight;
    public Boolean collisionOn = false;

    public Entities(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        int screenX = entityWorldX - gp.player.entityWorldX + gp.player.screenX;
        int screenY = entityWorldY - gp.player.entityWorldY + gp.player.screenY;

        // if statement so we only draws the necessary tiles (the tiles surrounding the player)
        if (    entityWorldX + gp.tileSize > gp.player.entityWorldX - gp.player.screenX &&
                entityWorldX - gp.tileSize < gp.player.entityWorldX + gp.player.screenX &&
                entityWorldY + gp.tileSize > gp.player.entityWorldY - gp.player.screenY &&
                entityWorldY - gp.tileSize < gp.player.entityWorldY + gp.player.screenY) {

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
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


    public BufferedImage setUpEntityTile(String imagePath) {
        ToolScaleImage scalePlayerTile = new ToolScaleImage();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = scalePlayerTile.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
