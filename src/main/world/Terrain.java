package world;

import java.awt.Graphics2D;
import java.util.Random;

public class Terrain {
    Tile[][] tiles;
    Integer tileSizeX;
    Integer tileSizeY;
    final int mapBuffer = 10;
    Integer xMapPos = 0;
    Integer yMapPos = 0;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        xMapPos = screenRows + mapBuffer * (-tileSizeX);
        yMapPos = screenRows + mapBuffer * (-tileSizeY);
        this.tiles = new Tile[screenRows + mapBuffer * 2][screenColumns + mapBuffer * 2];
        
        Random r = new Random();
        int low = 1;
        int high = 100;
        switch (type) {
            case "GRASS":
                for (int i = 0; i < screenRows + mapBuffer * 2; i++) {
                    for (int j = 0; j < screenColumns + mapBuffer * 2; j++) {
                        int result = r.nextInt(high - low) + low;
                        if (result < 90) {
                            type = "GRASS";
                        } else {
                            type = "LAVA";
                        }
                        tiles[i][j] = new Tile(tileSizeX, tileSizeY, type);
                    }
                }
                break;

            default:
                this.tiles = new Tile[screenColumns][screenRows];
                break;
        }

    }

    public void paint(Graphics2D graphic) {
        Integer currentX = xMapPos;
        Integer currentY = yMapPos;

        for (Tile[] tilesRow : tiles) {
            currentX = xMapPos;
            boolean drawn = false;
            for (Tile tile : tilesRow) {
                if (tile != null) {
                    tile.paint(graphic, currentX, currentY);
                    currentX += this.tileSizeX;
                    drawn = true;
                }
            }

            if (drawn) {
                currentY += this.tileSizeY;
            }
        }
    }
    
    public Tile[][] getTiles() {
        return tiles;
    }

    public void setXMapPos(Integer pos) {
        xMapPos += pos;
    }

    public void setYMapPos(Integer pos) {
        yMapPos += pos;
    }
}
