package ui;


import model.ObjectMasterKey;
import model.ObjectSuper;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



//Represents the keyHandlers for all the different gameStates; setting the keyBindings for each state and the
// result fo pressing the keys.

public class KeyHandler implements KeyListener {
    GamePanel gp;

    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;
    private boolean upPressed;
    private boolean sellKey;
    private boolean sellBoots;
    private boolean sellShield;
    private boolean sellSword;
    private boolean sellChest;
    private boolean purchaseAttempt;

    //EFFECTS: Constructor
    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    //EFFECTS: Built in method from the KeyListener class. Used to type something out.
    @Override
    public void keyTyped(KeyEvent e) {
    }


    //EFFECTS: Built in method from the KeyListener class. Executes function when the keys are pressed.
    @SuppressWarnings("methodlength")
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gp.gameState == gp.playState) {
            playStateKeyHandler(e);
        } else if (gp.gameState == gp.pauseState) {
            pauseStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSaveState) {
            confirmSaveStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmLoadState) {
            confirmLoadStateKeyHandler(e);
        } else if (gp.gameState == gp.dialogueState) {
            dialogueStateKeyHandler(e);
        } else if (gp.gameState == gp.buyState) {
            buyStateKeyHandler(e);
        } else if (gp.gameState == gp.tradeState) {
            tradeStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSellKeyState) {
            confirmSellKeyStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSellBootsState) {
            confirmSellBootsStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSellSwordState) {
            confirmSellSwordStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSellShieldState) {
            confirmSellShieldStateKeyHandler(e);
        } else if (gp.gameState == gp.confirmSellChestState) {
            confirmSellChestStateKeyHandler(e);
        } else if (gp.gameState == gp.inventoryState) {
            inventoryStateKeyHandler(e);
        }
        if (code == KeyEvent.VK_Q) {
            gp.gameState = gp.playState;
        }
    }

    //MODIFIES: gp.gameState
    //EFFECTS: Sets the key bindings for the paused state and the outcome if pressed.
    public void pauseStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_S) {
            gp.gameState = gp.confirmSaveState;
        }
        if (code == KeyEvent.VK_L) {
            gp.gameState = gp.confirmLoadState;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    //MODIFIES: gp.gameState
    //EFFECTS: Sets the key bindings for the confirmSave state and the outcome if pressed.
    public void confirmSaveStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.saveGame(gp.getJsonReader());
            gp.gameState = gp.pauseState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.pauseState;
        }
    }

    //MODIFIES: gp.gameState
    //EFFECTS: Sets the key bindings for the confirmLoad state and the outcome if pressed.
    public void confirmLoadStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.loadGame(gp.getJsonReader());
            gp.gameState = gp.playState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.pauseState;
        }
    }

    //MODIFIES: gp.gameState
    //EFFECTS: Sets the key bindings for the dialogue state and the outcome if pressed.
    public void dialogueStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_B) {
            gp.gameState = gp.buyState;
        }
        if (code == KeyEvent.VK_T) {
            gp.gameState = gp.tradeState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the trade state and the outcome if pressed.
    @SuppressWarnings("methodlength")
    public void tradeStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.getInv().getItemInInv("Key") != null) {
            if (code == KeyEvent.VK_K) {
                sellKey = true;
            }
        }
        if (gp.getInv().getItemInInv("Boots") != null) {
            if (code == KeyEvent.VK_B) {
                sellBoots = true;
            }
        }
        if (gp.getInv().getItemInInv("Sword") != null) {
            if (code == KeyEvent.VK_S) {
                sellSword = true;
            }
        }
        if (gp.getInv().getItemInInv("Shield") != null) {
            if (code == KeyEvent.VK_H) {
                sellShield = true;
            }
        }
        if (gp.getInv().getItemInInv("Chest") != null) {
            if (code == KeyEvent.VK_C) {
                sellChest = true;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: returns the sellItem booleans back to false (default);
    public void returnSellItemToDefault() {
        sellKey = false;
        sellBoots = false;
        sellSword = false;
        sellShield = false;
        sellChest = false;
    }


    //MODIFIES: gp.gameState, gp.store
    //EFFECTS: Sets the key bindings for the sellKey state and the outcome if pressed.
    public void confirmSellKeyStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.getStore().sellItem("Key");
            gp.gameState = gp.tradeState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.tradeState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the sellBoots state and the outcome if pressed.
    public void confirmSellBootsStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.getStore().sellItem("Boots");
            gp.gameState = gp.tradeState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.tradeState;
        }
    }


    //MODIFIES: gp.gameState
    //EFFECTS: Sets the key bindings for the inventory state and the outcome if pressed.
    public void inventoryStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.playState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the sellSword state and the outcome if pressed.
    public void confirmSellSwordStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.getStore().sellItem("Sword");
            gp.gameState = gp.tradeState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.tradeState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the sellShield state and the outcome if pressed.
    public void confirmSellShieldStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.getStore().sellItem("Shield");
            gp.gameState = gp.tradeState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.tradeState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the sellChest state and the outcome if pressed.
    public void confirmSellChestStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            gp.getStore().sellItem("Chest");
            gp.gameState = gp.tradeState;
        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.tradeState;
        }
    }

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the buy state and the outcome if pressed.
    public void buyStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        boolean purchased;

        if (code == KeyEvent.VK_Y) {
            ObjectSuper masterKey = new ObjectMasterKey(gp);
            purchased = gp.getStore().buyItem(masterKey, "Coin", 10);
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

    //MODIFIES: gp.gameState, this
    //EFFECTS: Sets the key bindings for the play state and the outcome if pressed.
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


    //EFFECTS: Built in method from the KeyListener class. Executes function when the keys are released.
    @SuppressWarnings("methodlength")
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
    }


    //GETTERS:
    public boolean getDownPressed() {
        return downPressed;
    }

    public boolean getUpPressed() {
        return upPressed;
    }

    public boolean getLeftPressed() {
        return leftPressed;
    }

    public boolean getRightPressed() {
        return rightPressed;
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
}
