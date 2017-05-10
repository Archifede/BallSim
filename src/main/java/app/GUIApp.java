package app;

import data.Ball;
import gui.GameFrame;
import gui.GamePanel;
import javafx.geometry.Point2D;
import logic.Physics;

import javax.swing.*;
import java.awt.*;

/**
 * @author Victor
 * @since 09 mai 17h16
 */
@SuppressWarnings("restriction") // this is for eclipse
public class GUIApp {

    private double angle;
    private double kineticEnergy = 50000000;
    private double mass = 100;
    private double friction = 0.01;
    private int diameter = 25;
    private boolean restart = true;


    public GUIApp() {

    }

    public void startSim() {

		/* since the ball cannot start at the positon (0,0) (otherwise 3/4 of the ball will be outside of the panel
		 * the ball needs to actually begin at the position (diameter/2 , diameter/2) */

		while(true){
            restart = true;
            fieldsChooser();
            Ball ball = new Ball(new Point2D(
                    (double) diameter / 2,
                    (double) diameter / 2
            ), mass, friction, diameter);
            ball.launch(kineticEnergy, angle);
            JFrame gameFrame = new GameFrame();
            GamePanel gamePanel = new GamePanel(ball);
            gameFrame.add(gamePanel);
            gameFrame.setVisible(true);
            Button button = new Button("Restart");
            button.addActionListener(e -> {
                restart = false;
            });
            gamePanel.add(button);
            periodicTick(gamePanel);
            gameFrame.setVisible(false);
        }


    }

    private void periodicTick(GamePanel gamePanel){
        while (restart) {
            this.tick(gamePanel);
        }
    }

    private void tick(GamePanel gamePanel) {
        double secondPerTick = 0.01;
        try {
            Thread.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Physics.apply(gamePanel.getBall(), secondPerTick, gamePanel.getWidth(), gamePanel.getHeight());
        gamePanel.repaint();
    }

    private void fieldsChooser() {
        JTextField kineticEnergyField = new JTextField("1000000", 10);
        JTextField angleField = new JTextField("40", 10);
        JTextField diameterField = new JTextField("30", 10);
        JTextField frictionField = new JTextField("0.02", 10);
        JTextField massField = new JTextField("50", 10);

        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("kineticEnergy(J):"));
        myPanel.add(kineticEnergyField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("angle(°)"));
        myPanel.add(angleField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("diameter(m):"));
        myPanel.add(diameterField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("friction(kg.s-1):"));
        myPanel.add(frictionField);
        myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("mass(kg):"));
        myPanel.add(massField);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Physics", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {


            this.kineticEnergy = Double.parseDouble(kineticEnergyField.getText());
            this.angle = Double.parseDouble(angleField.getText());
            this.diameter = (int) Double.parseDouble(diameterField.getText());
            this.friction = Double.parseDouble(frictionField.getText());
            this.mass = Double.parseDouble(massField.getText());

        }

    }
}
