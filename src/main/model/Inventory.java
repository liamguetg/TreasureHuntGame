package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


// Represents a players Inventory, with their picked up items;
public class Inventory {
    public LinkedList<Items> inventory;
    private Map<String, Integer> inventoryCount;

    //EFFECTS: constructs an instance of an inventory with no contents
    public Inventory() {
        inventory = new LinkedList<>();
        inventoryCount = new HashMap<>();
    }

    // EFFECTS: returns the item in inventory whose name matches the new item.
    public Items getItemInInv(Items newItem) {
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
        if (getItemInInv(newItem) == null) {
            inventory.add(newItem);
            inventoryCount.put(newItem.getItemName(), 1);
        } else {
            increaseAmountOfItem(newItem);
        }
    }

    //MODIFIES: this
    //EFFECTS: increases the amount (value) of item in hashmap
    public void increaseAmountOfItem(Items item) {
        String itemName = item.getItemName();
        inventoryCount.put(itemName, inventoryCount.get(itemName) + 1);
    }


    //REQUIRES: item is owned. amount to remove <= amount owned
    //MODIFIES: this
    //EFFECTS: removes item(s) from list
    public void removeFromInventory(Items itemToRemove, int amountToRemove) {
        String itemToRemoveName = itemToRemove.getItemName();
        int amountOfItemInInv = inventoryCount.get(itemToRemoveName);
        int amountLeft = amountOfItemInInv - amountToRemove;

        if (amountLeft < 0) {
            System.out.println("You don't have enough " + itemToRemoveName + "s");
        } else if (amountLeft == 0) {
            inventory.remove(getItemInInv(itemToRemove));
            inventoryCount.remove(itemToRemoveName);
        } else {
            inventoryCount.put(itemToRemoveName, amountLeft);
        }
    }

    //MODIFIES: this
    //EFFECTS: clears the inventory and resets the amount of each item to 0
    public void clearInventory() {
        inventory.clear();
        inventoryCount.clear();
    }

    //EFFECTS: true if inventory is empty
    public boolean inventoryEmpty() {
        return inventory.size() == 0;
    }

    //EFFECTS: true if inventoryCount is empty
    public boolean inventoryCountEmpty() {
        return inventoryCount.size() == 0;
    }

    // GETTERS

    // EFFECTS: returns the full inventory
    public LinkedList<Items> getInventory() {
        return inventory;
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the amount of an item in inventory
    public int getAmountOfItemInInv(Items itemInInv) {
        if (getItemInInv(itemInInv) == null) {
            return 0;
        } else {
            String itemName = (getItemInInv(itemInInv)).getItemName();
            return inventoryCount.get(itemName);
        }
    }

    // EFFECTS: returns the number of items in inventory
    public int getInventorySize() {
        return inventory.size();
    }
    // EFFECTS: returns the number of items in inventory
    public int getInventoryCountSize() {
        return inventoryCount.size();
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the value of an item in inventory (value per item)
    public int getValueOfItemInInv(Items itemInInv) {
        return itemInInv.getValuePerItem();
//        return (inventory.get(inventory.indexOf(itemInInv))).getValuePerItem();
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the total value of an item in inventory (value of all the same items combined)
    public int getTotValueOfItemInInv(Items itemInInv) {
        return getValueOfItemInInv(itemInInv) * inventoryCount.get(itemInInv.getItemName());
    }
}
