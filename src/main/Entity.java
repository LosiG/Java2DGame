package main;

public abstract class Entity {
    Integer currentX;
    Integer currentY;
    Integer spriteY;
    Integer spriteX;
    Integer speed;
    Integer acceleration;
    Integer hp;
    Integer damage;
    Integer invincibility;
    long lastInvincibilityRender;
    long lastHitTook;
    long lastDamageDone;

    abstract void moveUp(Integer value);

    abstract void moveDown(Integer value);

    abstract void moveLeft(Integer value);

    abstract void moveRight(Integer value);

    abstract String getObjectName();

    abstract void paint(java.awt.Graphics2D graphic);
}
