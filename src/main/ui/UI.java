package ui;

import model.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

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
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;

        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80 = new Font("Arial", Font.BOLD, 80);
        arial20 = new Font("Arial", Font.PLAIN, 20);
        ObjectKey objectKey = new ObjectKey(gp);
        keyImage = objectKey.img;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
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
