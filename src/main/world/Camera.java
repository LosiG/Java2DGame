package main.world;

import java.util.ArrayList;

import main.entities.Enemy;
import main.entities.Entity;
import main.entities.Experience;
import main.entities.Player;
import main.entities.Projectile;
import main.lib.Constants;

public class Camera {
    ArrayList<Player> player;
    ArrayList<Projectile> projectiles;
    ArrayList<Enemy> enemies;
    ArrayList<Experience> experiences;
    Tile[][] tiles;
    Terrain terrain;

    public Camera(
            ArrayList<Player> player, ArrayList<Projectile> projectiles,
            ArrayList<Enemy> enemies, Terrain terrain,
            ArrayList<Experience> experience) {
        this.enemies = enemies;
        this.player = player;
        this.experiences = experience;
        this.projectiles = projectiles;
        this.tiles = terrain.getTiles();
        this.terrain = terrain;
    }

    public void moveEverything(String direction) {
        moveEntity(direction, enemies, player.getFirst().speed);
        moveEntity(direction, player, player.getFirst().speed);
        moveEntity(direction, experiences, player.getFirst().speed);
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
                    moveable.currentY += speed;
                    break;
                case Constants.DOWN:
                    moveable.currentY -= speed;
                    break;
                case Constants.LEFT:
                    moveable.currentX -= speed;
                    break;
                case Constants.RIGHT:
                    moveable.currentX += speed;
                    break;

                default:
                    break;
            }
        }
    }
}
