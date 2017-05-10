package app;

import data.Ball;
import gui.GameFrame;
import javafx.geometry.Point2D;
import logic.Physics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Victor
 * @since 09 mai 17h16
 */
@SuppressWarnings("restriction") // this is for eclipse
public class GUIApp {
    
    private Game game;
    private int tick;
    private Ball ball;

   
	public GUIApp() {
		
		int mass = 100;
		int friction = 10;
		int diameter = 30;
		
		/* since the ball cannot start at the positon (0,0) (otherwise 3/4 of the ball will be outside of the panel 
		 * the ball needs to actually begin at the position (diameter/2 , diameter/2) */
		Point2D point = new Point2D(
				(double) diameter/2,
				(double) diameter/2
				);
        this.ball = new Ball(point, mass, friction, diameter);
    }

    public void startSim() {
        
        double secondPerTick = 0.05;
        
        this.ball.launch(5000000, 50);
        this.game = new Game(ball);
        this.createFrame();
        
        KeyAdapter keykey = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                Physics.apply(ball, secondPerTick, game.getWidth(), game.getHeight());
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

    private void createFrame() {

        JFrame frame = new GameFrame();
        frame.add(game);
        frame.setVisible(true);
    }
}
