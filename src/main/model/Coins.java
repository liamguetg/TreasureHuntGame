package model;

public class Coins extends Items {
    private String description;
    private String itemName;
    private int valuePerItem;
    private int amount;

    public Coins(String itemName, int amount, int valuePerItem) {
        super(itemName, amount, valuePerItem);
        this.itemName = "Coin";
        description = "A coin, worth $1. Can be used to purchase items.";
        this.amount = amount;
        this.valuePerItem = 1;

    }
}
