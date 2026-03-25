package ui;

import model.ObjectKey;
import model.ObjectSuper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

//Represents the different visible UI screens that appear with the different gameStates.
public class UI {

    GamePanel gp;
    Graphics2D g2;

    Font arial40;
    Font arial80;
    Font arial20;
    BufferedImage keyImage;

    //EFFECTS: constructor
    public UI(GamePanel gp) {
        this.gp = gp;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        ObjectKey objectKey = new ObjectKey(gp);
        keyImage = (BufferedImage) objectKey.getImg();
    }

    //EFFECTS: Selects which screen tp draw based on the gameState
    @SuppressWarnings("methodlength")
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial20);
        g2.setColor(Color.white);

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
            drawExitOption();
        }
        if (gp.gameState == gp.buyPassState) {
            drawBuyPassState();
            drawExitOption();
        }
        if (gp.gameState == gp.tradeState) {
            drawTradeStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSellKeyState) {
            drawConfirmSellKeyStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSellBootsState) {
            drawConfirmSellBootsStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSellSwordState) {
            drawConfirmSellSwordStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSellShieldState) {
            drawConfirmSellShieldStateScreen();
            drawExitOption();
        }
        if (gp.gameState == gp.confirmSellChestState) {
            drawConfirmSellChestStateScreen();
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

    //EFFECTS: Draws the dialogue screen.
    public void drawDialogueScreen() {
        makeDialogueWindow();
        g2.setFont(arial20);
        String text = "Would You like to:";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
        text = "Buy (B)";
        y += 45;
        g2.drawString(text, x, y);
        y += 45;
        text = "Trade (T)";
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the buyState screen.
    public void drawBuyStateScreen() {
        makeDialogueWindow();
        String text = "Buy a master Key for 10 coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the failed buy state screen.
    public void drawBuyFailState() {
        makeDialogueWindow();
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        String text;
        text = "You dont have the funds";
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the successful buy state screen.
    public void drawBuyPassState() {
        makeDialogueWindow();
        String text;
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        text = "You bought the key!";
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the trade screen.
    public void drawTradeStateScreen() {
        makeTradeWindow();

        String text = "What would you like to sell?";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int yheader = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, yheader);

        int row = 0;
        for (Map.Entry<ObjectSuper, Integer> entry : gp.getInv().getInventory().entrySet()) {
            String itemName = entry.getKey().getName();
            String command = findCommandToDisplay(itemName);
            text = entry.getKey().getName() + " " + command + "\t " + entry.getValue().toString();
            x = (gp.getTileSize() * 2) + gp.getTileSize();
            int y = yheader + ((1 + row) * (gp.getTileSize() + 2));
            if (!itemName.equals("Coin")) {
                g2.drawString(text, x, y);
            }
            row++;
        }
    }

    //EFFECTS: finds which commands to display on the trade screen based on what players has
    // in their inventory.
    public String findCommandToDisplay(String itemName) {
        String command = "(Can't sell)";
        switch (itemName) {
            case "Key":
                command = "(K)";
                break;
            case "Sword":
                command = "(S)";
                break;
            case "Shield":
                command = "(H)";
                break;
            case "Boots":
                command = "(B)";
                break;
            case "Chest":
                command = "(C)";
                break;
        }
        return command;
    }

    //EFFECTS: Draws the Confirm sell key screen.
    public void drawConfirmSellKeyStateScreen() {
        makeDialogueWindow();
        String text = "Sell Key for 1 Coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the Confirm sell Boots screen.
    public void drawConfirmSellBootsStateScreen() {
        makeDialogueWindow();
        String text = "Sell Boots for 10 Coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the Confirm sell Sword screen.
    public void drawConfirmSellSwordStateScreen() {
        makeDialogueWindow();
        String text = "Sell Sword for 3 Coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the Confirm sell Shield screen.
    public void drawConfirmSellShieldStateScreen() {
        makeDialogueWindow();
        String text = "Sell Shield for 2 Coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the Confirm sell chest screen.
    public void drawConfirmSellChestStateScreen() {
        makeDialogueWindow();
        String text = "Sell Chest for 15 Coin? (Y/N)";
        int x = (gp.getTileSize() * 2) + gp.getTileSize();
        int y = (gp.getTileSize() / 2) + gp.getTileSize();
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the Pause state screen.
    public void drawPauseScreen() {
        drawPause();
        g2.setFont(arial40);
        int y = gp.getScreenHeight() / 2;
        g2.drawString("SAVE (S)", getXToCenterText("SAVE (S)"), (y + 100));
        g2.drawString("LOAD (L)", getXToCenterText("LOAD (L)"), (y + 160));
    }

    //EFFECTS: Draws Pause on the screen.
    public void drawPause() {
        g2.setFont(arial80);
        String text = "PAUSED";
        int y = gp.getScreenHeight() / 2;
        int x = getXToCenterText(text);
        g2.drawString(text, x, y);
    }

    //EFFECTS: Draws the inventory screen, shows what items and how much of each the player has.
    public void drawInventoryScreen() {
        int x = (gp.getScreenWidth() / 3);
        int y = 50;
        int width = gp.getScreenWidth() / 3;
        int height = gp.getScreenHeight() - 100;
        makeWindow(x, y, width, height);

        g2.setFont(arial20);
        g2.setColor(Color.white);
        String text = "Inventory:";
        int yheader = y + gp.getTileSize();
        int xheader = getCenterTextWindow(text, width) + x;
        g2.drawString(text, xheader, yheader);

        int row = 0;
        for (Map.Entry<ObjectSuper, Integer> entry : gp.getInv().getInventory().entrySet()) {
            text = entry.getKey().getName() + ": " + entry.getValue().toString();
            x = (gp.getScreenWidth() / 3) + 20;
            y = yheader + ((1 + row) * (gp.getTileSize() + 2));

            g2.drawString(text, x, y);
            row++;
        }
    }

    //EFFECTS: Draws the 'Load game' option screen.
    public void drawLoadScreen() {
        drawWord40("Confirm Load (Y)", 0);
        drawWord40("Cancel (N)", 1);
    }

    //EFFECTS: Draws the 'save game' option screen.
    public void drawSaveScreen() {
        drawWord40("Confirm Save (Y)", 0);
        drawWord40("Cancel (N)", 1);
    }

    //EFFECTS: Helper method to draw a window to put text in.
    public void makeWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); //(Black) Fourth number controls the opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 20, 20);

        g2.setColor(Color.white);
        BasicStroke basicStroke = new BasicStroke(5);
        g2.setStroke(basicStroke);
        g2.drawRoundRect(x, y, width, height, 20, 20);
    }

    //EFFECTS: Draws the exit option at the bottom of all screenStates option screen.
    public void drawExitOption() {
        g2.setFont(arial20);
        String text = "Press Q to return to game";
        int y = gp.getScreenHeight() - 80;
        int x = getXToCenterText(text);
        g2.drawString(text, x, y);
    }

    //EFFECTS: Helper method to draw the window that shows player inventory.
    public void makeTradeWindow() {
        int invSize = gp.getInv().getInventorySize();
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() / 2;
        int height = (gp.getTileSize() * 2) + (gp.getTileSize() * invSize);
        makeWindow(x, y, width, height);
    }

    //EFFECTS: Helper method to draw the dialogue window.
    public void makeDialogueWindow() {
        int x = gp.getTileSize() * 2;
        int y = gp.getTileSize() / 2;
        int width = gp.getScreenWidth() - (gp.getTileSize() * 4);
        int height = gp.getTileSize() * 4;
        makeWindow(x, y, width, height);
    }

    //EFFECTS: Helper method to draw a window to put text in.
    public int getXToCenterText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length / 2;
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
        g2.setFont(arial40);
        g2.setColor(Color.yellow);

        text = "You bought a house!";
        textX = getXToCenterText(text);
        textY = gp.getScreenHeight() / 2;
        g2.drawString(text, textX, textY);

        g2.setFont(arial20);
        g2.setColor(Color.yellow);
        text = "(in this economy!?)";
        textX = getXToCenterText(text);
        textY = gp.getScreenHeight() / 2 + (gp.getTileSize() * 2);
        g2.drawString(text, textX, textY);

        gp.gameThread = null;
    }
}


