package ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean downPressed, leftPressed, rightPressed, upPressed;
    public boolean purchaseAttempt;
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
        if (gp.gameState == gp.storeState) {
            storeStateKeyHandler(e);
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
        if (code == KeyEvent.VK_Y) {
            gp.gameState = gp.storeState;

        }
        if (code == KeyEvent.VK_N) {
            gp.gameState = gp.playState;
        }
    }

    public void storeStateKeyHandler(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Y) {
            purchaseAttempt = true;
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
    }
}
