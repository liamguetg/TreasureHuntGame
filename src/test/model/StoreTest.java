package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GamePanel;
import ui.KeyHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StoreTest {
    GamePanel gp;
    KeyHandler keyH;
    Player testPlayer;
    Inventory testInventory;
    Store testStore;
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
        testStore = new Store(gp, keyH);
        coin = new ObjectCoin(gp);
        shield = new ObjectShield(gp);
        sword = new ObjectSword(gp);
        key = new ObjectKey(gp);
        chest = new ObjectChest(gp);
        boots = new ObjectBoots(gp);
        masterKey = new ObjectMasterKey(gp);
    }

    public void fillInv() {
        gp.getInv().addToInv(coin, 1);
        gp.getInv().addToInv(sword, 2);
        gp.getInv().addToInv(key, 3);
        gp.getInv().addToInv(shield, 4);
        gp.getInv().addToInv(masterKey, 5);
        gp.getInv().addToInv(boots, 6);
        gp.getInv().addToInv(chest, 7);
    }

    @Test
    public void buyTestSuccessful() {
        fillInv();
        assertEquals(7, gp.getInv().getInventorySize());

        assertEquals(1, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(3, gp.getInv().getAmountOfItemInInv("Key"));
        gp.getStore().buyItem(coin, "Key", 1);
        assertEquals(1, gp.getInv().getAmountOfItemInInv("Key"));
        assertEquals(2, gp.getInv().getAmountOfItemInInv("Coin"));

        assertEquals(4, gp.getInv().getAmountOfItemInInv("Shield"));
        assertEquals(6, gp.getInv().getAmountOfItemInInv("Boots"));
        testStore.buyItem(boots, "Shield", 4);
        assertEquals(0, gp.getInv().getAmountOfItemInInv("Shield"));
        assertEquals(7, gp.getInv().getAmountOfItemInInv("Boots"));

    }

    @Test
    public void buyTestFail() {
        fillInv();

        assertEquals(1, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(3, gp.getInv().getAmountOfItemInInv("Key"));
        gp.getStore().buyItem(coin, "Key", 4);
        assertEquals(3, gp.getInv().getAmountOfItemInInv("Key"));
        assertEquals(1, gp.getInv().getAmountOfItemInInv("Coin"));

        gp.getInv().clearInventory();
        gp.getStore().buyItem(masterKey, "Coin", 2);
        assertEquals(0, gp.getInv().getAmountOfItemInInv("Master Key"));
        assertEquals(0, gp.getInv().getAmountOfItemInInv("Coin"));
    }

    @Test
    public void sellTest() {
        fillInv();
        assertEquals(1, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(3, gp.getInv().getAmountOfItemInInv("Key"));
        gp.getStore().sellItem("Key");
        assertEquals(2, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(2, gp.getInv().getAmountOfItemInInv("Key"));

        gp.getStore().sellItem("Key");
        assertEquals(3, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(1, gp.getInv().getAmountOfItemInInv("Key"));

        gp.getStore().sellItem("Key");
        assertEquals(4, gp.getInv().getAmountOfItemInInv("Coin"));
        assertEquals(0, gp.getInv().getAmountOfItemInInv("Key"));
    }
}
