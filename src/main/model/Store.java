package model;

import ui.GamePanel;

public class Store {
    GamePanel gp;

    public Store(GamePanel gp){
        this.gp = gp;
    }

    public boolean buyItem(ObjectSuper itemToGet, String itemToTrade, int amountNeeded) {
        boolean gotFunds;
        boolean purchased;
        gotFunds = gp.betterInv.removeItems(itemToTrade, amountNeeded);
        if (gotFunds) {
            gp.betterInv.addItem(itemToGet);
            gp.betterInv.removeItems("Key", 3);
            purchased = true;
            return purchased;
        } else {
            System.out.println("that shit didnt work broke boy");
            purchased = false;
            return purchased;
        }
    }
}
