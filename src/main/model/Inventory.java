package model;

// import org.json.JSONArray;
// import org.json.JSONObject;
import ui.GamePanel;
import ui.KeyHandler;

import java.util.HashMap;
import java.util.Map;

// Represents a players Inventory, with the players items and the
// number of each item a player has.
public class Inventory {
    GamePanel gp;
    KeyHandler keyH;
    private final Map<ObjectSuper, Integer> inventory;

    // EFFECTS: Constructs an instance of an inventory with no contents
    public Inventory(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        inventory = new HashMap<>();
    }

    // EFFECTS: Returns the item in inventory with given name. Null if item with
    // that name is not present.
    public ObjectSuper getItemInInv(String itemInQuestion) {
        for (HashMap.Entry<ObjectSuper, Integer> entry : inventory.entrySet()) {
            ObjectSuper itemInInv = entry.getKey();
            String invItemName = itemInInv.name;
            if (invItemName.equals(itemInQuestion)) {
                return itemInInv;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Adds item to inventory by given amount
    public void addToInv(ObjectSuper itemInQuestion, int amount) {
        String itemName = itemInQuestion.getName();
        if (getItemInInv(itemName) == null) {
            inventory.put(itemInQuestion, amount);
            if (itemName.equals("Boots")) {
                gp.getPlayer().speed += 2;
            }
        } else {
            increaseItemAmountBy(itemName, amount);
        }
    }

    // MODIFIES: this
    // EFFECTS: Increases the amount of an item in inventory by a given amount.
    public void increaseItemAmountBy(String itemName, int amountToAdd) {
        ObjectSuper itemInQuestion = getItemInInv(itemName);
        int currentAmount = inventory.get(itemInQuestion);
        int newAmount = currentAmount + amountToAdd;
        inventory.put(itemInQuestion, newAmount);
    }

    // MODIFIES: this
    // EFFECTS: removes item from inventory by given amount.
    public boolean removeItemFromInv(String itemToRemoveName, int amountToRemove) {
        boolean gotFunds;
        int currentAmount;
        ObjectSuper itemInQuestion = getItemInInv(itemToRemoveName);
        if (itemInQuestion == null) {
            gotFunds = false;
        } else {
            currentAmount = inventory.get(itemInQuestion);
            int amountLeft = currentAmount - amountToRemove;
            if (amountLeft < 0) {
                gotFunds = false;
            } else if (amountLeft == 0) {
                inventory.remove(itemInQuestion);
                removeBoots(itemToRemoveName, amountToRemove);
                gotFunds = true;
            } else {
                inventory.put(itemInQuestion, amountLeft);
                removeBoots(itemToRemoveName, amountToRemove);
                gotFunds = true;
            }
        }
        return gotFunds;
    }

    // MODIFIES: player
    // EFFECTS: If boots are being removed, the speed must be reduced
    public void removeBoots(String itemName, int amountToRemove) {
        if (itemName.equals("Boots")) {
            gp.getPlayer().speed -= (2 * amountToRemove);
        }
    }

    // EFFECTS: gets the number of items in inventory
    public int getInventorySize() {
        return inventory.size();
    }

    // MODIFIES: this
    // EFFECTS: clears the inventory and resets the amount of each item to 0
    public void clearInventory() {
        inventory.clear();
    }

    // EFFECTS: Gets the amount of an item player has in inventory.
    public int getAmountOfItemInInv(String itemName) {
        ObjectSuper itemInQuestion = getItemInInv(itemName);

        if (itemInQuestion == null) {
            return 0;
        } else {
            return inventory.get(itemInQuestion);
        }
    }

    // PERSISTENCE

    // EFFECTS: Turns inventory into a JSON array
    // public JSONArray invToJson() {
    // JSONArray jsonArray = new JSONArray();
    // Map<ObjectSuper, Integer> yourInv = getInventory();
    // yourInv.forEach((key, value) -> jsonArray.put(saveEachItem(key, value)));
    // return jsonArray;
    // }

    // EFFECTS: Saves each Item as a JSONObject
    // public JSONObject saveEachItem(ObjectSuper key, int value) {
    // JSONObject json = new JSONObject();
    // json.put("Item", key.name);
    // json.put("Amount", value);
    // return json;
    // }

    // GETTERS
    public Map<ObjectSuper, Integer> getInventory() {
        return inventory;
    }
}
