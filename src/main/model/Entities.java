package model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entities {
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
    public Rectangle solidArea;
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public int solidAreaDefaultWidth;
    public int solidAreaDefaultHeight;
    public Boolean collisionOn = false;

    
}
