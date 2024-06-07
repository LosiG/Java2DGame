package main;

public class Player extends Entity {

    public Player(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteY = spriteY;
        this.spriteX = spriteX;
        this.speed = speed;
        this.acceleration = acceleration;
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

    @Override
    public String toString() {
        return "Player [currentX=" + currentX + ", currentY=" + currentY + ", spriteY=" + spriteY + ", spriteX="
                + spriteX + ", speed=" + speed + ", acceleration=" + acceleration + "]";
    }

}
