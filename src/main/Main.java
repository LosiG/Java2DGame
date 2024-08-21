package main;

import javax.swing.JFrame;

import main.ui.GameOverPanel;
import main.ui.GamePanel;
import main.ui.MainMenuPanel;

public class Main {

  private static GamePanel gamePanel;
  private static MainMenuPanel mainMenuPanel;
  private static GameOverPanel gameOverPanel;
  private static JFrame window;

  public static void main(String[] args) {
    window = new JFrame("2D Java Game");
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setResizable(false);

    mainMenu();
    window.setLocationRelativeTo(null);
    window.setVisible(true);
  }

  public static void startGame(){
    window.getContentPane().removeAll();
    gamePanel = new GamePanel();
    window.add(gamePanel);
    window.pack();
    gamePanel.grabFocus();
    gamePanel.startGameThread();
  }

  public static void gameOver(){
    window.getContentPane().removeAll();
    gameOverPanel = new GameOverPanel();
    window.add(gameOverPanel);
    window.pack();
  }

  public static void mainMenu(){
    window.getContentPane().removeAll();
    mainMenuPanel = new MainMenuPanel();
    window.add(mainMenuPanel);
    window.pack();
  }
}
