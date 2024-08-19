package main.world;

import java.awt.Graphics2D;
import java.util.Random;

import main.entities.Player;
import main.lib.Constants;
import main.ui.GamePanel;

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
    Tile snow;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        xMapPos = MAP_BUFFER * -tileSizeX;
        yMapPos = MAP_BUFFER * -tileSizeY;
        centerX = xMapPos;
        centerY = yMapPos;
        boundLeft = MAP_BUFFER / 3 * -tileSizeX;
        // boundRight = xMapPos * 2 + bound;
        this.tiles = new Tile[screenRows + MAP_BUFFER * 2][screenColumns + MAP_BUFFER * 2];
        snow = new Tile(tileSizeX, tileSizeY, "SNOW");
        
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
        if (xMapPos >= boundLeft) {
            xMapPos = centerX;
            resetMapPos(Constants.RIGHT);
        }
        //     System.out.println(xMapPos);
        //     System.out.println(boundRight);
            // System.exit(0);gcc
        // if (Math.abs(xMapPos) >= Math.abs(boundRight)){
        //     System.out.println("We go right");
        //     xMapPos = -bound;
        //     resetMapPos(Constants.LEFT);
        // }
        // if (Math.abs(yMapPos) >= Math.abs(bound)) {
        //     Integer lastMapPos = yMapPos;
        //     yMapPos = bound * 2;
        //     resetMapPos(null, lastMapPos);
        // }
    }

    public void resetMapPos(String direction) {
        slideMapTiles(direction);
        // if (lastYPos != null) {
        //     if (lastYPos == 0) {
        //         slideMapTiles(Constants.UP);
        //     }
        //     if (lastYPos < 0) {
        //         slideMapTiles(Constants.DOWN);
        //     }
        // }
    }

    private void slideMapTiles(String direction) {
        switch (direction) {
            case Constants.RIGHT:
                for (int i = tiles.length - 1; i >= 0; i--) {
                    for (int j = tiles[i].length - 1 - MAP_BUFFER; j >= 0; j--) {
                        tiles[i][j + MAP_BUFFER * 2 / 3] = tiles[i][j];
                        if (j <= MAP_BUFFER) {
                            tiles[i][j] = snow;
                        }
                    }
                }
                break;
            case Constants.LEFT:
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[i].length; j++) {
                        if (j >= tiles[i].length - MAP_BUFFER) {
                            tiles[i][j] = snow;
                        } else {
                            tiles[i][j] = tiles[i][j + MAP_BUFFER];
                        }
                    }
                }
                break;

            case Constants.UP:
                for (int i = tiles.length - 1 - MAP_BUFFER; i >= 0; i--) {
                    for (int j = tiles[i].length - 1; j >= 0; j--) {
                        tiles[i + MAP_BUFFER][j] = tiles[i][j];
                        if (i <= MAP_BUFFER) {
                            tiles[i][j] = snow;
                        }
                    }
                }
                break;
                
            case Constants.DOWN:
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[i].length; j++) {
                        if (i >= tiles.length - MAP_BUFFER) {
                            tiles[i][j] = snow;
                        } else {
                            tiles[i][j] = tiles[i + MAP_BUFFER][j];
                        }
                    }
                }
                break;

            default:
                break;
        }
    }

    private void printTiles(Tile[][] tiles) {
        for (Tile[] row : this.tiles) {
            System.out.print("Cols: " + (row.length - 1)  + " ");
            for (Tile tile : row) {
                System.out.print(tile.type.charAt(0));
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    private void genNewTiles() {
    }
}
