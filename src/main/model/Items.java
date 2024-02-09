package model;

public class Items {
    protected String itemName;
    protected String description;
    protected int valuePerItem;

    public Items(String itemName, int valuePerItem) {
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
