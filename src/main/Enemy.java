package main;

import java.awt.Graphics2D;

public class Enemy extends Entity {

    Integer score;

    public Enemy(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, int hp, int damage, int score) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteY = spriteY;
        this.spriteX = spriteX;
        this.speed = speed;
        this.acceleration = acceleration;
        this.hp = hp;
        this.damage = damage;
        this.invincibility = 1;
        this.lastInvincibilityRender = 0;
        this.lastDamageDone = 0;
        this.lastHitTook = 0;
        this.score = score;
    }

    public void moveToPlayer(Integer playerX, Integer playerY) {
        Integer deltaX = this.currentX - playerX;
        Integer deltaY = this.currentY - playerY;
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                moveLeft(speed);
            } else {
                moveRight(speed);
            }
            if (deltaY > 0) {
                moveDown(speed / 2);
            } else {
                moveUp(speed / 2);
            }
        } else {
            if (deltaY > 0) {
                moveDown(speed);
            } else {
                moveUp(speed);
            }
            if (deltaX > 0) {
                moveLeft(speed / 2);
            } else {
                moveRight(speed / 2);
            }

        }
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
    String getObjectName() {
        return "Enemy";
    }

    @Override
    public String toString() {
        return "Enemy [currentX=" + currentX + ", score=" + score + ", currentY=" + currentY + ", spriteY=" + spriteY
                + ", spriteX=" + spriteX + ", speed=" + speed + ", acceleration=" + acceleration + ", hp=" + hp
                + ", damage=" + damage + ", lastHitTook=" + lastHitTook + ", lastDamageDone=" + lastDamageDone
                + ", getClass()=" + getClass() + ", getObjectName()=" + getObjectName() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    @Override
    void paint(Graphics2D graphic) {
        graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
    }

}
