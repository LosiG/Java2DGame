package world;

import java.util.ArrayList;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Projectile;
import ui.GamePanel;
import lib.Constants;

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
        moveEntity(direction, enemies, player.getFirst().speed);
        moveEntity(direction, player, player.getFirst().speed);
        switch (direction) {
            case Constants.UP:
                terrain.setYMapPos(player.getFirst().speed);
                break;
            case Constants.DOWN:
                terrain.setYMapPos(-player.getFirst().speed);
                break;
            case Constants.LEFT:
                terrain.setXMapPos(-player.getFirst().speed);
                break;
            case Constants.RIGHT:
                terrain.setXMapPos(player.getFirst().speed);
                break;

            default:
                break;
        }
    }

    public <Moveable extends Entity> void moveEntity(String direction, ArrayList<Moveable> moveables, Integer speed) {
        for (Moveable moveable : moveables) {
            switch (direction) {
                case Constants.UP:
                    moveable.currentY += moveable.speed;
                    break;
                case Constants.DOWN:
                    moveable.currentY -= moveable.speed;
                    break;
                case Constants.LEFT:
                    moveable.currentX -= moveable.speed;
                    break;
                case Constants.RIGHT:
                    moveable.currentX += moveable.speed;
                    break;

                default:
                    break;
            }
        }
    }
}
