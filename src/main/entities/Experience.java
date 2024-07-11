package main.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class Experience extends Entity {
    public Integer value;

    public Experience(Integer x, Integer y, Integer value) {
        this.currentX = x;
        this.currentY = y;
        this.value = value;
        this.spriteX = 16;
        this.spriteY = 16;
        this.speed = 1;
        this.acceleration = 1;
        this.currentHp = this.maxHp = 1;
        this.invincibility = 1;

    }

    @Override
    void moveUp(Integer value) {
        this.currentY += value;
    }

    @Override
    void moveDown(Integer value) {
        this.currentY -= value;
    }

    @Override
    void moveLeft(Integer value) {
        this.currentX -= value;
    }

    @Override
    void moveRight(Integer value) {
        this.currentX += value;
    }

    @Override
    public String getObjectName() {
        return "Experience";
    }

    @Override
    public void paint(Graphics2D graphic) {
        graphic.setColor(Color.orange);
        graphic.fillRoundRect(currentX, currentY, spriteX, spriteY, 5, 5);
    }
}
