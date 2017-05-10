package logic;

import data.Ball;
import javafx.geometry.Point2D;

/**
 * @author Victor
 * @since 08 mai 17h40
 */
public class Physics {
    public static double speed2kinetic(double speed, double mass) {
        return 0.5 * mass * Math.pow(speed, 2);
    }

    public static double kinetic2Celerity(double kineticEnergy, double mass) {
        return Math.sqrt(2 * (kineticEnergy / mass));
    }

    public static final double GRAVITATIONNAL_ACCELERATION = 9.81;
    public static final double BOUNCE_RATE = 0.66;
    public static final double FLOOR_RATE = 0.9996;


    @SuppressWarnings("restriction") // This is for eclipse
    public static void apply(Ball ball, double deltaT, int width, int height) {

        Point2D celerity = ball.getCelerity();
        Point2D coordinates = ball.getCoordinates();
        double tau = ball.mass / ball.friction;
        double g = GRAVITATIONNAL_ACCELERATION;
        int d = (int) ball.getDiameter();

        if (celerity.getY() >= -0.5 && celerity.getY() <= 0.5 && coordinates.getY() <= d / 2) {
            coordinates = new Point2D(
                    -tau * (celerity.getX()) * Math.exp(-deltaT / tau) + coordinates.getX() + tau * (celerity.getX()),
                    d / 2);
        } else
        coordinates = new Point2D(
                -tau * (celerity.getX()) * Math.exp(-deltaT / tau) + coordinates.getX() + tau * (celerity.getX()),
                -tau * (celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g * deltaT + coordinates.getY() + tau * (celerity.getY() + tau * g));
        if (celerity.getY() >= -0.5 && celerity.getY() <= 0.5 && coordinates.getY() <= d / 2)
            celerity = new Point2D(
                    FLOOR_RATE * celerity.getX() * Math.exp(-deltaT / tau),
                    0);
        if ((coordinates.getY() <= d / 2 && celerity.getY() < 0 || coordinates.getY() >= height - d / 2 && celerity.getY() > 0)
                && (coordinates.getX() <= d / 2 && celerity.getX() < 0 || coordinates.getX() >= width - d / 2 && celerity.getX() > 0))
            celerity = new Point2D(
                    (-BOUNCE_RATE) * (celerity.getX() * Math.exp(-deltaT / tau)),
                    (-BOUNCE_RATE) * ((celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g));

        else if (coordinates.getY() <= d / 2 && celerity.getY() < 0
                || coordinates.getY() >= height - d / 2 && celerity.getY() > 0)
            celerity = new Point2D(
                    celerity.getX() * Math.exp(-deltaT / tau),
                    (-BOUNCE_RATE) * (
                            (celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g));
        else if (coordinates.getX() <= d / 2 && celerity.getX() < 0 || coordinates.getX() >= width - d / 2 && celerity.getX() > 0)
            celerity = new Point2D(
                    (-BOUNCE_RATE) * (celerity.getX() * Math.exp(-deltaT / tau)),
                    ((celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g));

        else
            celerity = new Point2D(
                    celerity.getX() * Math.exp(-deltaT / tau),
                    (celerity.getY() + tau * g) * Math.exp(-deltaT / tau) - tau * g);

        ball.setCelerity(celerity);
        ball.setCoordinates(coordinates);
    }


}
