package main;

import model.Game;

public class Main {
    public static void main(String[] args) {

        System.out.println("Bienvenidos a Chese Mouse Game" +
                "\nEl objetivo es que el ratón 'MM' llegue al queso 'CH'" +
                "\nPara desplazar el ratón pulse:" +
                "\nA: derecha" +
                "\nD: izquierda" +
                "\nW: arriba" +
                "\nS: abajo");

        Game game = new Game();
        game.play();

    }
}