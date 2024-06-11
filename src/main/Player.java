package main;

public class Player extends Entity {

    public Player(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, int hp) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteY = spriteY;
        this.spriteX = spriteX;
        this.speed = speed;
        this.acceleration = acceleration;
        this.hp = hp;
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
    String getObjectName() {
        return "Player";
    }
}
