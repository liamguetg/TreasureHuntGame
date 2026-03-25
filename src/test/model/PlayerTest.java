package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.GamePanel;
import ui.KeyHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
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

    @Test
    public void positionTest() {
        assertEquals(1104, gp.getPlayer().getPlayerX());
        assertEquals(1008, gp.getPlayer().getPlayerY());
        gp.getPlayer().setPlayerX(1100);
        gp.getPlayer().setPlayerY(1000);
        assertEquals(1100, gp.getPlayer().getPlayerX());
        assertEquals(1000, gp.getPlayer().getPlayerY());
        gp.getPlayer().setDefaultPlayer();
        assertEquals(1104, gp.getPlayer().getPlayerX());
        assertEquals(1008, gp.getPlayer().getPlayerY());
    }
    @Test
    public void speedTest() {
        assertEquals(4, gp.getPlayer().speed);
        gp.getInv().addToInv(boots, 1);
        assertEquals(6, gp.getPlayer().speed);
        gp.getInv().removeItemFromInv("Boots", 1);
        assertEquals(4, gp.getPlayer().speed);

    }

}
