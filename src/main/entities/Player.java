package main.entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Player extends Entity {
    Integer exp;
    Integer lvl;
    Integer strength;
    Integer dexterity;

    public Player(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, int hp) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteY = spriteY;
        this.spriteX = spriteX;
        this.speed = speed;
        this.acceleration = acceleration;
        this.currentHp = this.maxHp = hp;
        this.invincibility = 1;
        this.exp = 0;
        this.lvl = 1;
        this.strength = 10;
        this.dexterity = 10;
        try {
            String playerImage = "assets/player.png";
            img = ImageIO.read(
                    new File(playerImage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Integer getMovementSpeed() {
        return this.speed * this.acceleration;
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

    public int getPlayerPosX() {
        return this.currentX;
    }

    public int getPlayerPosY() {
        return this.currentY;
    }

    @Override
    public String toString() {
        return "Player [currentX=" + currentX + ", currentY=" + currentY + ", spriteY=" + spriteY + ", spriteX="
                + spriteX + ", speed=" + speed + ", acceleration=" + acceleration + "]";
    }

    @Override
    public String getObjectName() {
        return "Player";
    }

    @Override
    public void paint(Graphics2D graphic) {
        paintHpBar(this, graphic);
        // Draw the body
        if (img != null) {
            graphic.drawImage(img, this.currentX, this.currentY, null);
        } else {
            graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
        }
    }

    public void levelUp() {
        this.maxHp += 3;
        this.currentHp = maxHp;
        this.strength += 1;
        this.dexterity += 1;
        this.lvl += 1;
        this.exp = 0;
    }
}
