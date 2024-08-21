package main.world;

import java.awt.Graphics2D;
import java.util.Random;
import main.entities.Player;
import main.lib.Constants;

public class Terrain {
    Tile[][] tiles;
    Integer tileSizeX;
    Integer tileSizeY;
    final int MAP_BUFFER = 5 * 3;
    public Integer xMapPos = 0;
    public Integer yMapPos = 0;
    public Integer centerX = 0;
    public Integer centerY = 0;
    Integer boundLeft = 0;
    Integer boundRight = 0;
    Integer boundUp = 0;
    Integer boundDown = 0;
    Tile snow;
    Tile grass;
    Tile lava;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        Integer rowNum = screenRows + MAP_BUFFER * 2;
        Integer colNum = screenColumns + MAP_BUFFER * 2;
        xMapPos = MAP_BUFFER * -tileSizeX;
        yMapPos = MAP_BUFFER * -tileSizeY;
        centerX = xMapPos;
        centerY = yMapPos;
        boundLeft = MAP_BUFFER / 3 * -tileSizeX;
        boundRight = centerY - (MAP_BUFFER * 2 / 3) * tileSizeY;
        boundUp = MAP_BUFFER / 3 * -tileSizeY;
        boundDown = centerY - (MAP_BUFFER * 2 / 3) * tileSizeY;
        
        this.tiles = new Tile[rowNum][colNum];
        snow = new Tile(tileSizeX, tileSizeY, Constants.SNOW);
        lava = new Tile(tileSizeX, tileSizeY, Constants.LAVA);
        grass = new Tile(tileSizeX, tileSizeY, Constants.GRASS);
        
        for (int i = 0; i < rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                tiles[i][j] = getRandomTile();
            }
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
        if (xMapPos >= boundLeft) {
            xMapPos = centerX;
            slideMapTiles(Constants.RIGHT);
        }

        if (xMapPos <= boundRight) {
            xMapPos = centerX;
            slideMapTiles(Constants.LEFT);
        }

        if (yMapPos >= boundUp) {
            yMapPos = centerY;
            slideMapTiles(Constants.DOWN);
        }

        if (yMapPos <= boundDown) {
            yMapPos = centerY;
            slideMapTiles(Constants.UP);
        }
    }

    private void slideMapTiles(String direction) {
        switch (direction) {
            case Constants.RIGHT:
                for (int i = tiles.length - 1; i >= 0; i--) {
                    for (int j = tiles[i].length - 1 - MAP_BUFFER; j >= 0; j--) {
                        tiles[i][j + MAP_BUFFER * 2 / 3] = tiles[i][j];
                        if (j <= MAP_BUFFER) {
                            tiles[i][j] = getRandomTile();
                        }
                    }
                }
                break;

            case Constants.LEFT:
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[i].length; j++) {
                        if (j >= tiles[i].length - MAP_BUFFER) {
                            tiles[i][j] = getRandomTile();
                        } else {
                            tiles[i][j] = tiles[i][j + MAP_BUFFER * 2 / 3];
                        }
                    }
                }
                break;

            case Constants.DOWN:
                for (int i = tiles.length - 1 - MAP_BUFFER; i >= 0; i--) {
                    for (int j = tiles[i].length - 1; j >= 0; j--) {
                        tiles[i + MAP_BUFFER * 2 / 3][j] = tiles[i][j];
                        if (i <= MAP_BUFFER) {
                            tiles[i][j] = getRandomTile();
                        }
                    }
                }
                break;

            case Constants.UP:
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[i].length; j++) {
                        if (i >= tiles.length - MAP_BUFFER) {
                            tiles[i][j] = getRandomTile();
                        } else {
                            tiles[i][j] = tiles[i + MAP_BUFFER * 2 / 3][j];
                        }
                    }
                }
                break;

            default:
                System.out.println("Map default, son where are you going?");
                break;
        }
    }

    private Tile getRandomTile() {
        Random r = new Random();
        int low = 1;
        int high = 100;
        int result = r.nextInt(high - low) + low;

        if (80 <= result && result < 90){
            return lava;
        }

        if (95 <= result){
            return snow;
        }

        return grass;
    } 
}
