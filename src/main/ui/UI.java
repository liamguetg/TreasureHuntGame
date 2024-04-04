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

    public void makeWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); //(Black) Fourth number controls the opacity
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 20, 20);

        g2.setColor(Color.white);
        BasicStroke bStroke = new BasicStroke(5);
        g2.setStroke(bStroke);
        g2.drawRoundRect(x, y, width, height, 20, 20);
    }

    public void drawPauseScreen(){
        g2.setFont(arial80);
        String text = "PAUSED";
        int y = gp.screenHeight/2;
        int x = getXForCenterText(text);
        g2.drawString(text, x, y);
    }

    public int getXForCenterText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
