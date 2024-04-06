package model;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectSuper {

    GamePanel gp;
    public BufferedImage img;
    public String name;
    public Boolean collision = false;
    public int worldX;
    public int worldY;
    public int valuePerItem;

    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int solidAreaDefaultWidth = 48;
    public int solidAreaDefaultHeight = 48;
    public Rectangle solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY,
            solidAreaDefaultWidth, solidAreaDefaultHeight); //0, 0, 48, 48

    ToolScaleImage scaleObject = new ToolScaleImage();

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.player.entityWorldX + gp.player.screenX;
        int screenY = worldY - gp.player.entityWorldY + gp.player.screenY;

        // if statement so we only draws the necessary tiles (the tiles surrounding the player)
        if (worldX + gp.tileSize > gp.player.entityWorldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.entityWorldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.entityWorldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.entityWorldY + gp.player.screenY) {

            g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }
}