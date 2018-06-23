package SnakeGame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private JPanel panel = new Panel();

    Window(){
        this.add(panel);
        this.setSize(WIDTH,HEIGHT);
        this.setVisible(true);
        this.setTitle("Snake Game");
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:	// -> Right
                        //if it's not the opposite direction
                        if(Snake.direction!=2)
                            Snake.direction=1;
                        break;
                    case KeyEvent.VK_UP:	// -> Top
                        if(Snake.direction!=3)
                            Snake.direction=4;
                        break;

                    case KeyEvent.VK_LEFT: 	// -> Left
                        if(Snake.direction!=1)
                            Snake.direction=2;
                        break;

                    case KeyEvent.VK_DOWN:	// -> Bottom
                        if(Snake.direction!=4)
                            Snake.direction=3;
                        break;

                    default: 	break;
                }
            }
        });
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        new Window();
    }

}
