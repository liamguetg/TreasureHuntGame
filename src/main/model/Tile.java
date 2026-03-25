package model;

import java.awt.image.BufferedImage;

// Represents a Tile on the screen with a corresponding image and collision boolean (if false, the player can
// walk through/over the tile, else they can not go through the tile).
public class Tile {

    protected BufferedImage image;
    protected boolean collision = false;


    //GETTERS
    public BufferedImage getImage() {
        return image;
    }
}
