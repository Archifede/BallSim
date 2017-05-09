package app;

import data.Ball;
import javafx.geometry.Point2D;
import logic.Physics;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Victor
 * @since 09 mai 17h16
 */
public class GUIApp {

    private Game game;
    private int tick;

    public GUIApp() {
        startSim();

    }

    public void startSim() {
        Ball ball;


        double secondPerTick = 0.2;
        ball = new Ball(Point2D.ZERO, 100, 0.05);
        ball.lauch(200000, 80);
        game = new Game(ball);
        créationPlateau();
        KeyAdapter keykey = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Physics.apply(ball, secondPerTick);
                System.out.println("At " + secondPerTick * tick++ + "s [ " + ball + "]");
                game.repaint();

            }
        };
        game.addKeyListener(keykey);
        MouseAdapter moumous = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                game.requestFocus();
            }
        };
        game.addMouseListener(moumous);
        game.requestFocus();
    }

    private void créationPlateau() {


        JFrame frame = new JFrame("Sample Frame");
        frame.add(game);
        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
