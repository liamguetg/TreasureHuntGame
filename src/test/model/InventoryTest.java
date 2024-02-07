package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {
    Inventory inventory;
    Items item1;
    Items item2;
    Items coin1;
    Items coin2;

    @BeforeEach
    public void beforeEach() {
        inventory = new Inventory();
        item1 = new Items("Mystery Item 1", 1, 10);
        item2 = new Items("Mystery Item 2", 1, 50);
        coin1 = new Coins("Coin", 1, 1);
        coin2 = new Coins("Coin", 1, 1);
    }

    public void fillInv() {
        inventory.addToInventory(item1);
        inventory.addToInventory(item2);
        inventory.addToInventory(coin1);
        inventory.addToInventory(coin2);
    }

    @Test
    public void testAddToInventory() {
        assertTrue(inventory.isEmpty());
        assertEquals(0, inventory.getInventorySize());
        inventory.addToInventory(item1);
        assertFalse(inventory.isEmpty());
        assertFalse(inventory.isEmpty());
        assertEquals(1, inventory.getInventorySize());
        inventory.addToInventory(item2);
        assertEquals(2, inventory.getInventorySize());
        inventory.addToInventory(coin1);
        assertEquals(3, inventory.getInventorySize());
        inventory.addToInventory(coin2);
        assertEquals(3, inventory.getInventorySize());
    }

    @Test
    public void testGetAmountOfItemInInv() {
        inventory.addToInventory(item1);
        inventory.addToInventory(item2);
        inventory.addToInventory(coin1);
        assertEquals(1, inventory.getAmountOfItemInInv(coin1));
        assertEquals(1, inventory.getAmountOfItemInInv(coin2));
        assertEquals(1, inventory.getTotValueOfItemInInv(coin1));
        inventory.addToInventory(coin2);
        assertEquals(2, inventory.getAmountOfItemInInv(coin1));
        assertEquals(2, inventory.getAmountOfItemInInv(coin2));

    }

    @Test
    public void testGetValue() {
        inventory.addToInventory(item1);
        inventory.addToInventory(item2);
        inventory.addToInventory(coin1);
        inventory.addToInventory(coin2);
        assertEquals(1, inventory.getValueOfItemInInv(coin1));
        assertEquals(1, inventory.getValueOfItemInInv(coin2));
        assertEquals(2, inventory.getTotValueOfItemInInv(coin2));
        assertEquals(2, inventory.getTotValueOfItemInInv(coin1));
        assertEquals(2, inventory.getTotValueOfItemInInv(coin2));
        assertEquals(10, inventory.getValueOfItemInInv(item1));
        assertEquals(10, inventory.getTotValueOfItemInInv(item1));
        assertEquals(50, inventory.getValueOfItemInInv(item2));
        assertEquals(50, inventory.getTotValueOfItemInInv(item2));
    }

    @Test
    public void testRemoveItemOneAtATime() {
        fillInv();
        assertEquals(3, inventory.getInventorySize());
        inventory.removeFromInventory(coin1, 1);
        assertEquals(3, inventory.getInventorySize());
        inventory.removeFromInventory(item1, 1);
        assertEquals(2, inventory.getInventorySize());
        inventory.removeFromInventory(item2, 1);
        inventory.removeFromInventory(coin1, 1);
        assertEquals(0, inventory.getInventorySize());
        assertTrue(inventory.isEmpty());
    }

    @Test
    public void testRemoveTwoAtATime() {
        fillInv();
        assertEquals(3, inventory.getInventorySize());
        inventory.removeFromInventory(coin2, 2);
        assertEquals(2, inventory.getInventorySize());

        inventory.clearInventory();
        assertTrue(inventory.isEmpty());
        assertEquals(0, inventory.getAmountOfItemInInv(coin1));

        fillInv();
        assertFalse(inventory.isEmpty());
        assertEquals(3, inventory.getInventorySize());
        assertEquals(1, inventory.getAmountOfItemInInv(item1));
        assertEquals(2, inventory.getAmountOfItemInInv(coin1));
        inventory.removeFromInventory(coin1, 2);
        assertEquals(2, inventory.getInventorySize());
    }
}