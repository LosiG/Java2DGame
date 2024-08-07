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
    final int MAP_BUFFER = 10;
    Integer xMapPos = 0;
    Integer yMapPos = 0;
    Integer centerMapX = 0;
    Integer centerMapY = 0;
    Integer xBound = 0;
    Integer yBound = 0;
    Tile snow;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        xMapPos = (((screenColumns + MAP_BUFFER * 2) /2) * (-tileSizeX)) + GamePanel.WIDTH;
        yMapPos = screenRows + MAP_BUFFER * (-tileSizeY);
        centerMapX = xMapPos;
        centerMapY = yMapPos;
        xBound = (MAP_BUFFER * tileSizeX);
        yBound = ((screenRows + MAP_BUFFER * 2) * tileSizeY)/2;
        System.out.println("center" + xMapPos);
        System.out.println("xbound: " + xBound);
        System.out.println("diff + " + (-xBound - xMapPos));
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
        if (Math.abs(xMapPos) <= Math.abs(xBound) || xMapPos >= 0) {
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
                slideMapTiles(Constants.LEFT);
            }
            if (lastXPos <= 0) {
                slideMapTiles(Constants.RIGHT);
            }
        }
        if (lastYPos != null) {
            if (lastYPos == 0) {
                System.out.println(lastYPos);
                slideMapTiles(Constants.UP);
            }
            if (lastYPos < 0) {
                slideMapTiles(Constants.DOWN);
            }
        }
    }

    private void slideMapTiles(String direction) {
        switch (direction) {
            case Constants.LEFT:
                for (int i = tiles.length - 1; i >= 0; i--) {
                    for (int j = tiles[i].length - 1 - MAP_BUFFER; j >= 0; j--) {
                        tiles[i][j + MAP_BUFFER] = tiles[i][j];
                        if (j <= MAP_BUFFER) {
                            tiles[i][j] = snow;
                        }
                    }
                }
                break;
            case Constants.RIGHT:
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
                System.out.println("Down");
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
