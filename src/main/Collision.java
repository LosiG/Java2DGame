package main;

import java.util.ArrayList;

public class Collision {
    Player player;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    long lastHitTook = System.nanoTime();
    long lastShotLanded = System.nanoTime();


    public Collision( Player player, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        this.player = player;
        this.projectiles = projectiles;
    }

    public void checkForCollisions() {

        for (Enemy enemy : enemies) {
            if (enemy.currentX >= player.currentX &&
                    enemy.currentX <= player.currentX + player.spriteX &&
                    enemy.currentY >= player.currentY &&
                    enemy.currentY <= player.currentY + player.spriteY &&
                    (System.nanoTime() - lastHitTook) > GamePanel.ONE_SECOND / 2) {
                System.out.println("Player took dmg");
                lastHitTook = System.nanoTime();
            }
            for (Projectile projectile : projectiles) {
                if (projectile.currentX >= enemy.currentX &&
                        projectile.currentX <= enemy.currentX + player.spriteX &&
                        projectile.currentY >= enemy.currentY &&
                        projectile.currentY <= enemy.currentY + player.spriteY &&
                        (System.nanoTime() - lastShotLanded) > GamePanel.ONE_SECOND / 2) {
                    System.out.println("Enemy hit");
                    lastShotLanded = System.nanoTime();
                }

            }
        }

    }

    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void addProjectile(Projectile projectile) {
        enemies.add(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        enemies.remove(projectile);
    }

}
