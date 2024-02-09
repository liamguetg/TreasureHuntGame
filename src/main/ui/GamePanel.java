package ui;

import javax.swing.*;
import java.awt.*;

// Creates the screen for the 2D game.
public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS
    // Since visuals will be determined by the size of tiles I am using.
    // And since more tiles == more work,
    // I have chosen to use small tiles and then scale them to look larger
    final int originalTileSize = 16; //16x16 title
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48
    final int maxScreenHeightTiles = 12;
    final int maxScreenWidthTiles = 16;
    final int screenWidth = maxScreenWidthTiles * tileSize; // 768 pixels
    final int screenHeight = maxScreenHeightTiles * tileSize; // 576 pixels

    //Set player default settings
    int playerX = 100;
    int playerY = 100;
    int playerSpeed= 5;

    int FPS = 60;

    // INSTANTIATING BASIC GAME FUNCTIONS
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //Automatically calls the run method
    }

    //EFFECTS: Calls the update and repaints functions (to Updates player information and update screen)
    //         At each time interval (ie overy 0.0166 sec. to acheive 60FPS).
    @Override
    public void run() {
        double drawInterval = (double) 1000000000/FPS; // 0.0166 seconds/frame (or 60 FPS)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
//        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000) {
                System.out.println("FPS:" + drawCount);
                drawCount = 0;
                timer = 0;
            }



// Another method for making a game loop:
//            try {
//                double remainingTime = (nextDrawTime - System.nanoTime());
//                remainingTime = remainingTime/1000000;
//
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long) remainingTime);
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

        }
    }

    public void update() {
        if(keyH.upPressed == true) {
            playerY = playerY - playerSpeed;
        } else if(keyH.downPressed == true) {
            playerY = playerY + playerSpeed;
        } else if(keyH.leftPressed == true) {
            playerX = playerX - playerSpeed;
        } else if(keyH.rightPressed == true) {
            playerX = playerX + playerSpeed;
        }
    }

    // Built-in java class used to draw the scene
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
