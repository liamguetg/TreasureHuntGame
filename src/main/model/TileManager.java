package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Represents a class to manage the all the tiles. Helps to treat them as part of a map. Each tile gets a
// corresponding 2D index.

public class TileManager {

    private GamePanel gp;
    private Tile[] tile;
    private int[][] mapTileNum;

    // EFFECTS: Constructor
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("/Maps/world01.txt");
    }

    // EFFECTS: Gets the tile image based on the index from the map
    public void getTileImage() {
        setUpTile(0, "grass", false);
        setUpTile(1, "wall", true);
        setUpTile(2, "water", true);
        setUpTile(3, "earth", false);
        setUpTile(4, "tree", true);
        setUpTile(5, "sand", false);
    }

    // MODIFIES: Tile
    // EFFECTS: Retrieves and scales the images for each tile before entering the
    // game loop.
    public void setUpTile(int index, String imageName, boolean collision) {
        ToolScaleImage toolScaleImg = new ToolScaleImage();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/BackgroundTiles/" + imageName + ".png"));
            tile[index].image = toolScaleImg.scaleImage(tile[index].getImage(), gp.getTileSize(), gp.getTileSize());
            tile[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: Loads the tiles, indexing them as a 2D matrix, giving them a
    // "position" on the world map.
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.getMaxWorldCol() && row < gp.getMaxWorldRow()) {
                String line = br.readLine();
                while (col < gp.getMaxWorldCol()) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: g2
    // EFFECTS: Draws the game screen (the background) tiles.
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
        int x = 0;
        int y = 0;

        while (worldCol < gp.getMaxWorldCol() && worldRow < gp.getMaxWorldRow()) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.getTileSize();
            int worldY = worldRow * gp.getTileSize();
            int screenX = worldX - gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX();
            int screenY = worldY - gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY();

            if (onScreen(worldX, worldY)) {
                g2.drawImage(tile[tileNum].getImage(), screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == gp.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    // EFFECTS: Determines if the tiles is on screen.
    public boolean onScreen(int worldX, int worldY) {
        return worldX + gp.getTileSize() > gp.getPlayer().entityWorldX - gp.getPlayer().getScreenX()
                && worldX - gp.getTileSize() < gp.getPlayer().entityWorldX + gp.getPlayer().getScreenX()
                && worldY + gp.getTileSize() > gp.getPlayer().entityWorldY - gp.getPlayer().getScreenY()
                && worldY - gp.getTileSize() < gp.getPlayer().entityWorldY + gp.getPlayer().getScreenY();
    }

    // GETTERS
    public int[][] getMapTileNum() {
        return mapTileNum;
    }

    public Tile[] getTile() {
        return tile;
    }
}
