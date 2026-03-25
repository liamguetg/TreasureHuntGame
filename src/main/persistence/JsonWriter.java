package persistence;


import model.Inventory;
import model.Player;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of the Game to file (saves game).
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: Constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: Opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of game to file
    public void write(Inventory inv, Player player) {
        JSONObject jsonGame = new JSONObject();
        JSONArray jsonInv = inv.invToJson();
        JSONObject jsonPlayer = player.playerToJson();
        jsonGame.put("Inventory:", jsonInv);
        jsonGame.put("Character:", jsonPlayer);
        saveToFile(jsonGame.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
