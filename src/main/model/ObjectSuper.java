package model;

import ui.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

// A Superclass for all objects. Objects have solid areas (Hit boxes), names, values,
// x- and y- coordinates on the map and an associated image.
public class ObjectSuper {

    GamePanel gp;
    protected BufferedImage img;
    protected String name;
    protected Boolean collision = false;
    protected int worldX;
    protected int worldY;
    protected int valuePerItem;

    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;
    protected int solidAreaDefaultWidth = 48;
    protected int solidAreaDefaultHeight = 48;
    protected Rectangle solidArea = new Rectangle(solidAreaDefaultX, solidAreaDefaultY,
            solidAreaDefaultWidth, solidAreaDefaultHeight); //0, 0, 48, 48

    ToolScaleImage scaleObject = new ToolScaleImage();


    //MODIFIES: this
    //EFFECTS: Draws only the visible (on screen) Objects onto the GamePanel screen.
    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX - gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX();
        int screenY = worldY - gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY();

        // To only draw the visible (on screen) tiles:
        if (worldX + gp.getTileSize() > gp.getPlayer().entityWorldX - gp.getPlayer().getScreenX()
                && worldX - gp.getTileSize() < gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX()
                && worldY + gp.getTileSize() > gp.getPlayer().entityWorldY - gp.getPlayer().getScreenY()
                && worldY - gp.getTileSize() < gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY()) {

            g2.drawImage(img, screenX, screenY, gp.getTileSize(), gp.getTileSize(), null);
        }
    }

    //GETTERS:

    public Image getImg() {
        return img;
    }

    //GETTERS
    public String getName() {
        return name;
    }
}