package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public abstract class Entity {
    Integer currentX;
    Integer currentY;
    Integer spriteY;
    Integer spriteX;
    Integer speed;
    Integer acceleration;
    Integer maxHp;
    Integer currentHp;
    Integer damage;
    Integer invincibility;
    long lastInvincibilityRender;
    long lastHitTook;
    long lastDamageDone;
    BufferedImage img;

    abstract void moveUp(Integer value);

    abstract void moveDown(Integer value);

    abstract void moveLeft(Integer value);

    abstract void moveRight(Integer value);

    abstract String getObjectName();

    abstract void paint(java.awt.Graphics2D graphic);

    <T extends Entity> void paintHpBar(T entity, java.awt.Graphics2D grapich) {
        float currentHpRatio = (float) entity.currentHp / entity.maxHp;
        Integer currentHpLenght = Math.round(entity.spriteX * currentHpRatio);

        grapich.setColor(Color.red);
        grapich.fillRect(entity.currentX, entity.currentY - 8, entity.spriteX, 6);

        grapich.setColor(Color.green);
        grapich.fillRect(entity.currentX, entity.currentY - 8, currentHpLenght, 6);

    }
}
