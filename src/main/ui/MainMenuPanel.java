package main.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Main;

public class MainMenuPanel extends JPanel {

    static final Integer ORIGINAL_TILESIZE = 16;
    static final Integer SCALE = 3;
    static final Integer TILE_SIZE = ORIGINAL_TILESIZE * SCALE;
    static final Integer MAX_SCREEN_COLUMN = 16;
    static final Integer MAX_SCREEN_ROW = 12;
    static final Integer SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    static final Integer SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public MainMenuPanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.YELLOW);
        JButton startButton = new JButton("START");
        startButton.addActionListener(new StartListener());
        this.add(startButton);
    }

    public class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            Main.startGame();
        }
    }
}
