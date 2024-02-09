package model;

public class Coins extends Items {

    public Coins(String itemName, int valuePerItem) {
        super(itemName, valuePerItem);
        description = "A coin, worth $1. Can be used to purchase items.";
    }
}
