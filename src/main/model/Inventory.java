package model;

import java.util.LinkedList;


// Represents a players Inventory, with their picked up items;
public class Inventory {
    public LinkedList<Items> inventory;

    //EFFECTS:
    public Inventory() {
        inventory = new LinkedList<Items>();
    }

    public boolean isEmpty() {
        return (inventory.size() == 0);
    }

    public Items alreadyContained(Items newItem) {
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
        if (alreadyContained(newItem) == null) {
            inventory.add(newItem);
        } else {
            alreadyContained(newItem).increaseAmount(newItem);
        }
    }

    //REQUIRES: item is owned. amount to remove <= amount owned
    //MODIFIES: this
    //EFFECTS: removes item(s) from list
    public void removeFromInventory(Items itemToRemove, int amountToRemove) {
        int amountOfItemBeingRemoved;
        int amountLeft;
        int indexOfItemBeingRemoved = inventory.indexOf(itemToRemove);
        Items itemBeingRemoved = inventory.get(indexOfItemBeingRemoved);
        amountOfItemBeingRemoved = itemBeingRemoved.getAmount();
        amountLeft = amountOfItemBeingRemoved - amountToRemove;
        if (amountLeft == 0) {
            inventory.remove(indexOfItemBeingRemoved);
        } else {
            itemBeingRemoved.decreaseAmount(amountToRemove);
        }
    }

    public LinkedList<Items> getInventory() {
        return inventory;
    }

    public int inventorySize() {
        return inventory.size();
    }
}
