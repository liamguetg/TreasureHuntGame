package ui;

import model.ObjectMasterKey;
import model.ObjectSuper;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean downPressed, leftPressed, rightPressed, upPressed;
    public boolean purchaseAttempt;
    private boolean sellKey;
    private boolean sellBoots;
    private boolean sellShield;
    private boolean sellSword;
    private boolean sellChest;
    private boolean sellConfirmed;
    boolean deBugCheckDrawTime = false;


    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            playStateKeyHandler(e);
        }
        if (gp.gameState == gp.pauseState) {
            pauseStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSaveState) {
            confirmSaveStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmLoadState) {
            confirmLoadStateKeyHandler(e);
        }
        if (gp.gameState == gp.dialogueState) {
            dialogueStateKeyHandler(e);
        }
        if (gp.gameState == gp.buyState) {
            buyStateKeyHandler(e);
        }
        if (gp.gameState == gp.buyFailState) {
        }
        if (gp.gameState == gp.tradeState) {
            tradeStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSellKeyState) {
            confirmSellKeyStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSellBootsState) {
            confirmSellBootsStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSellSwordState) {
            confirmSellSwordStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSellShieldState) {
            confirmSellShieldStateKeyHandler(e);
        }
        if (gp.gameState == gp.confirmSellChestState) {
            confirmSellChestStateKeyHandler(e);
        }
        if (code == KeyEvent.VK_Q) {
            gp.gameState = gp.playState;
        }
    }


    public void pauseStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            gp.gameState = gp.confirmSaveState;
        }
        if (code == KeyEvent.VK_L) {
            gp.gameState = gp.confirmLoadState;
        }
    }

    public void confirmSaveStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            System.out.println("Saved game asshole");
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.pauseState;
        }
    }

    public void confirmLoadStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            System.out.println("Loading game cheating asshole");
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.pauseState;
        }
    }

    public void dialogueStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_B) {
            gp.gameState = gp.buyState;
        }
        if (code == KeyEvent.VK_T) {
            gp.gameState = gp.tradeState;
        }
    }

    public void tradeStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.inventory.getItemInInvWithName("Key") != null) {
            if (code == KeyEvent.VK_K) {
                sellKey = true;
            }
        }
        if (gp.inventory.getItemInInvWithName("Boots") != null) {
            if (code == KeyEvent.VK_B) {
                sellBoots = true;
            }
        }
        if (gp.inventory.getItemInInvWithName("Sword") != null) {
            if (code == KeyEvent.VK_S) {
                sellSword = true;
            }
        }
        if (gp.inventory.getItemInInvWithName("Shield") != null) {
            if (code == KeyEvent.VK_H) {
                sellShield = true;
            }
        }
        if (gp.inventory.getItemInInvWithName("Chest") != null) {
            if (code == KeyEvent.VK_C) {
                sellChest = true;
            }
        }
    }


    public void confirmSellKeyStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.store.sellItem("Key");
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void confirmSellBootsStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.store.sellItem("Boots");
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void confirmSellSwordStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.store.sellItem("Sword");
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void confirmSellShieldStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.store.sellItem("Shield");
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void confirmSellChestStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.store.sellItem("Chest");
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }




    public void buyStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        boolean purchased;

        if (code == KeyEvent.VK_Y) {
            ObjectSuper masterKey = new ObjectMasterKey(gp);
            purchased = gp.store.buyItem(masterKey, "Coin", 10);
            if (purchased) {
                gp.gameState = gp.buyPassState;
            } else {
                gp.gameState = gp.buyFailState;
            }
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void playStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.inventoryState;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }
    }

    //DEBUG
//        if (code == KeyEvent.VK_T) {
//            if (deBugCheckDrawTime == false) {
//                deBugCheckDrawTime = true;
//            } else if (deBugCheckDrawTime == true) {
//                deBugCheckDrawTime = false;
//            }
//        }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (gp.gameState == gp.buyState) {
            if (code == KeyEvent.VK_Y) {
                purchaseAttempt = false;
            }
        }

        if (gp.gameState == gp.buyFailState) {
            if (code == KeyEvent.VK_Y) {
                purchaseAttempt = false;
            }
        }

        if (gp.gameState == gp.tradeState) {
            if (code == KeyEvent.VK_K) {
                sellKey = false;
            }
            if (code == KeyEvent.VK_B) {
                sellBoots = false;
            }
            if (code == KeyEvent.VK_S) {
                sellSword = false;
            }
            if (code == KeyEvent.VK_H) {
                sellShield = false;
            }
            if (code == KeyEvent.VK_C) {
                sellChest = false;
            }
        }
//        if (gp.gameState == gp.confirmSellState) {
//            if (code == KeyEvent.VK_Y) {
//                sellConfirmed = false;
//            }
//            if (code == KeyEvent.VK_N) {
//                sellCanceled = false;
//            }
//        }
    }

    public boolean getSellKey() {
        return sellKey;
    }
    public boolean getSellBoots() {
        return sellBoots;
    }
    public boolean getSellSword() {
        return sellSword;
    }
    public boolean getSellShield() {
        return sellShield;
    }
    public boolean getSellChest() {
        return sellChest;
    }
    public boolean getSellConfirmed() {
        return sellConfirmed;
    }
//    public boolean getSellCanceled() {
//        return sellCanceled;
//    }
}
