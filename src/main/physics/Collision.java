package physics;

import java.util.ArrayList;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import ui.GamePanel;

public class Collision {
    ArrayList<Player> player;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    long lastHitTook = System.nanoTime();
    long lastShotLanded = System.nanoTime();

    public Collision(ArrayList<Player> player, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies) {
        this.enemies = enemies;
        this.player = player;
        this.projectiles = projectiles;
    }

    public void checkForCollisions() {
        entitiesCollided(projectiles, enemies);
        entitiesCollided(enemies, player);
    }

    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public <DamageDealer extends Entity, Tank extends Entity> void entitiesCollided(
            ArrayList<DamageDealer> damageDealers, ArrayList<Tank> tanks) {
        for (DamageDealer damageDealer : damageDealers) {
            for (Tank tank : tanks) {
                if (entityCollided(damageDealer, tank)) {
                    Collision.resolveDamage(damageDealer, tank);
                }
            }
        }
    }

    static <DamageDealer extends Entity, Tank extends Entity> void resolveDamage(DamageDealer damageDealer, Tank tank) {
        if (System.nanoTime() - tank.lastHitTook > (tank.invincibility * GamePanel.ONE_SECOND) / 2) {
            System.out.print(tank.getObjectName() + " took " + damageDealer.damage + " dmg\n");
            tank.currentHp -= damageDealer.damage;
            tank.lastHitTook = System.nanoTime();
            tank.lastInvincibilityRender = System.nanoTime();
        }
    }

    static <DamageDealer extends Entity, Tank extends Entity> boolean entityCollided(DamageDealer damageDealer,
            Tank tank) {
        return damageDealer.currentX >= tank.currentX &&
                damageDealer.currentX <= tank.currentX + tank.spriteX &&
                damageDealer.currentY >= tank.currentY &&
                damageDealer.currentY <= tank.currentY + tank.spriteY;
    }
}
