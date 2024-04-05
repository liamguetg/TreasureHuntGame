package ui;

import model.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    GamePanel gp;
    Graphics2D g2;

    Font arial40;
    Font arial80;
    Font arial20;

    BufferedImage keyImage;
    public Boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public Boolean gameDone = false;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        ObjectKey objectKey = new ObjectKey(gp);
        keyImage = objectKey.img;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial40);
        g2.setColor(Color.white);

        if (gp.gameState == gp.playState) {
            //play
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
        }
        if (gp.gameState == gp.inventoryState) {
            drawInventoryScreen();
        }
    }

    public void drawDialogueScreen() {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        makeWindow(x, y, width, height);

        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString(currentDialogue, x, y);
    }

    public void drawPauseScreen() {
        g2.setFont(arial80);
        String text = "PAUSED";
        int y = gp.screenHeight / 2;
        int x = getXToCenterText(text);
        g2.drawString(text, x, y);
    }

    public void drawInventoryScreen() {
        int x = (gp.screenWidth / 3);
        int y = 50;
        int width = gp.screenWidth / 3;
        int height = gp.screenHeight - 100;
        makeWindow(x, y, width, height);

        g2.setFont(arial40);
        g2.setColor(Color.white);
        String text = "Inventory:";
        int yHeader = y + gp.tileSize;
        int xHeader = getCenterTextWindow(text, width) + x;
        g2.drawString(text, xHeader, yHeader);

        g2.setFont(arial40);
        g2.setColor(Color.white);
        text = "Keys: " + String.valueOf(gp.betterInv.getNumKeys());
        x = width + gp.tileSize;
        y = yHeader + 48;
        g2.drawString(text, x, y);


    }

    public void makeWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); //(Black) Fourth number controls the opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 20, 20);

        g2.setColor(Color.white);
        BasicStroke bStroke = new BasicStroke(5);
        g2.setStroke(bStroke);
        g2.drawRoundRect(x, y, width, height, 20, 20);
    }

    public int getXToCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public int getCenterTextWindow(String text, int windowWidth) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = windowWidth / 2 - length / 2;
        return x;
    }

}


//    public void showMessage(String text) {
//        message = text;
//        messageOn = true;
//    }

//    public void oldDraw(Graphics2D g2) {
//        if (gameDone == true) {
//            String text;
//            int textLength;
//            int textX;
//            int textY;
//            g2.setFont(arial40);
//            g2.setColor(Color.white);
//
//            text = "You found the treasure";
//            textX = getXForCenterText(text);
//            textY = gp.screenHeight/2;
//            g2.drawString(text, textX, textY);
//
//            g2.setFont(arial80);
//            g2.setColor(Color.yellow);
//            text = "CONGRATS";
//            textX = getXForCenterText(text);
//            textY = gp.screenHeight/2 - (gp.tileSize *2);
//            g2.drawString(text, textX, textY);
//
//            gp.gameThread = null;
//
//        } else {
//            g2.setFont(arial40);
//            g2.setColor(Color.white);
//            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
//            g2.drawString("x " + gp.player.hasKey, 74, 65);
//
//
//            if (messageOn == true) {
//                // A way to use the current font but change the size
//                // Instead of making a new instance
//                // (ie instead of using g2.setFont(arial30));
//                g2.setFont(g2.getFont().deriveFont(30F));
//                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 12);
//                messageCounter++;
//
//                //
//                if (messageCounter > 120) { //120 frames (we are at 60 fps -> message displayed for 2 sec)
//                    messageCounter = 0;
//                    messageOn = false;
//                }
//            }
//        }
//    }

