package model;

public abstract class GameCell {

    private boolean isDiscovered;
    protected String content;
    private boolean isVisible; //  HAGO CAMBIO - AGREGO. Nuevo atributo para hacer visible la celda

    public GameCell(String content) {
        //this.content = content; // HAGO CAMBIO - INABILITO. No se asigna el contenido en el constructor
        this.isDiscovered = false; // HAGO CAMBIO - AGREGO. Por defecto, las casillas no están descubiertas
        this.isVisible = false; // HAGO CAMBIO - AGREGO. Por defecto, las casillas no son visibles
    }

    public boolean isDiscovered() {
        return isDiscovered;
    }

    public void setDiscovered() {
        isDiscovered = true;
    }

    public void setVisible() {
        isVisible = true; // HAGO CAMBIO - AGREGO. Hace que la celda sea visible sin importar si fue descubierta
    }

    public boolean getVisible() {
        return isVisible; // HAGO CAMBIO - AGREGO. Devuelve si la celda está visible o no
    }

    /*
    @Override
    public String toString() {
        return  content;
    }
     */

    /** Métod que devuelve el contenido de la celda
     *
     */
    @Override
    public String toString() {
        if (isVisible) {
            return content; // Si está visible, muestra su contenido real
        }
        return isDiscovered ? "·." : "00"; // Si no es visible, sigue la lógica normal
    }
}
