package main.entities;

import java.awt.Color;
import java.awt.image.BufferedImage;

import main.lib.Constants.Direction;

public abstract class Entity {
    public Integer currentX;
    public Integer currentY;
    public Integer spriteY;
    public Integer spriteX;
    public Integer speed;
    public Integer acceleration;
    public Integer maxHp;
    public Integer currentHp;
    public Integer damage;
    public Integer invincibility;
    public long lastInvincibilityRender;
    public long lastHitTook;
    public long lastDamageDone;
    public BufferedImage img;


    public void move(Integer value, Direction direction) {
        switch (direction) {
            case Direction.LEFT:
                this.currentX -= value;
                break;
            case Direction.UP:
                this.currentY -= value;
                break;
            case Direction.RIGHT:
                this.currentX += value;
                break;
            case Direction.DOWN:
                this.currentY += value;
                break;
            default:
                System.out.println("How TF did u end down here");
                break;
        }
    }

    public abstract String getObjectName();

    public abstract void paint(java.awt.Graphics2D graphic);

    <T extends Entity> void paintHpBar(T entity, java.awt.Graphics2D grapich) {
        float currentHpRatio = (float) entity.currentHp / entity.maxHp;
        Integer currentHpLenght = Math.round(entity.spriteX * currentHpRatio);

        grapich.setColor(Color.red);
        grapich.fillRect(entity.currentX, entity.currentY - 8, entity.spriteX, 6);

        grapich.setColor(Color.green);
        grapich.fillRect(entity.currentX, entity.currentY - 8, currentHpLenght, 6);

    }
}
