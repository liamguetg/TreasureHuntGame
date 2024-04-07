package model;

import ui.GamePanel;
import ui.KeyHandler;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


// Represents a players Inventory, with their picked up items;
public class Inventory {
    GamePanel gp;
    KeyHandler keyH;
    private final Map<ObjectSuper, Integer> inventory;

    //EFFECTS: constructs an instance of an inventory with no contents
    public Inventory(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        inventory = new HashMap<>();
    }

    //EFFECTS: returns the item in inventory whose name matches the new item.
    public ObjectSuper getItemInInv(ObjectSuper newItem) {
        for (HashMap.Entry<ObjectSuper, Integer> entry : inventory.entrySet()) {
            ObjectSuper key = entry.getKey();
            String keyName = key.name;
            if (keyName.equals(newItem.name)) {
                return key;
            }
        }
        return null;
    }

    //EFFECTS: checks if item with given name is in inventory.
    public boolean haveItem(String itemName) {
        boolean gotIt = false;
        for (HashMap.Entry<ObjectSuper, Integer> entry : inventory.entrySet()) {
            ObjectSuper key = entry.getKey();
            String keyName = key.name;
            if (keyName.equals(itemName)) {
                gotIt = true;
                return gotIt;
            }
        }
        return gotIt;
    }

    public ObjectSuper getItemInInvWithName(String itemName) {
        for (HashMap.Entry<ObjectSuper, Integer> entry : inventory.entrySet()) {
            ObjectSuper key = entry.getKey();
            String keyName = key.name;
            if (keyName.equals(itemName)) {
                return key;
            }
        }
        return null;
    }

    //MODIFIED: this
    // EFFECTS: Adds a new item to the inventory, if item type is already owned, increase amount owned.
    public void addToInventory(ObjectSuper newItem) {
        if (getItemInInv(newItem) == null) {
            inventory.put(newItem, 1);
        } else {
            increaseAmountOfItem(getItemInInv(newItem));
        }
    }

    //MODIFIES: this
    //EFFECTS: increases the amount (value) of item in hashmap
    public void increaseAmountOfItem(ObjectSuper item) {
        int currentAmount = inventory.get(item);
        int oneMore = currentAmount + 1;
        inventory.put(item, oneMore);
    }

    public void increaseItemAmountBy(ObjectSuper item, int amountToAdd) {
        int currentAmount = inventory.get(item);
        int newAmount = currentAmount + amountToAdd;
        inventory.put(item, newAmount);
    }


    public boolean removeFromInventoryWithName(String itemToRemoveName, int amountToRemove) {
        int amountOfItemInInv;
        int amountLeft;
        boolean gotFunds;
        ObjectSuper itemInQuestion = getItemInInvWithName(itemToRemoveName);

        if (itemInQuestion == null) {
            amountOfItemInInv = 0;
            gotFunds = false;
            System.out.println("You dont have any " + itemToRemoveName + " in your inventory.");
            return gotFunds;
        } else {
            amountOfItemInInv = getAmountOfItemInInv(itemInQuestion);
            amountLeft = amountOfItemInInv - amountToRemove;
            if (amountLeft < 0) {
                gotFunds = false;
                System.out.println("You don't have enough " + itemToRemoveName + "s");
                return gotFunds;
            } else if (amountLeft == 0) {
                inventory.remove(itemInQuestion);
                gotFunds = true;
                System.out.println("You got rid of all your " + itemToRemoveName);
                return gotFunds;
            } else {
                inventory.put(itemInQuestion, amountLeft);
                gotFunds = true;
                System.out.println("You have " + amountLeft + " " + itemToRemoveName + "'s left.");
                return gotFunds;
            }
        }
    }

    public void addMany(ObjectSuper itemToAdd, int amount) {
        String itemName = itemToAdd.name;
        ObjectSuper itemInQuestion = getItemInInvWithName(itemName);
        if (getItemInInv(itemToAdd) == null) {
            inventory.put(itemToAdd, amount);
        } else {
            increaseItemAmountBy(itemInQuestion, amount);
        }
    }

    public boolean removeOneFromInventoryWithName(String itemToRemoveName) {
        int amountOfItemInInv;
        int amountLeft;
        boolean gotFunds;
        ObjectSuper itemInQuestion = getItemInInvWithName(itemToRemoveName);

        if (!haveItem(itemToRemoveName)) {
            amountOfItemInInv = 0;
            gotFunds = false;
            System.out.println("You dont have any " + itemToRemoveName + " in your inventory.");
        } else {
            amountOfItemInInv = inventory.get(itemInQuestion);
            amountLeft = amountOfItemInInv - 1;
            if (amountLeft == 0) {
                inventory.remove(itemInQuestion);
                gotFunds = true;
                System.out.println("You got rid of all your " + itemToRemoveName);
            } else {
                inventory.put(itemInQuestion, amountLeft);
                gotFunds = true;
                System.out.println("You have " + amountLeft + " " + itemToRemoveName + "'s left.");
            }
        }
        return gotFunds;
    }



    //MODIFIES: this
    //EFFECTS: removes item(s) from list
    public boolean removeFromInventory(ObjectSuper itemToRemove, int amountToRemove) {
        String itemToRemoveName = itemToRemove.name;
        int amountOfItemInInv;
        int amountLeft;
        boolean gotFunds;

        if (!inventory.containsKey(itemToRemove)) {
            amountOfItemInInv = 0;
            gotFunds = false;
            System.out.println("You dont have any " + itemToRemoveName + " in your inventory.");

        } else {
            amountOfItemInInv = inventory.get(itemToRemove);
            amountLeft = amountOfItemInInv - amountToRemove;
            if (amountLeft < 0) {
                gotFunds = false;
                System.out.println("You don't have enough " + itemToRemoveName + "s");
            } else if (amountLeft == 0) {
                inventory.remove(itemToRemove);
                gotFunds = true;
                System.out.println("You got rid of all your " + itemToRemoveName);
            } else {
                inventory.put(itemToRemove, amountLeft);
                gotFunds = true;
                System.out.println("You have " + amountLeft + " " + itemToRemoveName + "'s left.");
            }
        }
        return gotFunds;
    }


    //MODIFIES: this
    //EFFECTS: clears the inventory and resets the amount of each item to 0
    public void clearInventory() {
        inventory.clear();
    }

    //EFFECTS: true if inventory is empty
    public boolean inventoryEmpty() {
        return inventory.size() == 0;
    }

    // GETTERS

    public Map<ObjectSuper, Integer> getInventory() {
        return inventory;
    }

    public int getAmountOfItemInInvWithName(String itemInInvName) {
        if (getItemInInvWithName(itemInInvName) == null) {
            return 0;
        } else {
            return inventory.get(itemInInvName);
        }
    }

    public int getAmountOfItemInInv(ObjectSuper itemInInv) {
        if (getItemInInv(itemInInv) == null) {
            return 0;
        } else {
            return inventory.get(itemInInv);
        }
    }

    // EFFECTS: returns the number of items in inventory
    public int getInventorySize() {
        return inventory.size();
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the value of an item in inventory (value per item)
    public int getValueOfItemInInv(ObjectSuper itemInInv) {
        return itemInInv.valuePerItem;
    }

    // REQUIRES: item is present in inventory.
    // EFFECTS: gets the total value of an item in inventory (value of all the same items combined)
    public int getTotValueOfItemInInv(ObjectSuper itemInInv) {
        return getValueOfItemInInv(itemInInv) * getAmountOfItemInInv(itemInInv);
    }

    public JTable inventoryAsTable() {
        JTable invAsTable = new JTable(inventory.size(), 2);
        int row = 0;
        for (Map.Entry<ObjectSuper, Integer> entry : inventory.entrySet()) {
            invAsTable.setValueAt(entry.getKey().name, row, 0);
            invAsTable.setValueAt(entry.getValue().toString(), row, 1);
            row++;
        }
        return invAsTable;
    }
}


