package model;
import java.util.Random;
import java.util.Scanner;

public class MinusCell extends GameCell implements Questionable {

    /**
     * Constructor de la clase MinusCell
     */
    public MinusCell() {
        super("00"); // Llama al constructor GameCell con un argumento String
        this.content = "00";
    }

    /**
     * Métod que devuelve la pregunta que se le debe hacer al usuario
     * sobreescribe el métod de la interfaz Questionable
     * @return
     */
    @Override
    public String getQuestion() {
        return "Adivina un número del 1 al 3:";
    }

    /**
     * Métod que compara la respuesta del usuario con la respuesta correcta
     * Sobreescribe el métod de la interfaz Questionable
     * @param answer
     * @return
     */
    @Override
    public boolean submitAnswer(String answer) {
        int correctNumber = new Random().nextInt(3) + 1;
        return answer.equals(String.valueOf(correctNumber));
    }

    /**
     * Métod que marca la casilla como ya descubierta
     * y pide al usuario que adinive un número
     * @param scanner
     * @param game
     */
    public void discovered(Scanner scanner, Game game) {
        setDiscovered();
        this.content = "·.";
        System.out.println(getQuestion());
        String userAnswer = scanner.next();
        if (!submitAnswer(userAnswer)) {
            System.out.println("¡Incorrecto! Pierdes 50 puntos.");
            game.updateScore(-50); // la clase Game se encarga de restar los puntos en la parte donde se llama al métod reveal
        } else {
            System.out.println("¡Adivinaste! No pierdes puntos.");
        }
    }
}