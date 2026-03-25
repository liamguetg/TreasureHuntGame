package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import ui.GamePanel;
import ui.KeyHandler;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
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
    public void getNameTest() {
        assertEquals("Coin", coin.getName());
        assertEquals("Master Key", masterKey.getName());
        assertEquals("Chest", chest.getName());
        assertEquals("Sword", sword.getName());
        assertEquals("Shield", shield.getName());
        assertEquals("Key", key.getName());
        assertEquals("Boots", boots.getName());
    }

    public void fillInv() {
        testInventory.addToInv(coin, 1);
        testInventory.addToInv(sword, 2);
        testInventory.addToInv(key, 3);
        testInventory.addToInv(shield, 4);
        testInventory.addToInv(masterKey, 5);
        testInventory.addToInv(boots, 6);
        testInventory.addToInv(chest, 7);
    }

    @Test
    public void testAddToInventory() {
        assertEquals(0, testInventory.getInventorySize());
        fillInv();
        assertEquals(7, testInventory.getInventorySize());

        assertEquals(1, testInventory.getAmountOfItemInInv("Coin"));
        assertEquals(2, testInventory.getAmountOfItemInInv("Sword"));
        assertEquals(3, testInventory.getAmountOfItemInInv("Key"));
        assertEquals(4, testInventory.getAmountOfItemInInv("Shield"));
        assertEquals(5, testInventory.getAmountOfItemInInv("Master Key"));
        assertEquals(6, testInventory.getAmountOfItemInInv("Boots"));
        assertEquals(7, testInventory.getAmountOfItemInInv("Chest"));
    }


    @Test
    public void testRemoveItems() {
        fillInv();
        assertEquals(7, testInventory.getInventorySize());

        testInventory.removeItemFromInv("Key", 1);
        assertEquals(2, testInventory.getAmountOfItemInInv("Key"));

        testInventory.removeItemFromInv("Boots", 2);
        assertEquals(4, testInventory.getAmountOfItemInInv("Boots"));

        testInventory.removeItemFromInv("Shield", 4);
        assertEquals(0, testInventory.getAmountOfItemInInv("Shield"));

        testInventory.removeItemFromInv("Master Key", 6);
        assertEquals(5, testInventory.getAmountOfItemInInv("Master Key"));

        testInventory.clearInventory();
        assertEquals(0, testInventory.getInventorySize());
    }
}
