package model;

import java.awt.image.BufferedImage;

public class SuperItems {
    public BufferedImage img;
    public String itemName;
    public Boolean collision = false;
    public int worldX;
    public int worldY;

    protected String description;
    protected int valuePerItem;

    public SuperItems(String itemName, int valuePerItem) {
        this.itemName = itemName;
        this.valuePerItem = valuePerItem;
        description = "A mystery Item";
    }


    public String getItemName() {
        return itemName;
    }

    public int getValuePerItem() {
        return valuePerItem;
    }

}
