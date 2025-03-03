package model;
import java.util.Random;
import java.util.Scanner;

public class MinusCell extends GameCell implements Questionable {
    public MinusCell() {
        this.content = "00";
    }

    @Override
    public String getQuestion() {
        return "Adivina un número del 1 al 3:";
    }

    @Override
    public boolean submitAnswer(String answer) {
        int correctNumber = new Random().nextInt(3) + 1;
        return answer.equals(String.valueOf(correctNumber));
    }

    public void reveal(Scanner scanner) {
        setDiscovered();
        this.content = "·.";
        System.out.println(getQuestion());
        String userAnswer = scanner.next();
        if (!submitAnswer(userAnswer)) {
            System.out.println("¡Incorrecto! Pierdes 50 puntos.");
        } else {
            System.out.println("¡Adivinaste! No pierdes puntos.");
        }
    }
}