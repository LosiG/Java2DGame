package main;

public class Projectile extends Entity {
    String direction;
    static final String UP = "UP";
    static final String DOWN = "DOWN";
    static final String LEFT = "LEFT";
    static final String RIGHT = "RIGHT";

    public Projectile(Integer currentX, Integer currentY, Integer spriteY, Integer spriteX, Integer speed,
            Integer acceleration, Integer hp, String direction) {
        this.currentX = currentX;
        this.currentY = currentY;
        this.spriteX = spriteX;
        this.spriteY = spriteY ;
        this.speed = speed;
        this.acceleration = acceleration;
        this.hp = hp;
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
