package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.*;
import org.json.*;
import ui.GamePanel;


// Represents a reader that reads the inventory and player position from JSON data stored in file
public class JsonReader {
    private String source;
    GamePanel gp;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source, GamePanel gp) {
        this.gp = gp;
        this.source = source;
    }


    // EFFECTS: reads Game file and returns it as a JSONObj.
    // throws IOException if an error occurs reading data from file
    public JSONObject readGameFile() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }


    //PLAYER LOADING:

    //EFFECTS: parses the item info from the JSON object and returns it
    public void parsePlayer(Player savedPlayer) throws IOException {
        JSONObject gameFile = readGameFile();
        JSONObject playerJsonOb = (JSONObject) gameFile.get("Character:");
        parseChar(savedPlayer, playerJsonOb);
    }

    public void parseChar(Player savedPlayer, JSONObject playerJsonOb) {
        if (playerJsonOb.getInt("X-position:") == -1
                || playerJsonOb.getInt("Y-position") == -1) {
            savedPlayer.setDefaultPlayer();
        } else {
            int posX = playerJsonOb.getInt("X-position:");
            savedPlayer.setPlayerX(posX);
            int posY = playerJsonOb.getInt("Y-position");
            savedPlayer.setPlayerY(posY);
        }
    }



//    //EFFECTS: Obtains the JSONObject with the character information.
//    public JSONObject loadJsonObPlayer() throws IOException {
//        JSONObject gameFile = readGameFile();
//        JSONObject playerJsonOb = (JSONObject) gameFile.get("Character:");
//        return playerJsonOb;
//    }

    //EFFECTS: Obtains the saved players x position from the JSONObject.
//    public int parsePlayerPosX(JSONObject playerJsonOb) {
//        int posX = playerJsonOb.getInt("X-position:");
//        return posX;
//    }
//
//    //EFFECTS: Obtains the saved players y position from the JSONObject.
//    public int parsePlayerPosY(JSONObject playerJsonOb) {
//        int posY = playerJsonOb.getInt("Y-position");
//        return posY;
//    }



    //INVENTORY LOADING:

    //EFFECTS: parses the item info from the JSON object and returns it
    public void parseInv(Inventory savedInv) throws IOException {
        JSONObject gameFile = readGameFile();
        JSONArray itemArray = (JSONArray) gameFile.get("Inventory:");
        parseItems(savedInv, itemArray);
    }

    //EFFECTS: parses items from JSON object and adds them to inventory
    private void parseItems(Inventory savedInv, JSONArray itemArray) {
        for (Object json : itemArray) {
            JSONObject nextItem = (JSONObject) json;
            addSavedItem(savedInv, nextItem);
        }
    }

    //MODIFIES: inventory
    //EFFECTS: Adds item and amount to inventory.
    @SuppressWarnings("methodlength")
    public void addSavedItem(Inventory savedInv, JSONObject jsonObject) {
        String itemName = jsonObject.getString("Item");
        int amount = jsonObject.getInt("Amount");
        switch (itemName) {
            case "Key":
                ObjectSuper key = new ObjectKey(gp);
                savedInv.addToInv(key, amount);
                break;
            case "Coin":
                ObjectSuper coin = new ObjectCoin(gp);
                savedInv.addToInv(coin, amount);
                break;
            case "Sword":
                ObjectSuper sword = new ObjectSword(gp);
                savedInv.addToInv(sword, amount);
                break;
            case "Shield":
                ObjectSuper shield = new ObjectShield(gp);
                savedInv.addToInv(shield, amount);
                break;
            case "Boots":
                ObjectSuper boots = new ObjectBoots(gp);
                savedInv.addToInv(boots, amount);
                break;
            case "Chest":
                ObjectSuper chest = new ObjectChest(gp);
                savedInv.addToInv(chest, amount);
                break;
            case "Master Key":
                ObjectSuper masterKey = new ObjectMasterKey(gp);
                savedInv.addToInv(masterKey, amount);
                break;
        }
    }
}




