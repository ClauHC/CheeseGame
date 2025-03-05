package main;
import model.Game;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenidos a Chese Mouse Game" +
                "\nEl objetivo es que el ratón 'MM' llegue al queso 'CH'" +
                "\nPara desplazar el ratón pulse:" +
                "\nA: derecha" +
                "\nD: izquierda" +
                "\nW: arriba" +
                "\nS: abajo");

        System.out.print("Presiona T para ver el Modo Test, o cualquier otra tecla para empezar a jugar: ");
        char key = scanner.next().toUpperCase().charAt(0);

        boolean isTestMode = false;

        // Crear el juego (se genera el tablero, el modo test recoge el tablero completo)
        Game game = new Game(isTestMode);

        if (key == 'T') {
            game.setTestMode(true); // Activar el modo test
            game.showTestBoard(); // Mostrar el tablero en modo test
            System.out.println("Ahora vamos a jugar.");
            game.setTestMode(false); // Desactivar el modo test para jugar
        }

        game.play(); // Iniciar el juego
    }
}