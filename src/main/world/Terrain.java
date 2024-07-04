package main.world;

import java.awt.Graphics2D;

public class Terrain {
    Tile[][] tiles;
    Integer tileSizeX;
    Integer tileSizeY;

    public Terrain(Integer screenColumns, Integer screenRows, String type, Integer tileSizeX, Integer tileSizeY) {
        this.tileSizeX = tileSizeX;
        this.tileSizeY = tileSizeY;
        this.tiles = new Tile[screenRows][screenColumns];
        switch (type) {
            case "GRASS":
                for (int i = 0; i < screenRows; i++) {
                    for (int j = 0; j < screenColumns; j++) {
                        tiles[i][j] = new Tile(tileSizeX, tileSizeY, type);
                    }
                }
                break;

            default:
                this.tiles = new Tile[screenColumns][screenRows];
                break;
        }

    }

    public void paint(Graphics2D grapich) {
        Integer currentX = 0;
        Integer currentY = 0;

        for (Tile[] tilesRow : tiles) {
            for (Tile tile : tilesRow) {
                tile.paint(grapich, currentX, currentY);
                currentX += this.tileSizeX;
            }
            currentX = 0;
            currentY += this.tileSizeY;

        }
    }
}
