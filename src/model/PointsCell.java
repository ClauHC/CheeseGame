package model;
import java.util.Random;

public class PointsCell extends GameCell {
    private int points;

    public PointsCell() {
        super("00"); // Call the GameCell constructor with a String argument
        int[] values = {10, 20, 30};
        this.points = values[new Random().nextInt(values.length)];
        this.content = "00";
    }

    public int getPoints() {
        return points;
    }

    public void reveal() {
        setDiscovered();
        this.content = "·.";
    }
}