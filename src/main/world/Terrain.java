package world;

import java.awt.Graphics2D;
import java.util.Random;

import entities.Player;
import ui.GamePanel;
import lib.Costants;

public class Terrain {
    Tile[][] tiles;
    Integer tileSizeX;
    Integer tileSizeY;
    final int MAP_BUFFER = 10;
    Integer xMapPos = 0;
    Integer yMapPos = 0;
    Integer centerMapX = 0;
    Integer centerMapY = 0;
    Integer xBound = 0;
    Integer yBound = 0;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        xMapPos = screenColumns + MAP_BUFFER * (-tileSizeX);
        yMapPos = screenRows + MAP_BUFFER * (-tileSizeY);
        centerMapX = xMapPos;
        centerMapY = yMapPos;
        xBound = ((screenColumns + MAP_BUFFER * 2 - 2) * tileSizeX)/2;
        yBound = ((screenRows + MAP_BUFFER * 2 - 2) * tileSizeY)/2;
        this.tiles = new Tile[screenRows + MAP_BUFFER * 2][screenColumns + MAP_BUFFER * 2];
        
        Random r = new Random();
        int low = 1;
        int high = 100;
        switch (type) {
            case "GRASS":
                for (int i = 0; i < screenRows + MAP_BUFFER * 2; i++) {
                    for (int j = 0; j < screenColumns + MAP_BUFFER * 2; j++) {
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
            for (Tile tile : tilesRow) {
                    tile.paint(graphic, currentX, currentY);
                    currentX += this.tileSizeX;
            }
            currentY += this.tileSizeY;
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

    public void checkMapPos(Player player) {
        if (Math.abs(xMapPos) >= Math.abs(xBound) || xMapPos >= 0) {
            Integer lastMapPos = xMapPos;
            xMapPos = centerMapX;
            resetMapPos(lastMapPos, null);
        }
        if (Math.abs(yMapPos) >= Math.abs(yBound) || yMapPos >= 0) {
            Integer lastMapPos = yMapPos;
            yMapPos = centerMapY;
            resetMapPos(null, lastMapPos);
        }
    }

    public void resetMapPos(Integer lastXPos, Integer lastYPos) {
        if (lastXPos != null) {
            if (lastXPos >= 0) {
                System.out.println("Going left");
                slideMapTiles(0, 2);
            }
            if (lastXPos <= 0) {
                System.out.println("Going right");
                slideMapTiles(tiles[0].length - 2, tiles[0].length);
            }
        }
    }

    private void slideMapTiles(int index1, int index2) {
        for (int i = tiles.length - 1 - MAP_BUFFER; i >= 0; i--) {
            for (int j = tiles[i].length - 1 - MAP_BUFFER; j >= 0; j--) {
                tiles[i][j + MAP_BUFFER] = tiles[i][j];
                if (i <= MAP_BUFFER || j <= MAP_BUFFER) {
                    tiles[i][j] = new Tile(tileSizeX, tileSizeY, "SNOW");
                }
            }
        }
        genNewTiles();
    }

    private void genNewTiles() {
    }
}
