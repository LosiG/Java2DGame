package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  private boolean upPressed;
  private boolean downPressed;
  private boolean leftPressed;
  private boolean rightPressed;
  private boolean spaceBarPressed;

  public boolean isUpPressed() {
    return upPressed;
  }

  public boolean isDownPressed() {
    return downPressed;
  }

  public boolean isLeftPressed() {
    return leftPressed;
  }

  public boolean isRightPressed() {
    return rightPressed;
  }

  public boolean isSpacebarPressed() {
    return spaceBarPressed;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_SPACE -> spaceBarPressed = true;
      case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = true;
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = true;
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = true;
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_SPACE -> spaceBarPressed = false;
      case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = false;
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = false;
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }
}
