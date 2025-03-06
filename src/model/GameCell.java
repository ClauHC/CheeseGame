package model;

public abstract class GameCell {

    private boolean isDiscovered;
    protected String content;
    private boolean isVisible; //  AGREGO VARIABLE. Nuevo atributo para hacer visible la celda

    /**
     * Constructor de la clase GameCell
     * @param content
     */
    public GameCell(String content) {
        this.content = content;
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
        isVisible = true; // AGREGO MÉTOD. Hace que el contendio de la celda sea visible
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

/*
 La clase GameCell es una clase abstracta que representa una casilla del tablero del juego, es la clase de la que
 parten todas las casillas (celdas) y por tanto la más importante. Esta clase había sido dejada creada previamente
 como parte del enunciado del ejercicio.

 El funcionamiento original de la clase NO HA SIDO ALTERADO, únicamente se ha añadido una variable booleana isVisible
 ya que el código del programa según la lógica que se ha seguido lo demandaba, ya que al ser un comportamiento común
 para todas las clases de celdas que extienden a esta este era el lugar donde debía ponerse.

 isDsicovered se ha usado para indicar si la celda ha sido descubierta (visitada) o no por el ratón.

 Se ha añadido un nuevo métod setVisible() que hace que la casilla sea visible, es decir, que muestre su contenido real.

 Se han agregado líneas al métod toString() para que use isVisble además de usar isDiscover como ya hacía y muestre
 el contenido real de la celda (10, 20, 30, --, ++, CC, CH) si es visible y si no, muestre "00" o "·." según si está
 descubierta o no por el ratón.

 Content almacena el contenido real de la celda. Este contenido es diferente para cada tipo de celda que extiende a GameCell.
 Según este visible o no (isVisible), descubierta o no (isDiscover) es lo que se muestra al usuario en el tablero.
 */
