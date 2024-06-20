package main;

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
        this.lastDamageDone = System.nanoTime();
        this.lastHitTook = System.nanoTime();
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
}
