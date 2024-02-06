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

    @Test
    public void testAddToInventory() {
        assertTrue(inventory.isEmpty());
        assertEquals(0, inventory.inventorySize());
        inventory.addToInventory(item1);
        assertFalse(inventory.isEmpty());
        assertFalse(inventory.isEmpty());
        assertEquals(1, inventory.inventorySize());
        inventory.addToInventory(item2);
        assertEquals(2, inventory.inventorySize());
        inventory.addToInventory(coin1);
        assertEquals(3, inventory.inventorySize());
        inventory.addToInventory(coin2);
        assertEquals(3, inventory.inventorySize());
    }





}