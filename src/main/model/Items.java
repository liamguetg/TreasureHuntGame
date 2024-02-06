package model;

public class Items {
    private String itemName;
    private String description;
    private int valuePerItem;
    private int totalValue;
    private int amount;

    public Items(String itemName, int valuePerItem, int amount, String description) {
        this.itemName = itemName;
        this.valuePerItem = valuePerItem;
        this.amount = amount;
        this.description = description;
    }

    //MODIFIES: totalValue
    //EFFECTS: calculates total value of an item based on amount owned
    public int calcTotalValue() {
        totalValue = valuePerItem * amount;
        return totalValue;
    }

    public String getItemName() {
        return itemName;
    }

    public int getValuePerItem() {
        return valuePerItem;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(Items newItem) {
        this.amount = amount + newItem.getAmount();
    }

    public void decreaseAmount(int amountToRemove) {
        this.amount = amount - amountToRemove;
    }

//    public void addToInventroy() {
//        for (Items item : inventory.getInventory()) {
//        }
//    }

}
