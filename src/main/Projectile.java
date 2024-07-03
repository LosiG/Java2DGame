package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Projectile extends Entity {
    String direction;
    static final String UP = "UP";
    static final String DOWN = "DOWN";
    static final String LEFT = "LEFT";
    static final String RIGHT = "RIGHT";

    public Projectile(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, Integer damage, String direction) {
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

    public void moveUp(Integer value) {
        this.currentY -= value;
    }

    public void moveDown(Integer value) {
        this.currentY += value;
    }

    public void moveLeft(Integer value) {
        this.currentX -= value;
    }

    public void moveRight(Integer value) {
        this.currentX += value;
    }

    public void move(String direction) {
        switch (direction) {
            case UP:
                moveUp(this.speed);
                break;
            case DOWN:
                moveDown(this.speed);
                break;
            case LEFT:
                moveLeft(this.speed);
                break;
            case RIGHT:
                moveRight(this.speed);
                break;
            default:
                break;
        }
    }

    @Override
    String getObjectName() {
        return "Projectile";
    }

    @Override
    void paint(Graphics2D graphic) {
        if (img != null) {
            graphic.drawImage(img, this.currentX, this.currentY, null);
        } else {
            graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
        }

    }
}
