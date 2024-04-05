package model;


import ui.GamePanel;
import ui.KeyHandler;

import java.util.ArrayList;


public class BetterInv {
    ArrayList<ObjectSuper> betterInv;
    int numKeys;
    int numSwords;
    int numShields;
    int numBoots;
    int numCoins;
    int numMasterKeys;
    KeyHandler keyH;
    GamePanel gp;


    public BetterInv(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
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
            case "Master Key":
                numMasterKeys = itemCounter(newItemName);
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


    public boolean removeItems(String itemToRemove, int amountToRemove) {
        boolean success;
        int amountInInv = itemCounter(itemToRemove);
        int diff = amountInInv - amountToRemove;
        if (diff >= 0) {
            int numLeftToRemove = amountToRemove;
            ArrayList<ObjectSuper> found = new ArrayList<>();
            while (numLeftToRemove > 0) {
                for (ObjectSuper item : betterInv) {
                    if (item.name == itemToRemove) {
                        found.add(item);
                        numLeftToRemove--;
                    }
                }
            }
            betterInv.removeAll(found);
            success = true;
            return success;
        } else {
            System.out.println("you dont have enough");
            success = false;
            return success;
        }
    }

    public void updateInv() {
        boolean purchased;
        if (keyH.purchaseAttempt) {
            ObjectSuper masterKey = new ObjectMasterKey(gp);
            purchased = gp.store.buyItem(masterKey, "Key", 3);
            if (purchased) {
                for (int i = 0; i < 20; i++) {
                    gp.npc[0].setLine(2);
                    gp.npc[0].speak();
                }
                gp.gameState = gp.playState;
            } else {
                for (int i = 0; i < 20; i++) {
                    gp.npc[0].setLine(3);
                    gp.npc[0].speak();
                }
                gp.gameState = gp.playState;
            }
        } itemCounter("Key");
    }

    //GETTERS:

    public int getNumKeys() {
        return numKeys;
    }

    public int getNumMasterKeys() {
        return numMasterKeys;
    }

    public ArrayList<ObjectSuper> getInv() {
        return betterInv;
    }
}
