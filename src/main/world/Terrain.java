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
    Integer boundUp = 0;
    Integer boundDown = 0;
    Tile snow;

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
        boundRight = ((colNum - 1 - MAP_BUFFER * 4 / 3) * -tileSizeX);
        boundUp = MAP_BUFFER / 3 * -tileSizeY;
        boundDown = ((rowNum - 1 - MAP_BUFFER * 4 / 3) * -tileSizeY);
        
        this.tiles = new Tile[rowNum][colNum];
        snow = new Tile(tileSizeX, tileSizeY, "SNOW");
        
        Random r = new Random();
        int low = 1;
        int high = 100;
        switch (type) {
            case "GRASS":
                for (int i = 0; i < rowNum; i++) {
                    for (int j = 0; j < colNum; j++) {
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
        
        if (xMapPos <= boundRight) {
            xMapPos = centerX;
            resetMapPos(Constants.LEFT);
        }

        if (yMapPos >= boundUp) {
            yMapPos = centerY;
            resetMapPos(Constants.DOWN);
        }
        
        if (yMapPos <= boundDown) {
            yMapPos = centerY;
            System.out.println(boundDown);
            resetMapPos(Constants.UP);
        }
    }

    public void resetMapPos(String direction) {
        slideMapTiles(direction);
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
                            tiles[i][j] = tiles[i][j + MAP_BUFFER * 2/3];
                        }
                    }
                }
                break;

            case Constants.DOWN:
                for (int i = tiles.length - 1 - MAP_BUFFER; i >= 0; i--) {
                    for (int j = tiles[i].length - 1; j >= 0; j--) {
                        tiles[i + MAP_BUFFER * 2/3][j] = tiles[i][j];
                        if (i <= MAP_BUFFER) {
                            tiles[i][j] = snow;
                        }
                    }
                }
                break;
                
            case Constants.UP:
                for (int i = 0; i < tiles.length; i++) {
                    for (int j = 0; j < tiles[i].length; j++) {
                        if (i >= tiles.length - MAP_BUFFER) {
                            tiles[i][j] = snow;
                        } else {
                            tiles[i][j] = tiles[i + MAP_BUFFER * 2/3][j];
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
}
