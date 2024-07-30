package main.world;

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
    BufferedImage img;

    public Tile(Integer sizeX, Integer sizeY, String type) {
        this.images = Arrays.asList("grass_tile.png");
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.type = type;
        try {
            String tileImage = "assets/" + this.images.getFirst();
            if (type == "LAVA") {
                tileImage = "assets/lava_tile.png";
            }
            if (type == "SNOW") {
                tileImage = "assets/snow_tile.png";
            }
            img = ImageIO.read(
                    new File(tileImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics2D graphic, Integer currentX, Integer currentY) {
        if (img != null) {
            graphic.drawImage(img, currentX, currentY, null);
        } else {
            graphic.fillRect(currentX, currentY, this.sizeX, this.sizeY);
        }

    }

}
