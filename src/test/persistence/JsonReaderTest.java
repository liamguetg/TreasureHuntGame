package persistence;

import model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GamePanel;
import ui.KeyHandler;
//import ui.GamePlayTextBasedInterface;
//import ui.InventoryInterface;
//import ui.StartGame;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {
    GamePanel gp;
    KeyHandler keyH;
    Player testPlayer;
    Inventory testInventory;
    ObjectSuper coin;
    ObjectSuper shield;
    ObjectSuper sword;
    ObjectSuper key;
    ObjectSuper chest;
    ObjectSuper boots;
    ObjectSuper masterKey;


    @BeforeEach
    public void beforeEach() {
        gp = new GamePanel();
        keyH = new KeyHandler(gp);
        testPlayer = new Player(gp, keyH);
        testInventory = new Inventory(gp, keyH);
        coin = new ObjectCoin(gp);
        shield = new ObjectShield(gp);
        sword = new ObjectSword(gp);
        key = new ObjectKey(gp);
        chest = new ObjectChest(gp);
        boots = new ObjectBoots(gp);
        masterKey = new ObjectMasterKey(gp);
    }

    @Test
    void testReaderNonExistentFile() {
        try {
            String JSON_STORE = "./data/noSuchFile.json";
            JsonReader reader = new JsonReader(JSON_STORE, gp);

            reader.parsePlayer(testPlayer);
            testInventory.clearInventory();
            reader.parseInv(testInventory);
            System.out.println("Loaded Game from " + JSON_STORE);

            fail("IOException expected");
        } catch (IOException e) {
            //Pass
        }
    }

    @Test
    void testReaderEmptyGame() {
        JsonReader reader = new JsonReader("./data/testReaderEmpty.json", gp);
        try {
            String JSON_STORE = "./data/testReaderEmpty.json";
            reader.parsePlayer(testPlayer);
            testInventory.clearInventory();
            reader.parseInv(testInventory);
            System.out.println("Loaded Game from " + JSON_STORE);

            assertEquals(0, testInventory.getInventorySize());
            assertEquals(1104, testPlayer.getPlayerX());
            assertEquals(1008, testPlayer.getPlayerY());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json", gp);
        try {
            String JSON_STORE = "./data/testReaderGeneral.json";
            reader.parsePlayer(testPlayer);
            testInventory.clearInventory();
            reader.parseInv(testInventory);
            System.out.println("Loaded Game from " + JSON_STORE);

            assertEquals(5, testInventory.getInventorySize());
            assertEquals(1008, testPlayer.getPlayerY());
            assertEquals(1210, testPlayer.getPlayerX());

            assertEquals(1, testInventory.getAmountOfItemInInv("Shield"));
            assertEquals(1, testInventory.getAmountOfItemInInv("Chest"));
            assertEquals(4, testInventory.getAmountOfItemInInv("Key"));
            assertEquals(1, testInventory.getAmountOfItemInInv("Boots"));
            assertEquals(2, testInventory.getAmountOfItemInInv("Sword"));

//            HashMap<ObjectSuper, Integer> comparisonInv = new HashMap<>();
//            comparisonInv.put(shield, 1);
//            comparisonInv.put(chest, 1);
//            comparisonInv.put(key, 4);
//            comparisonInv.put(boots, 1);
//            comparisonInv.put(sword, 2);
//            assertEquals(comparisonInv, testInventory.getInventory());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

