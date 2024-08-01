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
    public String getObjectName() {
        return "Experience";
    }

    @Override
    public void paint(Graphics2D graphic) {
        graphic.setColor(Color.orange);
        graphic.fillRoundRect(currentX, currentY, spriteX, spriteY, 5, 5);
    }
}
