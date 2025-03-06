package model;
import java.util.Random;

public class PointsCell extends GameCell {
    private int points;

    /**
     * Constructor ode la clase PointsCell
     */
    public PointsCell() {
        super("00"); // Call the GameCell constructor with a String argument
        int[] values = {10, 20, 30};
        this.points = values[new Random().nextInt(values.length)];
        this.content = "00";
    }

    /**
     * Métod que devuelve los puntos que se pueden ganar en la casilla
     * @return
     */
    public int getPoints() {
        return points;
    }

    /**
     * Métod que marca la casilla como ya descubierta
     */
    public void discovered() {
        setDiscovered();
        this.content = "·.";
    }
}