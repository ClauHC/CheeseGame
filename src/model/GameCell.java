package model;

public abstract class GameCell {

    private boolean isDiscovered;
    protected String content;
    private boolean isVisible; //  AGREGO. Nuevo atributo para hacer visible la celda

    /**
     * Constructor de la clase GameCell
     * @param content
     */
    public GameCell(String content) {
        this.content = content;
        this.isDiscovered = false; // AGREGO. Por defecto, las casillas no están descubiertas (por defecto están en 00 y no en ·.)
        this.isVisible = false; // AGREGO. Por defecto, las casillas no son visibles (por defecto están en 00 y no en 10, 20, 30, --, ++, CC)
    }

    /**
     * Métod que devuelve si la casilla ha sido descubierta (visitada) por el ratón
     * @return
     */
    public boolean isDiscovered() {
        return isDiscovered;
    }

    /**
     * Métod que marca la casilla como descubierta
     */
    public void setDiscovered() {
        isDiscovered = true;
    }

    /**
     * Métod que devuelve el contenido de la casilla, es decir la hace visible, elimina los 00 o ·. que la cubren
     * @return
     */
    public void setVisible() {
        isVisible = true; // AGREGO. Hace que el contendio de la celda sea visible
    }

    /*
    @Override
    public String toString() {
        return  content;
    }
     */

    /** Métod que devuelve el contenido de la celda
     *  Mantengo el métod original y agrego lineas para poder jugar con la visibilidad de la celda
     * @return String
     */
    @Override
    public String toString() {
        if (isVisible) {
            return content; // Si está visible, muestra su contenido real
        }
        return isDiscovered ? "·." : "00"; // Si no es visible, sigue la lógica normal
    }
}
