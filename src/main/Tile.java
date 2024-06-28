package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

public class Tile {
    List<String> images;
    Integer sizeX;
    Integer sizeY;
    String type;

    public Tile(Integer sizeX, Integer sizeY, String type) {
        this.images = Arrays.asList("grass_tile.png");
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.type = type;
    }

    public void paint(Graphics2D graphic, Integer currentX, Integer currentY) {
        BufferedImage img = null;
        try {
            String tileImage = "assets/" + this.images.getFirst();
            img = ImageIO.read(
                    new File(tileImage));
            graphic.drawImage(img, currentX, currentY, null);
        } catch (IOException e) {
            graphic.fillRect(currentX, currentY, this.sizeX, this.sizeY);
            e.printStackTrace();
        }
    }

}
