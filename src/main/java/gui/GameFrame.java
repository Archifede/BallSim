package gui;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.*;

/**
 * This is the frame of the Ball bouncing simulation.
 * 
 * @author Only Brad
 *
 */
public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7927156597267134363L;
	private static final double multiplier = 0.5;
	private final int WIDTH;
	private final int HEIGHT;
	
	public GameFrame() {
		
		this.setTitle("Ball Sim");
		
		// get the size of the users screen to adjust the size of the frame to their monitor
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	    this.WIDTH = gd.getDisplayMode().getWidth();
	    this.HEIGHT = gd.getDisplayMode().getHeight();
		
		// Start by adding a black border the primary panel inside the frame
		JPanel pane = (JPanel) this.getContentPane();
		pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		this.setSize((int)(this.WIDTH*multiplier),(int)(this.HEIGHT*multiplier));
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

}
