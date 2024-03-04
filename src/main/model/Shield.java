package model;

public class Shield extends SuperItems {

    public Shield(String itemName, int valuePerItem) {
        super(itemName, valuePerItem);
        description = "A coin, worth $1. Can be used to purchase items.";
    }
}