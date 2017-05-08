package data;

import javafx.geometry.Point2D;
import logic.Physics;

import java.text.DecimalFormat;


/**
 * @author Victor
 * @since 08 mai 17h32
 */
public class Ball {


    private Point2D coordinates;
    private Point2D celerity;
    public final double mass;
    public final double friction;


    public Ball(Point2D coordinates, double mass, double friction) {
        this.friction = friction;

        this.coordinates = coordinates;
        this.mass = mass;
    }

    public void lauch(double kineticEnergy, double angle) {
        double angleDegree = Math.toRadians(angle);

        double absoluteCelerity = Physics.kinetic2Celerity(kineticEnergy, mass);
        this.celerity = new Point2D(Math.round(absoluteCelerity * Math.cos(angleDegree)), Math.round(absoluteCelerity * Math.sin(angleDegree)));

    }

    public void nextPosition(double deltaT) {

        Physics.apply(this, deltaT);


    }

    public Point2D getCelerity() {
        return new Point2D(this.celerity.getX(), this.celerity.getY());
    }

    public void setCelerity(Point2D celerity) {
        this.celerity = celerity;
    }

    public Point2D getCoordinates() {
        return new Point2D(this.coordinates.getX(), this.coordinates.getY());
    }

    public void setCoordinates(Point2D coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#.##");
        String vx = df.format(celerity.getX());
        String vy = df.format(celerity.getY());
        String x = df.format(coordinates.getX());
        String y = df.format(coordinates.getY());

        return "vx:" + vx + "m.s-1  vy:" + vy + "m.s-1  x:"
                + x + "m  y:" + y + "m";
    }
}
