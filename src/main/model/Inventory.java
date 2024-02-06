package model;

import java.util.LinkedList;


// Represents a players Inventory, with their picked up items;
public class Inventory {
    public LinkedList<Items> inventory;

    //EFFECTS:
    public Inventory() {
        inventory = new LinkedList<Items>();
    }

    //MODIFIED: this
    // EFFECTS: Adds a new item to the inventory, if item type is already owned, increase amount owned.
    public void addToInventory(Items newItem) {
        for(Items item : inventory) {
            if (newItem.getItemName().equals(item.getItemName())) {
                item.increaseAmount(newItem);
            } else {
                inventory.add(newItem);
            }
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
}
