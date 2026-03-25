package ui;

import model.*;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;


import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Creates the screen for the 2D game and runs the game thread (game loop).
// - Frame specifications:
// * Uses tiles sized 16x16 pixels scaled by a factor of 3 (final tile size 48x48).
// * Screen size is 16x12 tiles (768 x 576 pixels).
// - GameThread Specifications
// * Frame is refreshed at 60 FPS
public class GamePanel extends JPanel implements Runnable {

    //SCREEN SETTINGS:
    final int originalTileSize = 16; //16x16 title
    final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 48 pixels
    private final int maxScreenColTiles = 12;
    private final int maxScreenRowTiles = 16;
    private final int screenWidth = maxScreenRowTiles * tileSize; // 768 pixels
    private final int screenHeight = maxScreenColTiles * tileSize; // 576 pixels

    int fps = 60;

    // INSTANTIATES THE GAME CLASSES
    private TileManager tileM = new TileManager(this);
    private KeyHandler keyH = new KeyHandler(this);
    private CollisionCheck colCheck = new CollisionCheck(this);
    private ObjectPlacer itemPlacer = new ObjectPlacer(this);
    private UI ui = new UI(this);
    private Store store = new Store(this, keyH);

    //PERSISTENCE
    private static final String JSON_STORE = "./data/gameTest.json";
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE, this);


    Thread gameThread;

    // ENTITY AND OBJECTS/ITEMS
    private Inventory inventory = new Inventory(this, keyH);
    private Player player = new Player(this, keyH);
    private ObjectSuper[] objList  = new ObjectSuper[10];
    private ObjectSuper[] randItemList = new ObjectSuper[11];
    private Entities[] npcList = new Entities[10];


    //WORLD MAP SETTINGS
    private final int maxWorldCol = 50;
    private final int maxWorldRow = 50;

    //GAME STATES
    protected int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int inventoryState = 4;
    public final int confirmSaveState = 5;
    public final int confirmLoadState = 6;
    public final int tradeState = 7;
    public final int buyState = 8;
    public final int buyFailState = 9;
    public final int buyPassState = 10;
    public final int wonState = 11;
    public final int confirmSellKeyState = 12;
    public final int confirmSellBootsState = 13;
    public final int confirmSellSwordState = 14;
    public final int confirmSellShieldState = 15;
    public final int confirmSellChestState = 16;


    //EFFECTS: Constructor
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    //MODIFIES: this
    //EFFECTS: Sets up the start of the game
    public void setUpGame() {
        itemPlacer.setObjects();
        itemPlacer.setNPC();
        itemPlacer.setRandItems();
        gameState = playState;

    }

    //EFFECTS: Starts the game thread (and runs it)
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); //Automatically calls the run method
    }


//     MODIFIES: this
//     EFFECTS: loads game from file
    public void loadGame(JsonReader jsonReader) {
        try {
            jsonReader.parsePlayer(player);
            inventory.clearInventory();
            jsonReader.parseInv(inventory);
            System.out.println("Loaded Game from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    //MODIFIES: JSONfile?
    //EFFECTS: Saves game as JSON file.
    public void saveGame(JsonReader jsonReader) {
        try {
            getJsonWriter().open();
            getJsonWriter().write(getInv(), getPlayer());
            getJsonWriter().close();

            System.out.println("Saved Game to " + getJsonStore());
        } catch (FileNotFoundException f) {
            System.out.println("Unable to write to file: " + getJsonStore());
        }
    }


    //EFFECTS: Runs the game thread calling the update and draw functions for each part of the game.
    // Calculates when to update and redraw the screen (every 0.0166 sec. to achieve 60FPS).
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / fps; // 0.0166 seconds/frame (or 60 FPS)
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    //EFFECTS: Updates what is drawn on the screen depending on the gameState.
    public void update() {
        if (gameState == playState) {
            player.update();
            for (int i = 0; i < npcList.length; i++) {
                if (npcList[i] != null) {
                    npcList[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            // nothing for now
        }
        if (gameState == tradeState) {
            store.update();
        }
    }


    //EFFECTS: Built-in java subclass used to draw the screen graphics (Tiles, entities and objects)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TILES
        tileM.draw(g2);
        //PLAYER
        player.draw(g2);
        //OBJECTS
        for (int i = 0; i < objList.length; i++) {
            if (objList[i] != null) {
                objList[i].draw(g2, this);
            }
        }
        //RANDOM ITEMS
        for (int i = 0; i < randItemList.length; i++) {
            if (randItemList[i] != null) {
                randItemList[i].draw(g2, this);
            }
        }
        //ENTITIES
        for (int i = 0; i < npcList.length; i++) {
            if (npcList[i] != null) {
                npcList[i].draw(g2);
            }
        }
        //UI
        ui.draw(g2);
        g2.dispose();
    }

    //MODIFIES: this
    //EFFECTS: Changes the gameState
    public void setGameState(int newState) {
        gameState = newState;
    }


//    //MODIFIES: this
//    //EFFECTS: Sets the inventory to the saved inventory
//    public void setInventory(Inventory savedInv) {
//        inventory = savedInv;
//    }
//
//    //MODIFIES: this
//    //EFFECTS: Sets the player to the saved player
//    public void setPlayer(Player savedPlayer) {
//        player = savedPlayer;
//    }


    //GETTERS

    public int getLength(ObjectSuper[] sucker) {
        return sucker.length;
    }

    public int getTileSize() {
        return tileSize;
    }

    public TileManager getTileManaged() {
        return tileM;
    }

    public ObjectSuper[] getObjList() {
        return objList;
    }

    public ObjectSuper[] getRandItemList() {
        return randItemList;
    }

    public Entities[] getNpcList() {
        return npcList;
    }

    public CollisionCheck getColCheck() {
        return colCheck;
    }

    public Player getPlayer() {
        return player;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getMaxWorldCol() {
        return maxWorldCol;
    }

    public int getMaxWorldRow() {
        return maxWorldRow;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public Inventory getInv() {
        return inventory;
    }

    public Store getStore() {
        return store;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }

    public String getJsonStore() {
        return JSON_STORE;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public GamePanel getGP() {
        return this;
    }
}

