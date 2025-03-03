package model;
import java.util.Random;
import java.util.Scanner;

public class PlusCell extends GameCell implements Questionable {
    private String question;
    private String[] options;
    private String correctAnswer;

    public PlusCell() {
        this.content = "00";
        String[][] questionSet = {
                {"Di un IDE para programar en Java", "IntelliJ", "Eclipse", "NetBeans"},
                {"Lenguaje en que está hecho Java", "C", "C++", "Java"},
                {"¿Qué significa JVM?", "Java Virtual Machine", "Java Visual Model", "Java Vector Map"}
        };
        Random randomQuestion = new Random();
        int index = randomQuestion.nextInt(questionSet.length);
        this.question = questionSet[index][0];
        this.correctAnswer = questionSet[index][1];
        this.options = new String[]{questionSet[index][1], questionSet[index][2], questionSet[index][3]};
    }

    //@Override es una anotación en Java que se usa cuando un métod de una clase hija sobrescribe un métod de su clase padre o de una interfaz.
    @Override
    public String getQuestion() {
        return question + " (Opciones: " + String.join(", ", options) + ")";
    }

    @Override
    public boolean submitAnswer(String answer) {
        return answer.equalsIgnoreCase(correctAnswer);
    }

    public void reveal(Scanner scanner) {
        setDiscovered();
        this.content = "·.";
        System.out.println(getQuestion());
        String userAnswer = scanner.nextLine();
        if (submitAnswer(userAnswer)) {
            System.out.println("¡Correcto! +50 puntos.");
        } else {
            System.out.println("Incorrecto, no ganas puntos.");
        }
    }
}