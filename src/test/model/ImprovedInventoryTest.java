package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImprovedInventoryTest {
    ImprovedInventory inventory;
    ImprovedItems item1;
    ImprovedItems item2;
    ImprovedItems coin1;
    ImprovedItems coin2;
    ImprovedItems coin3;

    @BeforeEach
    public void beforeEach() {
        inventory = new ImprovedInventory();
        item1 = new ImprovedItems("Mystery Item 1", 1, 10);
        item2 = new ImprovedItems("Mystery Item 2", 1, 50);
        coin1 = new ImprovedItems("Coin", 1, 1);
        coin2 = new ImprovedItems("Coin", 1, 1);
        coin3 = new ImprovedItems("Coin", 1, 1);
    }

    public void fillInv() {
        inventory.addToInventory(item1);
        inventory.addToInventory(item2);
        inventory.addToInventory(coin1);
        inventory.addToInventory(coin2);
        inventory.addToInventory(coin3);
    }

    @Test
    public void testConstructor() {
        assertTrue(inventory.hiddenInventory.isEmpty());
        assertTrue(inventory.shownInventory.isEmpty());
        assertEquals(0, inventory.hiddenInventory.size());
        assertEquals(0, inventory.shownInventory.size());
    }
    @Test
    public void testAddToInventory() {
        inventory.addToInventory(item1);
        assertEquals(1, inventory.hiddenInventory.size());
        assertEquals(1, inventory.shownInventory.size());
        inventory.addToInventory(item2);
        inventory.addToInventory(coin1);
        assertEquals(3, inventory.hiddenInventory.size());
        assertEquals(3, inventory.shownInventory.size());
        inventory.addToInventory(coin2);
        inventory.addToInventory(coin3);
        assertEquals(5, inventory.hiddenInventory.size());
        assertEquals(3, inventory.shownInventory.size());
    }

    @Test
    public void testUpdateItemAmount() {
        inventory.addToInventory(item1);
        assertEquals(1, item1.getAmountInInv());
        inventory.addToInventory(item2);
        assertEquals(1, item2.getAmountInInv());
        inventory.addToInventory(coin1);
        assertEquals(1, coin1.getAmountInInv());
        assertEquals(1, coin2.getAmountInInv());
        inventory.addToInventory(coin2);
        assertEquals(2, coin2.getAmountInInv());
        inventory.addToInventory(coin3);
        assertEquals(3, coin3.getAmountInInv());
    }
}