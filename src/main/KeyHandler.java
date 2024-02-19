package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  private boolean upPressed;
  private boolean downPressed;
  private boolean leftPressed;
  private boolean rightPressed;

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

  @Override
  public void keyTyped(KeyEvent e) {
    throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
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
      case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = false;
      case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = false;
      case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = false;
      case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = false;
    }
  }
}
