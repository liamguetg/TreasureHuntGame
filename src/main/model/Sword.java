package model;

public class Sword extends SuperItems {

    public Sword(String itemName, int valuePerItem) {
        super(itemName, valuePerItem);
        description = "A Sword, worth $" + valuePerItem + ".";
    }
}