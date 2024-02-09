package model;

public class Sword extends Items {

    public Sword(String itemName, int valuePerItem) {
        super(itemName, valuePerItem);
        description = "A Sword, worth $" + valuePerItem + ".";
    }
}