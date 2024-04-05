package model;


import java.util.ArrayList;


public class BetterInv {
    ArrayList<ObjectSuper> betterInv;
    int numKeys;
    int numSwords;
    int numShields;
    int numBoots;
    int numCoins;

    public BetterInv() {
        betterInv = new ArrayList<ObjectSuper>();
    }

   public void addItem(ObjectSuper newItem) {
        betterInv.add(newItem);
        itemCount(newItem);
   }

    //MODIFIES: this
    //EFFECTS: (Re)counts the number of each item with the newly added item.
    public void itemCount(ObjectSuper newItem) {
        String newItemName = newItem.name;
        switch (newItemName) {
            case "Key":
                numKeys = itemCounter(newItemName);
                break;
            case "Sword":
                numSwords = itemCounter(newItemName);
                break;
            case "Shield":
                numShields = itemCounter(newItemName);
                break;
            case "Boots":
                numBoots = itemCounter(newItemName);
                break;
            case "Coin":
                numCoins = itemCounter(newItemName);
                break;
        }
    }

    //EFFECTS: counts the number of the given item type in player inventory.
    public int itemCounter(String newItemName) {
        int numItem = 0;
        for (ObjectSuper item : betterInv) {
            if (newItemName == item.name) {
                numItem++;
            }
        }
        return numItem;
    }

    //GETTERS:

    public int getNumKeys() {
        return numKeys;
    }
}
