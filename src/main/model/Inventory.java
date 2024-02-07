package model;

import java.util.LinkedList;
import java.util.Map;


// Represents a players Inventory, with their picked up items;
public class Inventory {
    public LinkedList<Items> inventory;
    private Map<String, Integer> Items;

    //EFFECTS: constructs an instance of an inventory with no contents
    public Inventory() {
        inventory = new LinkedList<>();
    }

    // EFFECTS: returns the item in inventory whose name matches the new item.
    public Items itemInInv(Items newItem) {
        for (Items item : inventory) {
            if (newItem.getItemName().equals(item.getItemName())) {
                return item;
            }
        }
        return null;
    }

    //MODIFIED: this
    // EFFECTS: Adds a new item to the inventory, if item type is already owned, increase amount owned.
    public void addToInventory(Items newItem) {
        if (itemInInv(newItem) == null) {
            inventory.add(newItem);
        } else {
            itemInInv(newItem).increaseAmount(newItem);
        }
    }

    //REQUIRES: item is owned. amount to remove <= amount owned
    //MODIFIES: this
    //EFFECTS: removes item(s) from list
    public void removeFromInventory(Items itemToRemove, int amountToRemove) {
        Items itemBeingRemoved;
        int amountOfItemBeingRemoved;
        itemBeingRemoved = itemInInv(itemToRemove);
        amountOfItemBeingRemoved = itemBeingRemoved.getAmount();
        int amountLeft;
        amountLeft = amountOfItemBeingRemoved - amountToRemove;
        int indexOfItemBeingRemoved = inventory.indexOf(itemBeingRemoved);

        if (amountLeft == 0) {
            itemBeingRemoved.resetItem();
            inventory.remove(indexOfItemBeingRemoved);
        } else {
            itemBeingRemoved.decreaseAmount(amountToRemove);
        }
    }


    //MODIFIES: this
    //EFFECTS: clears the inventory and resets the amount of each item to 0
    public void clearInventory() {
        for (Items item : inventory) {
            item.resetItem();
        }
        inventory.clear();
    }

    // EFFECTS: returns true if inventory is empty (size == 0)
    public boolean isEmpty() {
        return (inventory.size() == 0);
    }

    // GETTERS

    // EFFECTS: returns the full inventory
    public LinkedList<Items> getInventory() {
        return inventory;
    }

    // EFFECTS: returns the number of items in inventory
    public int getInventorySize() {
        return inventory.size();
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the amount of an item in inventory
    public int getAmountOfItemInInv(Items itemInInv) {
        for (Items item : inventory) {
            if (itemInInv.getItemName().equals(item.getItemName())) {
                return item.getAmount();
            }
        } return 0;
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the value of an item in inventory (value per item)
    public int getValueOfItemInInv(Items itemInInv) {
        for (Items item : inventory) {
            if (itemInInv.getItemName().equals(item.getItemName())) {
                return item.getValuePerItem();
            }
        } return -1;
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the total value of an item in inventory (value of all the same items combined)
    public int getTotValueOfItemInInv(Items itemInInv) {
        for (Items item : inventory) {
            if (itemInInv.getItemName().equals(item.getItemName())) {
                return item.getTotalValue();
            }
        } return -1;
    }
}
