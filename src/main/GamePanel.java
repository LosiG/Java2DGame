package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    
    final Integer originalTileSize = 16;
    final Integer scale = 3;
    final Integer tileSize = originalTileSize * scale;
    final Integer maxScreenColumn = 16;
    final Integer maxScreenRow = 12;
    final Integer screenWidth = tileSize * maxScreenColumn;
    final Integer screenHeight = tileSize * maxScreenRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

}
