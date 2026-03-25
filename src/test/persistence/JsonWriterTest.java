package persistence;

import model.*;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GamePanel;
import ui.KeyHandler;

import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
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
    void testWriterInvalidFile() {
        String JSON_STORE = "./data/my/illegal:fileName.json";
        JsonWriter writer = new JsonWriter(JSON_STORE);
        try {
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //Pass
        }
    }

    @Test
    void testWriterEmptyGame() {
        String JSON_STORE = "./data/testWriterEmpty.json";
        JsonWriter writer = new JsonWriter(JSON_STORE);
        try {
            writer.open();
            writer.write(testInventory, testPlayer);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json", gp);
            gp.loadGame(reader);
            assertEquals(0, testInventory.getInventorySize());
            assertEquals(1104, testPlayer.getPlayerX());
            assertEquals(1008, testPlayer.getPlayerY());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralGame() {
        testInventory.addToInv(key, 1);
        testInventory.addToInv(coin, 2);
        testInventory.addToInv(chest, 3);
        testInventory.addToInv(sword, 4);
        testPlayer.setPlayerX(1105);
        testPlayer.setPlayerY(1003);


        String JSON_STORE = "./data/testWriterGeneral.json";
        JsonWriter writer = new JsonWriter(JSON_STORE);
        JsonReader reader = new JsonReader(JSON_STORE, gp);

        try {
            writer.open();
            writer.write(testInventory, testPlayer);
            writer.close();

            gp.loadGame(reader);
            assertEquals(4, testInventory.getInventorySize());
            assertEquals(1105, testPlayer.getPlayerX());
            assertEquals(1003, testPlayer.getPlayerY());
            assertEquals(1, testInventory.getAmountOfItemInInv("Key"));
            assertEquals(2, testInventory.getAmountOfItemInInv("Coin"));
            assertEquals(3, testInventory.getAmountOfItemInInv("Chest"));
            assertEquals(4, testInventory.getAmountOfItemInInv("Sword"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





}
