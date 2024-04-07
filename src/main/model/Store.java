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
        if (keyH.getSellKey() || keyH.getSellShield()
                || keyH.getSellSword() || keyH.getSellChest() || keyH.getSellBoots()) {
            if (keyH.getSellKey()) {
                gp.gameState = gp.confirmSellKeyState;
            } else if (keyH.getSellShield()) {
                gp.gameState = gp.confirmSellShieldState;
            } else if (keyH.getSellSword()) {
                gp.gameState = gp.confirmSellSwordState;
            } else if (keyH.getSellChest()) {
                gp.gameState = gp.confirmSellChestState;
            } else if (keyH.getSellBoots()) {
                gp.gameState = gp.confirmSellBootsState;
            }
        }
    }

    public void confirmSellItem(String itemName){
        if (keyH.getSellConfirmed()) {
            sellItem(itemName);
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
            } else if (!gotFunds) {
                System.out.println("that shit didnt work broke boy");
                purchased = false;
                return purchased;
            }
        }
        return purchased;
    }

    public void pickItemToSell() {
        String itemToSell = "";
        if (keyH.getSellKey() || keyH.getSellShield()
                || keyH.getSellSword() || keyH.getSellChest() || keyH.getSellBoots()) {
            if (keyH.getSellKey()) {
                itemToSell = "Key";
            } else if (keyH.getSellShield()) {
                itemToSell = "Shield";
            } else if (keyH.getSellSword()) {
                itemToSell = "Sword";
            } else if (keyH.getSellChest()) {
                itemToSell = "Chest";
            } else if (keyH.getSellBoots()) {
                itemToSell = "Boots";
            }
            if (gp.inventory.getItemInInvWithName(itemToSell) != null) {
                switch (itemToSell) {
                    case "Key":
                        sellItem("Key");
                        break;
                    case "Shield":
                        sellItem("Shield");
                        break;
                    case "Sword":
                        sellItem("Sword");
                        break;
                    case "Boots":
                        sellItem("Boots");
                        break;
                    case "Chest":
                        sellItem("Chest");
                        break;
                }
            }
        }
    }


    public void sellItem(String itemName) {
        ObjectSuper coin = new ObjectCoin(gp);
        int amountPaid;
        ObjectSuper itemInQuestion;

        itemInQuestion = gp.inventory.getItemInInvWithName(itemName);
        gp.inventory.removeOneFromInventoryWithName(itemName);
        amountPaid = itemInQuestion.valuePerItem;
        gp.inventory.addMany(coin, amountPaid);

        if (itemName.equals("Boots")) {
            gp.player.speed -= 2;
        }
    }
}


//    public void confirmSale(String itemToSell) {
//        ObjectSuper itemInQuestion = gp.inventory.getItemInInvWithName(itemToSell);
//        int valueOfItem = itemInQuestion.valuePerItem;
//        gp.ui.drawConfirmSellScreen(itemToSell, valueOfItem);
//        if (keyH.getSellConfirmed()) {
//            switch (itemToSell) {
//                case "Key":
//                    sellItem("Key");
//                    break;
//                case "Shield":
//                    sellItem("Shield");
//                    break;
//                case "Sword":
//                    sellItem("Sword");
//                    break;
//                case "Boots":
//                    sellItem("Boots");
//                    break;
//                case "Chest":
//                    sellItem("Chest");
//                    break;
//            }
//            gp.gameState = gp.sellSuccessState;
//
//        } else if (keyH.getSellCanceled()) {
//            gp.gameState = gp.sellFailState;
//        }
//    }



//             if (keyH.getSellKey()) {
//        itemToSell = "Key";
//        sellItem("Key");
//    } else if (keyH.getSellShield()) {
//        sellItem("Shield");
//    } else if (keyH.getSellSword()) {
//        sellItem("Sword");
//    } else if (keyH.getSellChest()) {
//        sellItem("Chest");
//    } else if (keyH.getSellBoots()) {
//        sellItem("Boots");
//    }



//    public String getItemBeingSold() {
//        return itemToSell;
//    }
//
//    public int getValueItemSold() {
//        ObjectSuper itemInQuestion = gp.inventory.getItemInInvWithName(itemToSell);
//        int valueOfItem = itemInQuestion.valuePerItem;
//        return valueOfItem;
//    }
