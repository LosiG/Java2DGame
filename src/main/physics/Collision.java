package main.physics;

import java.util.ArrayList;
import java.util.Arrays;

import main.entities.Enemy;
import main.entities.Entity;
import main.entities.Experience;
import main.entities.Player;
import main.entities.Projectile;
import main.ui.GamePanel;

public class Collision {
    ArrayList<Player> player;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    ArrayList<Experience> experiences;
    long lastHitTook = System.nanoTime();
    long lastShotLanded = System.nanoTime();

    public Collision(ArrayList<Player> player, ArrayList<Projectile> projectiles, ArrayList<Enemy> enemies,
            ArrayList<Experience> experiences) {
        this.enemies = enemies;
        this.player = player;
        this.projectiles = projectiles;
        this.experiences = experiences;
    }

    public void checkForCollisions() {
        entitiesCollided(projectiles, enemies);
        entitiesCollided(enemies, player);
        entitiesCollided(player, experiences);
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
        Integer tankMinX = tank.currentX;
        Integer tankMinY = tank.currentY;
        Integer tankMaxX = tankMinX + tank.spriteX;
        Integer tankMaxY = tankMinY + tank.spriteY;

        Integer damageDealerMinX = damageDealer.currentX;
        Integer damageDealerMinY = damageDealer.currentY;
        Integer damageDealerMaxX = damageDealerMinX + damageDealer.spriteX;
        Integer damageDealerMaxY = damageDealerMinY + damageDealer.spriteY;

        boolean hasCollidedOnX = ((isFirstBetweenSecondAndThird(tankMinX, damageDealerMinX, damageDealerMaxX))
                || isFirstBetweenSecondAndThird(tankMaxX, damageDealerMinX, damageDealerMaxX))
                || (isFirstBetweenSecondAndThird(damageDealerMinX, tankMinX, tankMaxX)
                        || isFirstBetweenSecondAndThird(damageDealerMaxX, tankMinX, tankMaxX));

        boolean hasCollidedOnY = ((isFirstBetweenSecondAndThird(tankMinY, damageDealerMinY, damageDealerMaxY))
                || isFirstBetweenSecondAndThird(tankMaxY, damageDealerMinY, damageDealerMaxY))
                || (isFirstBetweenSecondAndThird(damageDealerMinY, tankMinY, tankMaxY)
                        || isFirstBetweenSecondAndThird(damageDealerMaxY, tankMinY, tankMaxY));

        return hasCollidedOnX
                && hasCollidedOnY;
    }

    public static boolean isFirstBetweenSecondAndThird(Integer a, Integer b, Integer c) {
        return b <= a && a <= c;
    }
}
