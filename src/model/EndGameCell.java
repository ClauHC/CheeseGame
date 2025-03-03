package model;

public class EndGameCell extends GameCell {
    private EndGamecellType type;

    public EndGameCell(EndGamecellType type) {
        this.type = type;
        this.content =type.getSymbol();
    }

    public EndGamecellType getType() {
        return type;
    }
}
