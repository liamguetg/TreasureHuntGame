package model;

public class Coins extends SuperItems {

    public Coins(String itemName, int valuePerItem) {
        super(itemName, valuePerItem);
        description = "A coin, worth $1. Can be used to purchase items.";
    }
}
