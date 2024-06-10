package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {

  static final Integer ORIGINAL_TILESIZE = 16;
  static final double ONE_SECOND = 1000000000.00;
  static final double FIRE_RATE = 0.25;
  static final Integer SCALE = 3;
  static final Integer TILE_SIZE = ORIGINAL_TILESIZE * SCALE;
  static final Integer MAX_SCREEN_COLUMN = 16;
  static final Integer MAX_SCREEN_ROW = 12;
  static final Integer SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
  static final Integer SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

  static final Integer PROJECTILE_WIDTH = 20;
  static final Integer PROJECTILE_HEIGHT = 20;
  static final Integer PROJECTILE_SPEED = 25;
  static final Integer PROJECTILE_ACCELERATION = 1;

  static final Integer FPS = 60;

  boolean pause = false;
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  long lastProjectileAdded = System.nanoTime(); 
  long lastPause = System.nanoTime(); 

  Player player = new Player(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE, 3, 1);
  Enemy enemy1 = new Enemy(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE, 2, 1);
  Enemy enemy2 = new Enemy(400, 400, TILE_SIZE, TILE_SIZE, 1, 1);
  ArrayList<Enemy> enemies = new ArrayList<>(Arrays.asList(enemy1, enemy2));
  ArrayList<Projectile> projectiles = new ArrayList<>();
  Collision collision = new Collision(player, projectiles, enemies);


  public GamePanel() {
    this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
    this.setBackground(Color.BLACK);
    this.setDoubleBuffered(true);
    this.addKeyListener(keyH);
    this.setFocusable(true);
  }

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000.00 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;
    while (gameThread != null) {
      update();

      repaint();

      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime = remainingTime / 1000000;

        if (remainingTime < 0) {
          remainingTime = 0;
        }

        Thread.sleep((long) remainingTime);

        nextDrawTime += drawInterval;
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void update() {
    if (keyH.isSpacebarPressed() && waitForSeconds(lastPause, 0.5)) {
      pause = !pause;
      this.lastPause = System.nanoTime();
    }
    if (pause) {
      return;
    }

    if (keyH.isUpPressed()) {
      player.moveUp(player.speed);
    }
    if (keyH.isDownPressed()) {
      player.moveDown(player.speed);
    }
    if (keyH.isLeftPressed()) {
      player.moveLeft(player.speed);
    }
    if (keyH.isRightPressed()) {
      player.moveRight(player.speed);
    }

    if (keyH.isShootDownPressed() || keyH.isShootLeftPressed() ||
        keyH.isShootRightPressed() || keyH.isShootUpPressed()) {
      String direction = "";
      if (keyH.isShootDownPressed()) {
        direction = Projectile.DOWN;
      }
      if (keyH.isShootUpPressed()) {
        direction = Projectile.UP;
      }
      if (keyH.isShootLeftPressed()) {
        direction = Projectile.LEFT;
      }
      if (keyH.isShootRightPressed()) {
        direction = Projectile.RIGHT;
      }
      if (waitForSeconds(lastProjectileAdded, FIRE_RATE)) {
        projectiles.add(
            new Projectile(
                player.currentX, player.currentY, PROJECTILE_HEIGHT,
                PROJECTILE_WIDTH, PROJECTILE_SPEED, PROJECTILE_ACCELERATION, direction));
        this.lastProjectileAdded = System.nanoTime();
      }
    }

    enemy1.moveToPlayer(player.currentX, player.currentY);
    enemy2.moveToPlayer(player.currentX, player.currentY);
    collision.checkForCollisions();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.MAGENTA);

    g2.fillRect(player.currentX, player.currentY, player.spriteX, player.spriteY);

    g2.setColor(Color.RED);

    g2.fillRect(enemy1.currentX, enemy1.currentY, enemy1.spriteX, enemy1.spriteY);

    g2.fillRect(enemy2.currentX, enemy2.currentY, enemy2.spriteX, enemy2.spriteY);

    g2.setColor(Color.yellow);
    for (Iterator<Projectile> it = projectiles.iterator(); it.hasNext();) {
      Projectile projectile = it.next();
      if (projectile.currentX > SCREEN_WIDTH ||
          projectile.currentY > SCREEN_HEIGHT ||
          projectile.currentX < 0 ||
          projectile.currentY < 0) {
        it.remove();
      }
      g2.fillRect(projectile.currentX, projectile.currentY, projectile.spriteX, projectile.spriteY);
      projectile.move(projectile.direction);
    }

    g2.dispose();
  }

  public static boolean waitForSeconds(long waitParam, double howLongToWait) {
    if (System.nanoTime() - waitParam > howLongToWait * ONE_SECOND) {
      return true;
    }
    return false;
  }
}
