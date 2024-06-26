package main;

import java.awt.Graphics2D;

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
        graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
    }
}
