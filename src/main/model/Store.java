package model;

import ui.GamePanel;
import ui.KeyHandler;

//Represents the store class, can buy items with coin and sell items in inventory for coins.
public class Store {
    GamePanel gp;
    KeyHandler keyH;

    //EFFECTS: Constructor
    public Store(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    //MODIFIES: gameState
    //EFFECTS: Updates the storeState based on what item player chose to sell.
    public void update() {
        if (keyH.getSellKey() || keyH.getSellShield()
                || keyH.getSellSword() || keyH.getSellChest() || keyH.getSellBoots()) {
            if (keyH.getSellKey()) {
                gp.setGameState(gp.confirmSellKeyState);
            } else if (keyH.getSellShield()) {
                gp.setGameState(gp.confirmSellShieldState);
            } else if (keyH.getSellSword()) {
                gp.setGameState(gp.confirmSellSwordState);
            } else if (keyH.getSellChest()) {
                gp.setGameState(gp.confirmSellChestState);
            } else if (keyH.getSellBoots()) {
                gp.setGameState(gp.confirmSellBootsState);
            }
        }
        keyH.returnSellItemToDefault();
    }

    //MODIFIES: inventory
    //EFFECTS: "Buys" an item by adding to inventory and removing the corresponding price (amount of coins).
    public boolean buyItem(ObjectSuper itemToGet, String itemToTradeName, int amountNeeded) {
        boolean gotFunds;
        boolean purchased = false;
        ObjectSuper itemToTrade = gp.getInv().getItemInInv(itemToTradeName);

        if (itemToTrade != null) {
            gotFunds = gp.getInv().removeItemFromInv(itemToTradeName, amountNeeded);
            if (gotFunds) {
                gp.getInv().addToInv(itemToGet, 1);
                gp.getInv().removeItemFromInv(itemToTradeName, amountNeeded);
                purchased = true;
                return purchased;
            }
        }
        return purchased;
    }

    //MODIFIES: inventory
    //EFFECTS: "Sells" item by removing it from inventory and adding corresponding amount of coins.
    public void sellItem(String itemName) {
        ObjectSuper coin = new ObjectCoin(gp);
        int amountPaid;
        ObjectSuper itemInQuestion;

        itemInQuestion = gp.getInv().getItemInInv(itemName);
        gp.getInv().removeItemFromInv(itemName, 1);
        amountPaid = itemInQuestion.valuePerItem;
        gp.getInv().addToInv(coin, amountPaid);

        if (itemName.equals("Boots")) {
            gp.getPlayer().speed -= 2;
        }
    }
}


