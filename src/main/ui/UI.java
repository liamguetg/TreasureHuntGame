package ui;

import model.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
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
        //test for the new branch

        if (gameDone == true) {
            String text;
            int textLength;
            int textX;
            int textY;
            g2.setFont(arial40);
            g2.setColor(Color.white);

            text = "You found the treasure";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            textX = gp.screenWidth/2 - textLength/2;
            textY = gp.screenHeight/2;
            g2.drawString(text, textX, textY);

            text = "Your time was: " + dFormat.format(playTime);
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            textX = gp.screenWidth/2 - textLength/2;
            textY = gp.screenHeight/2 + (gp.tileSize *2);
            g2.drawString(text, textX, textY);

            g2.setFont(arial80);
            g2.setColor(Color.yellow);
            text = "CONGRATS";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            textX = gp.screenWidth/2 - textLength/2;
            textY = gp.screenHeight/2 - (gp.tileSize *2);
            g2.drawString(text, textX, textY);

            gp.gameThread = null;

        } else {
            g2.setFont(arial40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 65);

            //TIME:
            playTime += (double)1/60;
            g2.setFont(arial20);
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize*13, gp.tileSize);


            if (messageOn == true) {
                // A way to use the current font but change the size
                // Instead of making a new instance
                // (ie instead of using g2.setFont(arial30));
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 12);
                messageCounter++;

                //
                if (messageCounter > 120) { //120 frames (we are at 60 fps -> message displayed for 2 sec)
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
