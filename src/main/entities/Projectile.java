package main.entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Entity {
    public Direction direction;

    public Projectile(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, Integer damage, Direction direction) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteX = spriteX;
        this.spriteY = spriteY;
        this.speed = speed;
        this.acceleration = acceleration;
        this.damage = damage;
        this.direction = direction;
        this.lastDamageDone = System.nanoTime();
        this.invincibility = 0;
        try {
            String projectileImage = "assets/projectile.png";
            img = ImageIO.read(
                    new File(projectileImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move() {
        super.move(this.speed, this.direction);
    }

    @Override
    public String getObjectName() {
        return "Projectile";
    }

    @Override
    public void paint(Graphics2D graphic) {
        if (img != null) {
            graphic.drawImage(img, this.currentX, this.currentY, null);
        } else {
            graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
        }

    }
}
