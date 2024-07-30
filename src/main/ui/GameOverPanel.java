package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

public class GameOverPanel extends JPanel {
    static final Integer ORIGINAL_TILESIZE = 16;
    static final Integer SCALE = 3;
    static final Integer TILE_SIZE = ORIGINAL_TILESIZE * SCALE;
    static final Integer MAX_SCREEN_COLUMN = 16;
    static final Integer MAX_SCREEN_ROW = 12;
    static final Integer SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    static final Integer SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public GameOverPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.YELLOW);
        JButton restartButton = new JButton("RESTART");
        restartButton.addActionListener(new RestartListener());
        this.add(restartButton);

        JButton mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.addActionListener(new MainMenuListener());
        this.add(mainMenuButton);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage base = null;
        try {
            base = ImageIO.read(new File("assets\\gameOverScreen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.drawImage(base, null, getFocusCycleRootAncestor());
    }

    public class RestartListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Main.startGame();
        }
    }

    public class MainMenuListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Main.mainMenu();
        }
    }
}
