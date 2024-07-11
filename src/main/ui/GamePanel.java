package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.entities.Enemy;
import main.entities.Experience;
import main.entities.Player;
import main.entities.Projectile;
import main.input.KeyHandler;
import main.physics.Collision;
import main.world.Terrain;

import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable {

  static final Integer ORIGINAL_TILESIZE = 16;
  public static final double ONE_SECOND = 1000000000.00;
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

  static final Integer PLAYER_HP = 100;
  static final Integer PROJECTILE_DMG = 10;
  static final Integer MOB_HP = 50;
  static final Integer MOB_DMG = 10;

  static final Integer FPS = 60;

  boolean pause = false;
  boolean gameOver = false;
  KeyHandler keyH = new KeyHandler();
  Thread gameThread;
  long lastProjectileAdded = System.nanoTime();
  long lastPause = System.nanoTime();
  Integer score = 0;
  long lastEnemySpawn = System.currentTimeMillis();

  Player player = new Player(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE, 3, 1, PLAYER_HP);
  Enemy enemy1 = new Enemy(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE, 2, 1, MOB_HP, MOB_DMG, 10);
  Enemy enemy2 = new Enemy(400, 400, TILE_SIZE, TILE_SIZE, 1, 1, MOB_HP, MOB_DMG, 10);
  ArrayList<Enemy> enemies = new ArrayList<>(Arrays.asList(enemy1, enemy2));
  ArrayList<Player> players = new ArrayList<>(Arrays.asList(player));
  ArrayList<Projectile> projectiles = new ArrayList<>();
  ArrayList<Experience> experiences = new ArrayList<>();
  Terrain terrain = new Terrain(MAX_SCREEN_COLUMN, MAX_SCREEN_ROW, "GRASS", TILE_SIZE, TILE_SIZE);
  Collision collision = new Collision(players, projectiles, enemies);

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
    if (gameOver) {
      return;
    }
    if (keyH.isSpacebarPressed() && waitForSeconds(lastPause, 0.5)) {
      pause = !pause;
      this.lastPause = System.nanoTime();
    }
    if (pause) {
      return;
    }

    if (keyH.isUpPressed()) {
      player.moveUp(player.speed * Math.round(player.dexterity / 10f));
    }
    if (keyH.isDownPressed()) {
      player.moveDown(player.speed * Math.round(player.dexterity / 10f));
    }
    if (keyH.isLeftPressed()) {
      player.moveLeft(player.speed * Math.round(player.dexterity / 10f));
    }
    if (keyH.isRightPressed()) {
      player.moveRight(player.speed * Math.round(player.dexterity / 10f));
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
                PROJECTILE_WIDTH, PROJECTILE_SPEED, PROJECTILE_ACCELERATION,
                PROJECTILE_DMG * Math.round(player.strength / 10f), direction));
        this.lastProjectileAdded = System.nanoTime();
      }
    }
    Iterator<Enemy> enemyIterator = enemies.iterator();
    while (enemyIterator.hasNext()) {
      Enemy enemy = enemyIterator.next();
      enemy.moveToPlayer(player.currentX, player.currentY);
      if (enemy.currentHp <= 0) {
        score += enemy.score;
        experiences.add(new Experience(enemy.currentX, enemy.currentY, 10));
        enemyIterator.remove();
      }

    }
    if (enemies.size() < 100 && System.currentTimeMillis() - lastEnemySpawn > 1000) {
      enemies.add(generateRandomEnemy());
      lastEnemySpawn = System.currentTimeMillis();
    }
    Iterator<Projectile> projectileIterator = projectiles.iterator();
    while (projectileIterator.hasNext()) {
      Projectile projectile = projectileIterator.next();
      projectile.move(projectile.direction);
      if (projectile.currentX > SCREEN_WIDTH ||
          projectile.currentY > SCREEN_HEIGHT ||
          projectile.currentX < 0 ||
          projectile.currentY < 0) {
        projectileIterator.remove();
      }
    }
    collision.checkForCollisions();

    if (player.currentHp <= 0) {
      gameOver = true;
    }
  }

  private Enemy generateRandomEnemy() {
    return new Enemy(ThreadLocalRandom.current().nextInt(0, SCREEN_WIDTH),
        ThreadLocalRandom.current().nextInt(0, SCREEN_HEIGHT), TILE_SIZE, TILE_SIZE,
        ThreadLocalRandom.current().nextInt(1, 3),
        1,
        ThreadLocalRandom.current().nextInt(20, 100),
        ThreadLocalRandom.current().nextInt(5, 10), 10);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    terrain.paint(g2);
    g2.setColor(Color.MAGENTA);

    player.paint(g2);

    g2.setColor(Color.RED);
    enemies.forEach(enemy -> enemy.paint(g2));

    experiences.forEach(experience -> experience.paint(g2));

    g2.setColor(Color.yellow);
    projectiles.forEach(projectile -> projectile.paint(g2));

    g2.setColor(Color.WHITE);

    String scoreTitle = "Score: " + score.toString();
    g2.drawString(scoreTitle, 700, 20);

    if (gameOver) {
      BufferedImage base = null;
      try {
        base = ImageIO.read(new File("assets\\gameOverScreen.png"));
      } catch (IOException e) {
        e.printStackTrace();
      }
      g2.drawImage(base, null, getFocusCycleRootAncestor());
    }

    g2.dispose();
  }

  public static boolean waitForSeconds(long waitParam, double howLongToWait) {
    if (System.nanoTime() - waitParam > howLongToWait * ONE_SECOND) {
      waitParam = System.nanoTime();
      return true;
    }
    return false;
  }
}
