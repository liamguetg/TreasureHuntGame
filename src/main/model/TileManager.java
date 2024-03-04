package model;

import ui.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
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

    // EFFECTS: Retrieves and scales the images for each tile before entering the game loop.
    public void setUpTile(int index, String imageName, boolean collision) {
        ToolScaleImage toolScaleImg = new ToolScaleImage();
        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/BackgroundTiles/"+imageName+".png"));
            tile[index].image = toolScaleImg.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
            br.close();
        } catch(Exception e) {
        }
    }

    //EFFECTS: Draws the game screen (the background)
    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;
        int x = 0;
        int y= 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.entityWorldX + gp.player.screenX;
            int screenY = worldY - gp.player.entityWorldY + gp.player.screenY;

            // if statement so we only draws the necessary tiles (the tiles surrounding the player)
            if(worldX + gp.tileSize > gp.player.entityWorldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.entityWorldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.entityWorldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.entityWorldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
