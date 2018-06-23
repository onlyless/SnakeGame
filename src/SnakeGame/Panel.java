package SnakeGame;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Snake snake = Snake.getInstance();

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawRect(0,0,WIDTH,HEIGHT);
        gamePaint(g);

        repaint();
    }

    private void gamePaint(Graphics g) {
        Color temp = g.getColor();
        Integer[][] Grid = snake.getGrid();

        int cnt_h = Grid.length;
        int cnt_w = Grid[0].length;
        int height = HEIGHT/cnt_h;
        int width = WIDTH/cnt_w;

        for(int i=0;i<Grid.length;i++){
            for(int j=0;j<Grid[i].length;j++){
                if(Grid[i][j]==2) {
                    g.setColor(Color.BLACK);
                    g.fillRect(width * j, height * i, width, height);
                    g.setColor(temp);
                }
                else if(Grid[i][j] == 3){
                    g.setColor(Color.RED);
                    g.fillRect(width * j, height * i, width, height);
                    g.setColor(temp);
                }
            }
        }
        g.setColor(temp);

    }

}
