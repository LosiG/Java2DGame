package main.entities;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.ui.GamePanel;
import main.lib.Constants.Direction;

public class Enemy extends Entity {

    public Integer score;

    public Enemy(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, int hp, int damage, int score) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteY = spriteY;
        this.spriteX = spriteX;
        this.speed = speed;
        this.acceleration = acceleration;
        this.currentHp = this.maxHp = hp;
        this.damage = damage;
        this.invincibility = 1;
        this.lastInvincibilityRender = 0;
        this.lastDamageDone = 0;
        this.lastHitTook = 0;
        this.score = score;
        try {
            String enemyImage = "assets/enemy_1.png";
            img = ImageIO.read(
                    new File(enemyImage));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void moveToPlayer(Integer playerX, Integer playerY) {
        Integer deltaX = this.currentX - playerX;
        Integer deltaY = this.currentY - playerY;
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                move(speed, Direction.LEFT);
            } else {
                move(speed, Direction.RIGHT);
            }
            if (deltaY > 0) {
                move(speed / 2, Direction.UP);
            } else {
                move(speed / 2, Direction.DOWN);
            }
        } else {
            if (deltaY > 0) {
                move(speed, Direction.UP);
            } else {
                move(speed, Direction.DOWN);
            }
            if (deltaX > 0) {
                move(speed / 2, Direction.LEFT);
            } else {
                move(speed / 2, Direction.RIGHT);
            }
        }
    }

    @Override
    public String getObjectName() {
        return "Enemy";
    }

    @Override
    public String toString() {
        return "Enemy [currentX=" + currentX + ", score=" + score + ", currentY=" + currentY + ", spriteY=" + spriteY
                + ", spriteX=" + spriteX + ", speed=" + speed + ", acceleration=" + acceleration
                + ", damage=" + damage + ", lastHitTook=" + lastHitTook + ", lastDamageDone=" + lastDamageDone
                + ", getClass()=" + getClass() + ", getObjectName()=" + getObjectName() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    @Override
    public void paint(Graphics2D graphic) {

        if (System.nanoTime() - this.lastHitTook > GamePanel.ONE_SECOND * this.invincibility) {
            this.draw(graphic);
        } else {
            if (System.nanoTime() - this.lastInvincibilityRender < GamePanel.ONE_SECOND / 4) {
                this.draw(graphic);
            } else if (System.nanoTime() - this.lastInvincibilityRender > GamePanel.ONE_SECOND / 3)
                this.lastInvincibilityRender = System.nanoTime();
        }
    }

    public void draw(Graphics2D graphic) {
        paintHpBar(this, graphic);
        if (img != null) {
            graphic.drawImage(img, this.currentX, this.currentY, null);
        } else {
            graphic.fillRect(this.currentX, this.currentY, this.spriteX, this.spriteY);
        }
    }

}
