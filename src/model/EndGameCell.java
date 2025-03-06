package model;

public class EndGameCell extends GameCell {
    private EndGamecellType type;

    /**
     * Constructor de la clase EndGameCell
     * @param type
     */
    public EndGameCell(EndGamecellType type) {
        super(type.getSymbol()); // Contenido de la casilla (CC or CH)
        this.type = type;

        // Make only the cheese visible from the start
        if (type == EndGamecellType.Cheese) {
            setVisible();
        }
    }

    /**
     * Métod que devuelve el tipo de casilla de fin de juego
     */
    public EndGamecellType getType() {
        return type;
    }
}