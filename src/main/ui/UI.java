package ui;

import model.ObjectKey;
import model.ObjectSuper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

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
            drawExitOption();
        }
        if (gp.gameState == gp.dialogueState) {
            drawDialogueScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.buyState) {
            drawBuyStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.buyFailState) {
            drawBuyFailState();
        }
        if (gp.gameState == gp.buyPassState) {
            drawBuyPassState();
        }
        if (gp.gameState == gp.tradeState) {
            drawSellStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.inventoryState) {
            drawInventoryScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmLoadState) {
            drawLoadScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSaveState) {
            drawSaveScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.wonState) {
            drawWonGameScreen();
        }
    }

    public void drawDialogueScreen() {
        makeDialogueWindow();
        g2.setFont(arial40);
        String text = "Would You like to:";
        int x = (gp.tileSize * 2) + gp.tileSize;
        int y = (gp.tileSize / 2) + gp.tileSize;
        g2.drawString(text, x, y);
        text =  "Buy (B)";
        y += 45;
        g2.drawString(text, x, y);
        y += 45;
        text = "Trade (T)";
        g2.drawString(text, x, y);
    }

    public void drawBuyStateScreen() {
        makeDialogueWindow();
        String text = "Buy a master Key for";
        int x = (gp.tileSize * 2) + gp.tileSize;
        int y = (gp.tileSize / 2) + gp.tileSize;
        g2.drawString(text, x, y);
        text = "3 normal keys? (Y/N)";
        y += 45;
        g2.drawString(text, x, y);
    }

    public void drawBuyFailState() {
        makeDialogueWindow();

        int x = (gp.tileSize * 2) + gp.tileSize;
        int y = (gp.tileSize / 2) + gp.tileSize;
        String text;
        text = "You dont have the funds";
        g2.drawString(text, x, y);
    }

    public void drawBuyPassState() {
        makeDialogueWindow();
        String text;
        int x = (gp.tileSize * 2) + gp.tileSize;
        int y = (gp.tileSize / 2) + gp.tileSize;
        text = "You bought the key!";
        g2.drawString(text, x, y);
    }

    public void drawSellStateScreen() {
        makeDialogueWindow();
        String text = "What would you like to sell?";
        int x = (gp.tileSize * 2) + gp.tileSize;
        int yHeader = (gp.tileSize / 2) + gp.tileSize;
        g2.drawString(text, x, yHeader);

        int row = 0;
        for (Map.Entry<ObjectSuper, Integer> entry : gp.inventory.getInventory().entrySet()) {
            text = entry.getKey().name;
            x = (gp.tileSize * 2) + gp.tileSize;
            int y = yHeader + ((1 + row) * (gp.tileSize + 2));

            g2.drawString(text, x, y);
            row++;
        }
    }

    public void drawPauseScreen() {
        drawPause();
        g2.setFont(arial40);
        int y = gp.screenHeight / 2;
        g2.drawString("SAVE (S)", getXToCenterText("SAVE (S)"), (y + 100));
        g2.drawString("LOAD (L)", getXToCenterText("LOAD (L)"), (y + 160));
    }

    public void drawPause() {
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

        int row = 0;
        for (Map.Entry<ObjectSuper, Integer> entry : gp.inventory.getInventory().entrySet()) {
            text = entry.getKey().name + ": " + entry.getValue().toString();
            x = (gp.screenWidth / 3) + 20;
            y = yHeader + ((1 + row) * (gp.tileSize + 2));

            g2.drawString(text, x, y);
            row++;
        }
    }

    public void drawLoadScreen() {
        drawWord40("Confirm Load (Y)", 0);
        drawWord40("Cancel (N)", 1);
    }

    public void drawSaveScreen() {
        drawWord40("Confirm Save (Y)", 0);
        drawWord40("Cancel (N)", 1);
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

    public void drawExitOption() {
        g2.setFont(arial20);
        String text = "Press Q to return to game";
        int y = gp.getScreenHeight() - 80;
        int x = getXToCenterText(text);
        g2.drawString(text, x, y);
    }

    public void makeDialogueWindow() {
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        makeWindow(x, y, width, height);
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

    //EFFECTS: Draws given word on given line with font arial40.
    public void drawWord40(String word, int line) {
        g2.setFont(arial40);
        int y = gp.getScreenHeight() / 2 + (40 * line);
        int x = getXToCenterText(word);
        g2.drawString(word, x, y);
    }

    public void drawWonGameScreen() {
        String text;
        int textX;
        int textY;
        g2.setFont(arial80);
        g2.setColor(Color.yellow);

        text = "You bought a house!";
        textX = getXToCenterText(text);
        textY = gp.screenHeight / 2;
        g2.drawString(text, textX, textY);

        g2.setFont(arial40);
        g2.setColor(Color.yellow);
        text = "(in this economy!?)";
        textX = getXToCenterText(text);
        textY = gp.screenHeight / 2 - (gp.tileSize * 2);
        g2.drawString(text, textX, textY);

        gp.gameThread = null;
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

