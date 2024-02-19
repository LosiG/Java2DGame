package main;

public class Player {
    private Integer currentX;
    private Integer currentY;
    private Integer spriteY;
    private Integer spriteX;
    private Integer speed;
    private Integer acceleration;

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

    public void moveUp() {
        this.currentY -= this.getMovementSpeed();
    }

    public void moveDown() {
        this.currentY += this.getMovementSpeed();
    }

    public void moveLeft() {
        this.currentX -= this.getMovementSpeed();
    }

    public void moveRight() {
        this.currentX += this.getMovementSpeed();
    }

    public Integer getCurrentX() {
        return currentX;
    }

    public Integer getCurrentY() {
        return currentY;
    }

    public Integer getSpriteY() {
        return spriteY;
    }

    public Integer getSpriteX() {
        return spriteX;
    }

    public Integer getSpeed() {
        return speed;
    }

    public Integer getAcceleration() {
        return acceleration;
    }

    public void setCurrentX(Integer currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(Integer currentY) {
        this.currentY = currentY;
    }

    public void setSpriteY(Integer spriteY) {
        this.spriteY = spriteY;
    }

    public void setSpriteX(Integer spriteX) {
        this.spriteX = spriteX;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public void setAcceleration(Integer acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public String toString() {
        return "Player [currentX=" + currentX + ", currentY=" + currentY + ", spriteY=" + spriteY + ", spriteX="
                + spriteX + ", speed=" + speed + ", acceleration=" + acceleration + "]";
    }

}
