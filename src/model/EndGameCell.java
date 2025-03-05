package model;

public class EndGameCell extends GameCell {
    private EndGamecellType type;

    public EndGameCell(EndGamecellType type) {
        super(type.getSymbol()); // Content of the cell (CC or CH)
        this.type = type;

        // Make only the cheese visible from the start
        if (type == EndGamecellType.Cheese) {
            setVisible();
        }
    }

    public EndGamecellType getType() {
        return type;
    }
}