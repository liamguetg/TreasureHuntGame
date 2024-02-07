package model;

public class Items {
    private String itemName;
    private String description;
    private int valuePerItem;
    private int totalValue;
    private int amount;

    public Items(String itemName, int amount, int valuePerItem) {
        this.itemName = itemName;
        this.valuePerItem = valuePerItem;
        this.amount = amount;
        description = "A mystery Item";
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
        return calcTotalValue();
    }

    public int getAmount() {
        return amount;
    }

    public void increaseAmount(Items newItem) {
        amount = this.amount + newItem.getAmount();
    }

    public void decreaseAmount(int amountToRemove) {
        this.amount = amount - amountToRemove;
    }


    // MODIFIES: this
    // EFFECTS: resets the amount of an item owned to 0, for when the inventory is cleared.
    public void resetItem() {
        this.amount = 1;
    }


}
