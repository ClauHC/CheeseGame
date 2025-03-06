package model;

public abstract class GameCell {

    private boolean isDiscovered;
    protected String content;
    private boolean isVisible; //  AGREGO. Nuevo atributo para hacer visible la celda

    public GameCell(String content) {
        this.content = content;
        this.isDiscovered = false; // AGREGO. Por defecto, las casillas no están descubiertas (por defecto están en 00 y no en ·.)
        this.isVisible = false; // AGREGO. Por defecto, las casillas no son visibles (por defecto están en 00 y no en 10, 20, 30, --, ++, CC)
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered() {
        isDiscovered = true;
    }

    public void setVisible() {
        isVisible = true; // AGREGO. Hace que el contendio de la celda sea visible
    }

    public boolean getVisible() {
        return isVisible; // AGREGO. Devuelve si la celda está visible o no
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
