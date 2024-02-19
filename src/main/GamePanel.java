package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

  static final Integer ORIGINAL_TILESIZE = 16;
  static final Integer SCALE = 3;
  static final Integer TILE_SIZE = ORIGINAL_TILESIZE * SCALE;
  static final Integer MAX_SCREEN_COLUMN = 16;
  static final Integer MAX_SCREEN_ROW = 12;
  static final Integer SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
  static final Integer SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

  static final Integer FPS = 60;

  KeyHandler keyH = new KeyHandler();
  Thread gameThread;

  Player player1 = new Player(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE, 3, 1);
  Player enemy = new Player(300, 300, TILE_SIZE, TILE_SIZE, 3, 1);

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
    if (keyH.isUpPressed()) {
      player1.moveUp();
    }
    if (keyH.isDownPressed()) {
      player1.moveDown();
    }
    if (keyH.isLeftPressed()) {
      player1.moveLeft();
    }
    if (keyH.isRightPressed()) {
      player1.moveRight();
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.GREEN);

    g2.fillRect(player1.getCurrentX(), player1.getCurrentY(), player1.getSpriteX(), player1.getSpriteY());

    g2.setColor(Color.RED);

    g2.fillRect(enemy.getCurrentX(), enemy.getCurrentY(), enemy.getSpriteX(), enemy.getSpriteY());

    g2.dispose();
  }
}
