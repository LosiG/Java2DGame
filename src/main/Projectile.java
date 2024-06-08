package main;

public class Projectile extends Enemy {
    String direction;
    static final String UP = "UP";
    static final String DOWN = "DOWN";
    static final String LEFT = "LEFT";
    static final String RIGHT = "RIGHT";

    public Projectile(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, String direction) {
        super(currentX, currentY, spriteY, spriteX, speed, acceleration);
        this.direction = direction;
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
}
