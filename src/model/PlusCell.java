package model;
import java.util.Random;
import java.util.Scanner;

public class PlusCell extends GameCell implements Questionable {
    private String question;
    private String[] options;
    private String correctAnswer;

    public PlusCell() {
        super("00"); // Llamar al constructor GameCell con un argumento String
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
        System.out.print("Elige una opción (1, 2, 3): ");
        int userChoice = scanner.nextInt();
        scanner.nextLine(); // limpiar el buffer

        String userAnswer = "";
        if (userChoice == 1) {
            userAnswer = options[0];
        } else if (userChoice == 2) {
            userAnswer = options[1];
        } else if (userChoice == 3) {
            userAnswer = options[2];
        }

        if (submitAnswer(userAnswer)) {
            System.out.println("¡Correcto! +50 puntos.");
            // la clase Game se encarga de sumar los puntos en la parte donde se llama al métod reveal
        } else {
            System.out.println("Incorrecto, no ganas puntos.");
        }
    }
}