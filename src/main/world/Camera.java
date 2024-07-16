package world;

import java.util.ArrayList;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import ui.GamePanel;
import lib.Costants;

public class Camera {
    ArrayList<Player> player;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    Tile[][] tiles;
    Terrain terrain;

    public Camera(
            ArrayList<Player> player, ArrayList<Projectile> projectiles,
            ArrayList<Enemy> enemies, Terrain terrain) {
        this.enemies = enemies;
        this.player = player;
        this.projectiles = projectiles;
        this.tiles = terrain.getTiles();
        this.terrain = terrain;
    }

    public void moveEverything(String direction) {
        moveEntity(direction, enemies);
        moveEntity(direction, player);
        switch (direction) {
            case Costants.UP:
                terrain.setYMapPos(player.getFirst().speed);
                break;
            case Costants.DOWN:
                terrain.setYMapPos(-player.getFirst().speed);
                break;
            case Costants.LEFT:
                terrain.setXMapPos(-player.getFirst().speed);
                break;
            case Costants.RIGHT:
                terrain.setXMapPos(player.getFirst().speed);
                break;

            default:
                break;
        }
    }

    public <Moveable extends Entity> void moveEntity(String direction, ArrayList<Moveable> moveables) {
        for (Moveable moveable : moveables) {
            switch (direction) {
                case Costants.UP:
                    moveable.currentY += moveable.speed;
                    break;
                case Costants.DOWN:
                    moveable.currentY -= moveable.speed;
                    break;
                case Costants.LEFT:
                    moveable.currentX -= moveable.speed;
                    break;
                case Costants.RIGHT:
                    moveable.currentX += moveable.speed;
                    break;

                default:
                    break;
            }
        }
    }
}
