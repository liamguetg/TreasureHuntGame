package ui;

import model.*;

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
    public final int tileSize = originalTileSize * scale; // 48 pixels
    public final int maxScreenColTiles = 12;
    public final int maxScreenRowTiles = 16;
    public final int numColOnScreen = 16;
    public final int numRowsOnScreen = 12;
    public final int screenWidth = maxScreenRowTiles * tileSize; // 768 pixels
    public final int screenHeight = maxScreenColTiles * tileSize; // 576 pixels

    int FPS = 60;

    // INSTANTIATES THE GAME FUNCTIONS
    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    //    Sound soundEffect = new Sound(); // Dont have the audio files to use
    //    Sound music = new Sound();
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public objectPlacer itemPlacer = new objectPlacer(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECTS/ITEMS
    public BetterInv betterInv = new BetterInv();
    public Player player = new Player(this, keyH);
    public ObjectSuper objList[] = new ObjectSuper[10];
    public Entities npc[] = new Entities[10];

    //WORLD MAP SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
//    public final int worldWith = tileSize * maxWorldCol;
//    public final int worldHeight = tileSize * maxWorldRow;

    //GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame() {
        itemPlacer.setObjects();
        itemPlacer.setNPC();
//        playMusic(0);
//        stopMusic();
        gameState = playState;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //Automatically calls the run method
    }

    //EFFECTS: Calls the update and repaints functions (to Updates player information and update screen)
    //         At each time interval (ie overy 0.0166 sec. to acheive 60FPS).
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.0166 seconds/frame (or 60 FPS)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
//        double nextDrawTime = System.nanoTime() + drawInterval;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
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
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing for now
        }
        if (gameState == dialogueState) {
            //
        }
    }

    // Built-in java class used to draw the scene
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;


        tileM.draw(g2);
        player.draw(g2);
        ui.draw(g2);

        //ITEMS
        for (int i = 0; i < objList.length; i++) {
            if (objList[i] != null) {
                objList[i].draw(g2, this);
            }
        }
        //NPC
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }
        g2.dispose();
    }
}

    //MUSIC/SOUND
//    public void playMusic(int i) {
//        music.setFile(i);
//        music.play();
//        music.loop();
//    }
//    public void stopMusic() {
//        music.stop();
//    }
//    public void playSoundEffect(int i) {
//        soundEffect.setFile(i);
//        soundEffect.play();
//    }


