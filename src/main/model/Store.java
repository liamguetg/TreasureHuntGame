package model;

import ui.GamePanel;
import ui.KeyHandler;

public class Store {
    GamePanel gp;
    KeyHandler keyH;

    public Store(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }

    public void update() {
        boolean bought;
        if (keyH.purchaseAttempt) {
            ObjectSuper masterKey = new ObjectMasterKey(gp);
            bought = buyItem(masterKey, "Key", 3);
        }
    }

    public boolean buyItem(ObjectSuper itemToGet, String itemToTradeName, int amountNeeded) {
        boolean gotFunds;
        boolean purchased = false;
        ObjectSuper itemToTrade = gp.inventory.getItemInInvWithName(itemToTradeName);

        if (itemToTrade != null) {
            gotFunds = gp.inventory.removeFromInventory(itemToTrade, amountNeeded);
            if (gotFunds) {
                gp.inventory.addToInventory(itemToGet);
                gp.inventory.removeFromInventory(itemToTrade, amountNeeded);
                purchased = true;
                return purchased;
            } else if (!gotFunds){
                System.out.println("that shit didnt work broke boy");
                purchased = false;
                return purchased;
            }
        }
        return purchased;
    }
}
