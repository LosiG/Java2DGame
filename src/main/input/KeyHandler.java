package main.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

  private boolean upPressed;
  private boolean downPressed;
  private boolean leftPressed;
  private boolean rightPressed;
  private boolean spaceBarPressed;
  
  private boolean shootUp;
  private boolean shootDown;
  private boolean shootLeft;
  private boolean shootRight;

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

  public boolean isShootDownPressed() {
    return shootDown;
  }

  public boolean isShootUpPressed() {
    return shootUp;
  }

  public boolean isShootLeftPressed() {
    return shootLeft;
  }

  public boolean isShootRightPressed() {
    return shootRight;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_SPACE -> spaceBarPressed = true;
      case KeyEvent.VK_W -> upPressed = true;
      case KeyEvent.VK_S -> downPressed = true;
      case KeyEvent.VK_A -> leftPressed = true;
      case KeyEvent.VK_D -> rightPressed = true;
      case KeyEvent.VK_UP -> shootUp = true;
      case KeyEvent.VK_DOWN -> shootDown = true;
      case KeyEvent.VK_LEFT -> shootLeft = true;
      case KeyEvent.VK_RIGHT -> shootRight = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int code = e.getKeyCode();
    switch (code) {
      case KeyEvent.VK_SPACE -> spaceBarPressed = false;
      case KeyEvent.VK_W -> upPressed = false;
      case KeyEvent.VK_S -> downPressed = false;
      case KeyEvent.VK_A -> leftPressed = false;
      case KeyEvent.VK_D -> rightPressed = false;
      case KeyEvent.VK_UP -> shootUp = false;
      case KeyEvent.VK_DOWN -> shootDown = false;
      case KeyEvent.VK_LEFT -> shootLeft = false;
      case KeyEvent.VK_RIGHT -> shootRight = false;
    }
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // TODO Auto-generated method stub
  }
}
